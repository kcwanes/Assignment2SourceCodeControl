package sourcecodecontrol;

import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.lang.String;
import java.text.SimpleDateFormat;

public class BranchClass {
	public void makeBranch() throws IOException {

		String fileName;
		String newBranch;
		Boolean confirm;
		Scanner in = new Scanner(System.in);

		// keep asking the user for the file name until the correct
		// file name is input (allows them to correct for typos)
		do {
			System.out
					.println("Enter the name of the file under source code control (with the file extension):");
			fileName = in.nextLine();
			System.out.print(fileName);
			confirm = Helper.Confirm("\nIs this correct? (y|n)");

		} while (confirm != true);

		// keep asking the user for the branch name until the correct
		// branch name is input (allows them to correct for typos)
		do {
			System.out
					.println("Enter the name of the new branch (latest revision will be copied from 'main'):");
			newBranch = in.nextLine();
			System.out.print("The new branch will be called '" + newBranch
					+ "'.");
			confirm = Helper.Confirm("\nIs this correct? (y|n)");

		} while (confirm != true);

		System.out.println("The new branch file path is:");
		String newBranchFilePath = Helper.RepoPath + File.separator
				+ Helper.stripExtension(fileName) + File.separator + newBranch;
		System.out.println(newBranchFilePath);

		String sourceMainBranch = Helper.RepoPath + File.separator
				+ Helper.stripExtension(fileName) + File.separator + "main";

		System.out.println("The source main branch file path is:");
		System.out.println(sourceMainBranch);

		int latestVersion = Helper.countFileVersions(fileName);

		// 'path' array will store the output of the 'getPathToFileVersion'
		// method, which returns a variety of file path extensions needed for
		// parsing.
		String[] path = new String[5];
		path = Helper.getPathToFileVersion(fileName, "main", latestVersion);

		System.out.println("path[0] = " + path[0]);
		System.out.println("path[1] = " + path[1]);
		System.out.println("path[2] = " + path[2]);
		System.out.println("path[3] = " + path[3]);
		System.out.println("path[4] = " + path[4]);

		// String targetFileFilePath = newBranchFilePath + File.separator
		// + path[2];
		String targetFileFilePathWithComment = newBranchFilePath
				+ File.separator + path[3];

		// 'src' is a file object that contains the file path of the source file
		// from 'main'
		// to be copied to the new branch.
		File src = new File(path[0]);

		// 'srcComment' is also a file object that contains the file path of the
		// source comment
		// file from 'main' that will be copied to the new branch
		File srcComment = new File(path[1]);

		// the string that we will need to create the file object which
		// contains the destination file path
		newBranchFilePath = newBranchFilePath + File.separator + path[4];

		// file object which contains the destination file path of the target
		// file
		File dest = new File(newBranchFilePath);

		// file object which contains the destination file path of the target
		// comment
		File destComment = new File(targetFileFilePathWithComment);

		// create the directory where the file revisions for the new branch will
		// be
		// stored
		dest.mkdirs();

		// string that contains the file path of the target file to be copied
		// to the new branch
		newBranchFilePath = newBranchFilePath + File.separator + fileName;

		// file object that contains the file path of the target file to be
		// copied
		// to the new branch
		dest = new File(newBranchFilePath);

		// create target file in new branch (file will be empty after this
		// operation)
		dest.createNewFile();
		// create target comment file in new branch (file will be empty after
		// this operation)
		destComment.createNewFile();

		// copy file contents from source file in 'main' to target file in new
		// branch
		Helper.copyIntoFile(src, dest);

		// copy file contents from source comment file in 'main' to target file
		// in new branch
		Helper.copyIntoFile(srcComment, destComment);

	}

}

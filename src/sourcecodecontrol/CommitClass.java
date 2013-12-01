package sourcecodecontrol;

import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.lang.String;
import java.text.SimpleDateFormat;

public class CommitClass {

	/**
	 * Main method for the commit feature Currently gets the filepath and
	 * filename of the file the user wishes to commit (may change this such that
	 * the main Java file of the project gets this user input).
	 * 
	 * Will create a copy of the user specified txt file and store it in the
	 * same folder with a timestamp appended to the filename
	 * 
	 * @throws IOException
	 */

	public void commitMethod() throws IOException {
		String fileName;
		String path;
		String branch;
		String comment;
		String theTime;
		String pathWithFileName;
		String newCommentPath;
		String newDir;
		String newPath;

		Scanner in = new Scanner(System.in);

		do {

			System.out
					.println("Enter the filepath of the file you wish to commit ");
			System.out
					.println("[without a slash at the end -- e.g. C:\\Users\\Bob\\repo (Windows) or /Users/Bob/repo (Unix)]");
			path = in.nextLine();

			System.out
					.println("Enter the name of the file you wish to commit (with file extension):");
			fileName = in.nextLine();

			System.out
					.println("Enter the branch you want to commit to ('main' is the default):");
			branch = in.nextLine();

			pathWithFileName = path + File.separator + fileName;
			String branchFilePath = Helper.RepoPath + File.separator
					+ Helper.stripExtension(fileName) + File.separator + branch;

			System.out.println("The branch file path is:");
			System.out.println(branchFilePath);

			File branchPath = new File(branchFilePath);

			// If the user tried to commit to a non-existent branch, quit the
			// program.
			// Exception: If they are trying to commit to 'main' and it doesnt
			// already exist,
			// create the 'main' branch.
			// User must 'branch' to commit to another branch that is not 'main'
			if (branchPath.exists() == false && !branch.equals("main")) {
				System.out.println("Branch '" + branch + "' does not exist.");
				System.out
						.println("Please use the command 'branch' before commiting to a non-existent branch.");
				System.out.println("Program quitting.");
				System.exit(1);
			}

			// File repoFolder = new File (pathWithFileName);
			// repoFolder.mkdirs();

			System.out.println("\nThe file is: ");
			System.out.println(pathWithFileName);
		} while (Helper.Confirm("Is this correct? (y|n)") != true);

		System.out.println("Enter a comment for this revision:");
		comment = in.nextLine();

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd-HH-mm-ss");
		theTime = sdf.format(date);

		// simplify this
		newDir = Helper.RepoPath + File.separator
				+ Helper.stripExtension(fileName) + File.separator + branch
				+ File.separator + theTime;

		File src = new File(path + File.separator + fileName);

		newDir = newDir.replace("\\", "\\\\");

		newPath = newDir + File.separator + fileName;
		newCommentPath = newDir + File.separator + "comment.txt";

		newPath = newPath.replace("\\", "\\\\");
		newCommentPath = newCommentPath.replace("\\", "\\\\");

		File directory = new File(newDir);
		File newDest = new File(newPath);
		File newCmt = new File(newCommentPath);

		directory.mkdirs();
		newDest.createNewFile();
		newCmt.createNewFile();

		Helper.copyIntoFile(src, newDest);
		Helper.printStringToFile(newCmt, theTime + '\n' + comment);

	}
}

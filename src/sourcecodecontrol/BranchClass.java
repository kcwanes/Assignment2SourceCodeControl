package sourcecodecontrol;

import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.lang.String;
import java.text.SimpleDateFormat;

public class BranchClass {
	public void makeBranch() throws IOException{
		
		String fileName;
		String newBranch;
		Boolean confirm;
		Scanner in = new Scanner(System.in);
		do{
			System.out.println("Enter the name of the file under source code control (with the file extension):");
			fileName = in.nextLine();
			System.out.print(fileName);
			confirm = Helper.Confirm("\nIs this correct? (y|n)");
			System.out.println("Enter the name of the new branch (latest revision will be copied from 'main'):");
			newBranch = in.nextLine();
			System.out.print("The new branch will be called '"+ newBranch + "'.");
			confirm = Helper.Confirm("\nIs this correct? (y|n)");
			
			System.out.println("The new branch file path is:");
			String newBranchFilePath = Helper.RepoPath + File.separator + Helper.stripExtension(fileName) + File.separator + newBranch;
			System.out.println(newBranchFilePath);
			
			String sourceMainBranch = Helper.RepoPath + File.separator + Helper.stripExtension(fileName) + File.separator + "main";
			
			System.out.println("The source main branch file path is:");
			System.out.println(sourceMainBranch);
			
			int fileVersions = Helper.countFileVersions(fileName);
			System.out.println(fileVersions);
			
			String[] path = new String[2];
			
			path = Helper.getPathToFileVersion(fileName, fileVersions);
			
			System.out.println("path[0] = " + path[0]);
			System.out.println("path[1] = " + path[1]);
			System.out.println("path[2] = " + path[2]);
			System.out.println("path[3] = " + path[3]);
			System.out.println("path[4] = " + path[4]);
			
			String theNewBranchFilePath = newBranchFilePath + File.separator + path[2];
			String newBranchFilePathWithComment = newBranchFilePath + File.separator + path[3];
			
			System.out.println("The new NEW branch file path is:");
			System.out.println(theNewBranchFilePath);

			System.out.println("The new branch file path, with comment, is:");
			System.out.println(newBranchFilePathWithComment);
			
			File src = new File(path[0]);
			File srcComment = new File(path[1]);
			
			newBranchFilePath = newBranchFilePath + File.separator + path[4];
			
			File dest = new File(newBranchFilePath);
			File destComment = new File(newBranchFilePathWithComment);
			
			dest.mkdirs();
			
			newBranchFilePath = newBranchFilePath + File.separator + fileName;
			
			dest = new File(newBranchFilePath);
						
			dest.createNewFile();
			destComment.createNewFile();
			
			Helper.copyIntoFile(src, dest);
			Helper.copyIntoFile(srcComment, destComment);
			
		}while(confirm != true);
	}


}

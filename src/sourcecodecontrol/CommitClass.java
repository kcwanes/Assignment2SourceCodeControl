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
		Boolean confirm;
		String comment;
		String theTime;
		String pathWithFileName;
		String newCommentPath;
		String newDir;
		String newPath;

		Scanner in = new Scanner(System.in);
		
		do{
	
			System.out.println("Enter the filepath of the file you wish to commit ");
			System.out
					.println("[without a slash at the end -- e.g. C:\\Users\\Bob\\repo (Windows) or /Users/Bob/repo (Unix)]");
			path = in.nextLine();
	
			System.out.println("Enter the name of the file you wish to commit:");
			fileName = in.nextLine();
			
			pathWithFileName = path + File.separator + fileName;
		
			//File repoFolder = new File (pathWithFileName);
			//repoFolder.mkdirs();
	
			System.out.println("\nThe file is: ");
			System.out.println(pathWithFileName);
			confirm = Helper.Confirm("Is this correct? (y|n)");
		}while(confirm != true);
	
		System.out.println("Enter a comment for this revision:");
		comment = in.nextLine();
		
		System.out.println("Your comment is:");
		System.out.println(comment);
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd-HH-mm-ss");
		theTime = sdf.format(date);


		newDir = Helper.RepoPath + File.separator + Helper.stripExtension(fileName) + File.separator + theTime;
		System.out.println("newFilePath = " + newDir);
		
		File src = new File(path + File.separator + fileName);

		newDir = newDir.replace("\\", "\\\\");
		
		newPath = newDir + File.separator + fileName;
		newCommentPath = newDir + File.separator + "Comment.txt";
		
		newPath = newPath.replace("\\", "\\\\");
		newCommentPath = newCommentPath.replace("\\", "\\\\");		
		
		File directory = new File(newDir);
		File newDest = new File (newPath);
		File newCmt = new File(newCommentPath);

		directory.mkdirs();
		newDest.createNewFile();
		newCmt.createNewFile();
	
		Helper.copyIntoFile(src, newDest);
		Helper.printStringToFile(newCmt, theTime + '\n' + comment);

	}
}

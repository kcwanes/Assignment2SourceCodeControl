package sourcecodecontrol;

import java.io.*;
import java.nio.*;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;
import java.lang.String;
import java.lang.Object;

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

		String pathWithFileName;

		Scanner in = new Scanner(System.in);

		System.out
				.println("Enter the filepath of the file you wish to commit ");
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
		
		System.out.println("Enter a comment for this revision:");
		comment = in.nextLine();
		
		System.out.println("Your comment is:");
		System.out.println(comment);
		
		Date date = new Date();
		System.out.println(new Timestamp(date.getTime()));

		// theTime contains the timestamp we want to append
		// stored as a string
		String theTime = date.toString();

		if (confirm == true) {
			
			// call the method that will append a timestamp to the filename
			String newFile = Helper.appendTimeStamp(fileName, theTime);
			
			System.out.println("newFile = " + newFile);

			String newFilePath = path + File.separator + newFile;
			System.out.println("newFilePath = " + newFilePath);
			

			File src = new File(pathWithFileName);

			newFilePath = newFilePath.replace("\\", "\\\\");
			System.out.println("newFilePath = " + newFilePath);
			
			

			String newFilePathStripped = Helper.stripExtension(newFilePath);
			
			System.out.println("newFilePath (w/o extension) = " + newFilePathStripped);
			
			File dest = new File(newFilePathStripped);
			dest.mkdirs();
			
			//trying fix Unix bug
			String newPath = newFilePathStripped + File.separator + newFile;
			System.out.println("newPath: ");
			System.out.println(newPath);
			
			File newDest = new File (newPath);
			newDest.createNewFile();
			
			String newFileWithComment = Helper.appendTimeStampWithComment(fileName, theTime, comment);
			//System.out.println("newFileWithComment = " + newFileWithComment);
			//String newFilePathWithComment = path + File.separator + newFileWithComment;
			//newFilePathWithComment = newFilePathWithComment.replace("\\", "\\\\");
			//System.out.println("newFilePathWithComment = " + newFilePathWithComment);

			//cause of a bug (remove two file separators and put one instead?)
			newFilePathStripped = newFilePathStripped + File.separator + newFileWithComment;
			//newFilePathStripped = newFilePathStripped.replace("\\", "\\\\");
			
			System.out.println("new newFilePathStripped:");
			System.out.println(newFilePathStripped);
			
			System.out.println("File separator is:");
			System.out.println(File.separator);

			//src = new File(newFilePathStripped);
			
			Helper.copyFile(src, newDest);
			
			PrintWriter out = new PrintWriter(newFilePathStripped);
			out.print(comment);
			out.close();

		} else if (confirm == false) {
			
			// allow user to re-enter filename

		}
		

	}
}

package sourcecodecontrol;

import java.io.*;
import java.sql.*;
import java.util.Scanner;
import java.lang.String;

public class CommitClass {

	public void commitMethod() {
		String fileName;
		String confirm;

		Scanner in = new Scanner(System.in);
		System.out
				.println("Enter the name of the file you wish to commit (or 'exit'):");
		fileName = in.nextLine();
		// keep getting filenames while the user did not enter 'exit'
		// while (!fileName.equalsIgnoreCase("exit")) {
		System.out.println("\nYou've entered: '" + fileName + "'.");
		System.out.println("Is this correct? (y|n)");
		confirm = in.nextLine();

		if (confirm.equalsIgnoreCase("y")) {
			System.out.println("You said yes.");

			try {
				System.out.println("\nAgain, You've entered: '" + fileName
						+ "'.");
				
				// ideally, object inFile can read text from the user specified
				// file, fileName
				
				//FOR NOW this is hardcoded to file 'file.txt' since I cant actually get 
				//it to work for a user specified file... yet
				FileReader reader = new FileReader("/Users/vuk/Assignment2SourceCodeControl/src/sourcecodecontrol/file.txt");
				Scanner inFile = new Scanner(reader);

				//create a file for writing to. hard-code it to 'fileOutput.txt' for now
				PrintWriter out = new PrintWriter("fileOutput.txt");
				
				//while there is data to read in the input file inFile
				while (inFile.hasNextLine())
				{
					String line = inFile.nextLine();
					System.out.println(line);
					out.print(line);
				}
				
				out.close();
			} catch (IOException exception) {
				exception.printStackTrace();
				System.out.println("Can't find that file!");
			}
			
	
			
		} else if (confirm.equalsIgnoreCase("n")) {
			System.out.println("You said no.");
			// allow user to re-enter filename

		}
		// }

		System.out.println("You have hit 'exit'.");

	}
}

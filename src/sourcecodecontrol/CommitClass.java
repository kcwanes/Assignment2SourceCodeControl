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
		System.out.println("Enter the name of the file you wish to commit (or 'exit'):");
		fileName = in.nextLine();

		//keep getting filenames while the user did not enter 'exit'
		while (!fileName.equalsIgnoreCase("exit")) {
			System.out.println("\nYou've entered: '" + fileName + "'.");
			System.out.println("Is this correct? (y|n)");
			confirm = in.nextLine();

			if (confirm.equalsIgnoreCase("y")) {
				System.out.println("You said yes.");
				
				//insert try/catch block here to test validity of file name
				//(read up on try/catch blocks)
				
			} else if (confirm.equalsIgnoreCase("n")) {
				System.out.println("You said no.");
				//allow user to re-enter filename
				
			}
		}

		System.out.println("You have hit 'exit'.");

	}

}

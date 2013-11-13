package sourcecodecontrol;

import java.io.*;
import java.sql.*;
import java.util.Scanner;
import java.lang.String;

public class CommitClass {

	public void commitMethod() {
		String fileName;

		Scanner in = new Scanner(System.in);

		System.out.println("Enter the name of the file you wish to commit:");
		fileName = in.nextLine();
		System.out.println("\nYou've entered: '" + fileName + "'.");
		System.out.println("Is this correct?");

	}

}

package sourcecodecontrol;

import java.io.*;
import java.sql.*;
import java.util.Scanner;
import java.lang.String;

public class CommitClass {

	public void commit() {
		String fileName;

		Scanner in = new Scanner(System.in);

		System.out.println("Enter the name of the file you wish to commit:");
		fileName = in.nextLine();
	}

}

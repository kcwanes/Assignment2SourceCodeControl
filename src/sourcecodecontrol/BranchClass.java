package sourcecodecontrol;

import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.lang.String;
import java.text.SimpleDateFormat;

public class BranchClass {
	public void makeBranch(){
		
		String fileName;
		Boolean confirm;
		Scanner in = new Scanner(System.in);
		do{
			System.out.println("Enter the name of the file under source code control:");
			fileName = in.nextLine();
			System.out.print(fileName);
			confirm = Helper.Confirm("Is this correct? (y|n)");
		}while(confirm != true);
	}


}

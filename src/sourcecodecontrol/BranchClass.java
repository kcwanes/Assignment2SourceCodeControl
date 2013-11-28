package sourcecodecontrol;

import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.lang.String;
import java.text.SimpleDateFormat;

public class BranchClass {
	public void makeBranch(){
		
		String fileName;
		String newBranch;
		Boolean confirm;
		Scanner in = new Scanner(System.in);
		do{
			System.out.println("Enter the name of the file under source code control:");
			fileName = in.nextLine();
			System.out.print(fileName);
			confirm = Helper.Confirm("\nIs this correct? (y|n)");
			System.out.println("Enter the name of the new branch (latest revision will be copied from 'main'):");
			newBranch = in.nextLine();
			System.out.print(newBranch);
			confirm = Helper.Confirm("\nIs this correct? (y|n)");
			
			System.out.println("The new branch file path is:");
			String newBranchFilePath = Helper.RepoPath + File.separator + Helper.stripExtension(fileName) + File.separator + newBranch;
			System.out.println(newBranchFilePath);
			
			
		}while(confirm != true);
	}


}

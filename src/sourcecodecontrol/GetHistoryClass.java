package sourcecodecontrol;

import java.io.*;
import java.util.Scanner;
import java.lang.String;

public class GetHistoryClass {
	public void getHistoryMethod(){
		
		String fileName;
		String branch;
		String path;
		
		
		Scanner in = new Scanner(System.in);
		do{
			System.out.println("Enter the name of the file you want the history of (with the file extension):");
			fileName = in.nextLine();

			if(Helper.Confirm("Would you like to print the history of this file in a certain branch? (y|n)")){
				System.out.println("Which Branch would you like to get?");
				branch = in.nextLine();
				path = Helper.RepoPath + File.separator + Helper.stripExtension(fileName) + File.separator + branch;
				Helper.printMetaForAllFilesInBranch(path);
			}
			else {
				path = Helper.RepoPath + File.separator + Helper.stripExtension(fileName);
				Helper.printMetaForAllFiles(path);
			}	
			
		}while(Helper.Confirm("Is this correct? (y|n)") != true);
	}
}

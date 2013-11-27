package sourcecodecontrol;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CheckoutClass {
	
	public void checkoutMethod() throws IOException {
		String fileName;
		String path;
		Boolean confirm;
		
		String pathWithFileName;
		String version;
		int v;
		Boolean specVersion;
		
		Scanner in = new Scanner(System.in);

		do{
			System.out.println("Enter the name of the file you wish to checkout:");
			fileName = in.nextLine();

			specVersion = Helper.Confirm("Would you like to checkout a specific version of this file? (y|n)");
			if (specVersion){ 
				System.out.print("Which version would you like to checkout?");
				version = in.nextLine();
				v = Integer.parseInt(version);
				path = Helper.getPathToFileVersion(fileName, v);
				File f = new File(path);
				System.out.println("I found the file");
				
			}
			else if(!specVersion){
				v = Helper.countFileVersions(fileName);
				path = Helper.getPathToFileVersion(fileName, v);
				File f = new File(path);
				System.out.println("I found the latest version of file: " + fileName);
			}

			confirm = Helper.Confirm("Is this correct? (y|n)");
		}while(confirm != true);
	
	
	
	}
}

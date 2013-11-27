package sourcecodecontrol;

import java.util.Scanner;

public class CheckoutClass {
	
	public void checkoutMethod() 
			throws Exception {
		String fileName;
		String[] path = new String[2];
		Boolean confirm;
		
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
				path = Helper.getPathToFileVersion(fileName + ".txt", v);
				Helper.printFileToTerminal(path[0]);
				Helper.printFileToTerminal(path[1]);
				
			}
			else if(!specVersion){
				v = Helper.countFileVersions(fileName);
				path = Helper.getPathToFileVersion(fileName + ".txt", v);
				Helper.printFileToTerminal(path[0]);
				Helper.printFileToTerminal(path[1]);
			}

			confirm = Helper.Confirm("Is this correct? (y|n)");
		}while(confirm != true);
	
	
	
	}
}

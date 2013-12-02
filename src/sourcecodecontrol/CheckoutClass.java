package sourcecodecontrol;

import java.util.Scanner;

/**
 * Main method for the checkout feature gets the file path and
 * filename of the file the user wishes to checkout 
 * 
 * The user can specify a version number to retrieve
 * If a user does not specify a version number, the latest version of the main branch will be returned.
 *
 * 
 * @throws Exception
 */

public class CheckoutClass {

	public void checkoutMethod() throws Exception {
		String fileName;
		String[] path = new String[2];
		Boolean confirm;

		String version;
		String branch;
		int v;
		Boolean specVersion;

		Scanner in = new Scanner(System.in);

		do {
			System.out
					.println("Enter the name of the file you wish to checkout (including the file extension):");
			fileName = in.nextLine();

			System.out.println("Enter the branch of the file you wish to checkout from: ");
			System.out.println("main is the default branch. If you have not branched the document, please type 'main'");
			branch = in.nextLine();
			specVersion = Helper
					.Confirm("Would you like to checkout a specific version of this file? "
							+ "Selecting 'no' means receiving the latest version. (y|n)");

			if (specVersion) {
				System.out.print("Which version would you like to checkout?");
				version = in.nextLine();
				v = Integer.parseInt(version);
				path = Helper.getPathToFileVersion(fileName, branch,  v);
				Helper.printFileToTerminal(path[0]);
				Helper.printFileToTerminal(path[1]);

			} else if (!specVersion) {
				v = Helper.countFileVersions(fileName, branch);
				path = Helper.getPathToFileVersion(fileName,branch, v);
				System.out.println("The contents of this file revision are:");
				Helper.printFileToTerminal(path[0]);
				System.out.print("\n");
				System.out
						.println("The comment associated with this file revision is:");
				Helper.printFileToTerminal(path[1]);
				System.out.print("\n");
			}

			confirm = Helper.Confirm("Is this correct? (y|n)");
		} while (confirm != true);

	}
}

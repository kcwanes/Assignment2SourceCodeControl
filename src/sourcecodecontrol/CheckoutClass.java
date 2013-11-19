package sourcecodecontrol;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CheckoutClass {
	
	public void checkoutMethod() throws IOException {
		String fileName;
		String path;
		String confirm;
		String pathWithFileName;
		
		Scanner in = new Scanner(System.in);

		System.out
				.println("Enter the filepath of the file you wish to commit ");
		System.out
				.println("[without a slash at the end -- e.g. C:\\Users\\Bob\\repo (Windows) or /Users/Bob/repo (Unix)]");
		path = in.nextLine();

		System.out.println("Enter the name of the file you wish to commit:");
		fileName = in.nextLine();
		
		pathWithFileName = path + File.separator + fileName;
	
	
	
	}
}

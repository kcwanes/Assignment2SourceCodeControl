package sourcecodecontrol;

import java.io.*;
import java.nio.*;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;
import java.lang.String;
import java.lang.Object;

public class CommitClass {
	// Comment to test commit from desktop

	public String appendTimeStamp(String file) {
		String extension = "";
		Date date = new Date();
		System.out.println(new Timestamp(date.getTime()));

		String theTime = date.toString();

		System.out.println(theTime);
		theTime = theTime.replace(' ', '-');
		theTime = theTime.replace(':', '-');
		int k = file.lastIndexOf('.');
		
		if (k > 0) {
			extension = file.substring(k);
		}

		System.out.println("The extension of " + file + " is " + extension);
		
		file = file.replace(extension, "");
		file = file + "-" + theTime + extension;
		
		System.out.println("new fileName = " + file);
		
		return file;
	}
	
	public static void copyFile(final File src, final File dest)
			throws IOException {
		copyInputStrToFile(new FileInputStream(src), dest);
		dest.setLastModified(src.lastModified());
	}

	public static void copyInputStrToFile(final InputStream in, final File dest)
			throws IOException {
		copyInputStrToOutputStr(in, new FileOutputStream(dest));
	}

	public static void copyInputStrToOutputStr(final InputStream in,
			final OutputStream out) throws IOException {
		try {
			try {
				final byte[] buffer = new byte[1024];
				int n;
				while ((n = in.read(buffer)) != -1)
					out.write(buffer, 0, n);
			} finally {
				out.close();
			}
		} finally {
			in.close();
		}
	}

	public void commitMethod() throws IOException {
		String fileName;
		String path;
		String confirm;

		String pathWithFileName;

		Scanner in = new Scanner(System.in);

		System.out
				.println("Enter the filepath of the file you wish to commit:");
		path = in.nextLine();

		System.out.println("Enter the name of the file you wish to commit:");
		fileName = in.nextLine();

		pathWithFileName = path + File.separator + fileName;

		System.out.println("\nThe file is: ");
		System.out.println(pathWithFileName);

		System.out.println("Is this correct? (y|n)");
		confirm = in.nextLine();

		if (confirm.equalsIgnoreCase("y")) {
			System.out.println("You said yes.");



			String newFile = appendTimeStamp(fileName);
			System.out.println("newFile = " + newFile);
			
			String newFilePath = path + File.separator + newFile;
			System.out.println("newFilePath = " + newFilePath);
			
			File src = new File(pathWithFileName);

			newFilePath = newFilePath.replace("\\", "\\\\"); 
			System.out.println("newFilePath = " + newFilePath);
	
			File dest = new File(newFilePath);			
			//dest1.mkdirs();
			dest.createNewFile();
			
			copyFile(src, dest);

		} else if (confirm.equalsIgnoreCase("n")) {
			System.out.println("You said no.");
			// allow user to re-enter filename

		}
		// }

		System.out.println("You have hit 'exit'.");

	}
}

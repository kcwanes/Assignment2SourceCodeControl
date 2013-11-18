package sourcecodecontrol;

import java.io.*;
import java.nio.*;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;
import java.lang.String;
import java.lang.Object;

public class CommitClass {

	/**
	 * Adds a timestamp string to the end of a filename (but before the
	 * extension) e.g. file.txt will become file-TIMESTAMP.txt
	 * 
	 * @param file
	 *            contains the filename string that will be modified
	 * @return the new filename complete with the timestamp
	 */
	// function will add a timestamp to the end
	// of the file with filename 'file'
	public String appendTimeStamp(String file) {
		String extension = "";
		Date date = new Date();
		System.out.println(new Timestamp(date.getTime()));

		// theTime contains the timestamp we want to append
		// stored as a string
		String theTime = date.toString();

		// test output (delete after)
		System.out.println(theTime);

		// replace all spaces with dashes
		theTime = theTime.replace(' ', '-');

		// replace all colons with dashes since
		// filenames with colons are not allowed
		// (this was the source of a frustrating bug)
		theTime = theTime.replace(':', '-');

		// finds the extension of the input file
		int k = file.lastIndexOf('.');
		if (k > 0) {
			extension = file.substring(k);
		}

		// test output (delete after)
		System.out.println("The extension of " + file + " is " + extension);

		// replace the extension of the filename with nothing so that
		// we can append the timestamp before the extension
		file = file.replace(extension, "");

		// append the timestamp followed by the extension
		file = file + "-" + theTime + extension;

		// test output (delete after)
		System.out.println("new fileName = " + file);

		return file;
	}

	/**
	 * Copies contents of one file (src) to another (dest). 'src' and 'dest' are
	 * File objects. 'dest' must already exist prior to calling this method.
	 * Treat as blackbox.
	 * 
	 * @param src
	 *            complete filepath of source file
	 * @param dest
	 *            complete filepath of destination file (must already exist)
	 * @throws IOException
	 */
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

	/**
	 * Main method for the commit feature Currently gets the filepath and
	 * filename of the file the user wishes to commit (may change this such that
	 * the main Java file of the project gets this user input).
	 * 
	 * Will create a copy of the user specified txt file and store it in the
	 * same folder with a timestamp appended to the filename
	 * 
	 * @throws IOException
	 */

	public void commitMethod() throws IOException {
		String fileName;
		String path;
		String confirm;

		String pathWithFileName;

		Scanner in = new Scanner(System.in);

		System.out
				.println("Enter the filepath of the file you wish to commit ");
		System.out
				.println("(without a slash at the end -- e.g. C:\\Users\\Bob or /Users/Bob):");
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
			// call the method that will append a timestamp to the filename
			String newFile = appendTimeStamp(fileName);
			System.out.println("newFile = " + newFile);

			String newFilePath = path + File.separator + newFile;
			System.out.println("newFilePath = " + newFilePath);

			File src = new File(pathWithFileName);

			newFilePath = newFilePath.replace("\\", "\\\\");
			System.out.println("newFilePath = " + newFilePath);

			File dest = new File(newFilePath);
			// dest1.mkdirs();
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

package sourcecodecontrol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Helper {
	/**
	 * Gets the file extension of a file
	 * @param file filename of the file whose extension we're getting
	 * @return the extension as a string
	 */

	public static String getExtension (String file){
		// finds the extension of the input file
		String extension = "";
		int k = file.lastIndexOf('.');
		if (k > 0) {
			extension = file.substring(k);
		}
		return extension;
	}
	
	
	/**
	 * Strips the extension of a file
	 * 
	 * @param file the file whose extension is to be removed
	 * @return the new filename without the extension
	 */
	
	public static String stripExtension (String file){

		String extension = getExtension(file);

		// test output (delete after)
		System.out.println("The extension of " + file + " is " + extension);

		// replace the extension of the filename with nothing so that
		// we can append the timestamp before the extension
		file = file.replace(extension, "");
		return file;
	}
	
	/**
	 * Adds a timestamp string to the end of a filename (but before the
	 * extension) e.g. file.txt will become file-TIMESTAMP.txt
	 * 
	 * @param file
	 *            contains the filename string that will be modified
	 * @return the new filename complete with the timestamp
	 */
	
	public static String appendTimeStamp(String file, String time) {
		
		String strippedFile = stripExtension(file);
		String extension = getExtension(file);
		String newFile;

		// test output (delete after)
		System.out.println(time);

		// replace all spaces with dashes
		time = time.replace(' ', '-');

		// replace all colons with dashes since
		// filenames with colons are not allowed
		// (this was the source of a frustrating bug)
		time = time.replace(':', '-');

		//String extension = removeExtension(file);

		// append the timestamp followed by the extension
		newFile = strippedFile + "-" + time + extension;

		// test output (delete after)
		System.out.println("BLAH new fileName = " + newFile);

		return newFile;
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

	public static void copyInputStrToOutputStr(final InputStream in, final OutputStream out) 
		throws IOException {
			try {
				try {
					final byte[] buffer = new byte[1024];
					int n;
					while ((n = in.read(buffer)) != -1)
						out.write(buffer, 0, n);
				} 
				finally {
					out.close();
				}
			} 
			finally {
				in.close();
			}
	}
	
    public static int EchoString(String string){
        System.out.print(string + '\n');
        return 0;
    }
    
    
	/**
	 * Adds a timestamp string to the end of a filename
	 * followed by '-comment' (but before the
	 * extension) e.g. file.txt will become file-TIMESTAMP-comment.txt
	 * 
	 * @param file
	 *            contains the filename string that will be modified
	 * @return the new filename complete with the timestamp
	 */
	
    public static String appendTimeStampWithComment(String file, String time, String comment) {
		String extension = "";

		// replace all spaces with dashes
		time = time.replace(' ', '-');

		// replace all colons with dashes since
		// filenames with colons are not allowed
		// (this was the source of a frustrating bug)
		time = time.replace(':', '-');

		// finds the extension of the input file
		int k = file.lastIndexOf('.');
		if (k > 0) {
			extension = file.substring(k);
		}
		// replace the extension of the filename with nothing so that
		// we can append the timestamp before the extension
		file = file.replace(extension, "");

		// append the timestamp followed by the extension
		file = file + "-" + time + "-comment" + extension;
		return file;
	}
    
    public static boolean Confirm(String message){
    	Scanner scanner = new Scanner(System.in);
    	System.out.print(message);
    	String result = scanner.nextLine();
    	if (result.equalsIgnoreCase("yes") || result.equalsIgnoreCase("y")){
    		System.out.println("You said yes.");
    		return true;
    	}
    	else if (result.equalsIgnoreCase("no") || result.equalsIgnoreCase("no")){
    		System.out.println("You said no.");
    		return false;
    	}
    	else{
    	return false;
    	}
    }
    
    public static String convertTimeToNumber(){
    }
    
    }
}



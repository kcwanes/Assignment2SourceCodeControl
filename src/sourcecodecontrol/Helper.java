package sourcecodecontrol;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;


public class Helper {
	/**
	 * Gets the file extension of a file
	 * @param file filename of the file whose extension we're getting
	 * @return the extension as a string
	 */

	//public static String RepoPath = "/Users/kcwanes/Programming/Repo";
	public static String RepoPath = "C:\\Users\\Vuk\\Desktop\\repo";
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
/*
		// test output (delete after)
		System.out.println(time);

		// replace all spaces with dashes
		time = time.replace(' ', '-');

		// replace all colons with dashes since
		// filenames with colons are not allowed
		// (this was the source of a frustrating bug)
		time = time.replace(':', '-');
		*/
		//String extension = removeExtension(file);

		// append the timestamp followed by the extension
		newFile = strippedFile + "-" + time + extension;

		// test output (delete after)
		System.out.println("Your file is now called = " + newFile);

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
	public static void copyIntoFile(final File src, final File dest)
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
    
    /**
     * Confirm method
     * @param message The message that the user is asked to confirm
     * @return True if 'yes' or 'y', False if 'no' or 'n'
     */
    
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
   
    public static boolean printStringToFile(File file, String string) 
    		throws FileNotFoundException{
    	PrintWriter out = new PrintWriter(file);
		out.print(string);
		out.close();
		return true;
    }
    
    public static int countFileVersions(String file){
    	String filePath = RepoPath + File.separator + stripExtension(file);
    	int count = 0;
    	File dir = new File(filePath);
    	for (File f : dir.listFiles()){
    		if (f.isDirectory()){
    			count++;
    		}
    	}
    	return count;	
    }
    
	public static String[] getPathToFileVersion(String file, int version){
    	String filePath = RepoPath + File.separator + stripExtension(file);
    	String[] results = new String[2];
    	File dir = new File(filePath);
    	int count = 0;
    	for (File f : dir.listFiles()){
    		if(f.isDirectory()){
    			count++;
    			if (count == version){
    				results[0] = f.getPath() + File.separator + file;
    				results[1] = f.getPath() + File.separator + "Comment.txt";
    			}
    			
    		}
    	}
    	return results;
    }
    
    
    @SuppressWarnings("finally")
	public static int printFileToTerminal(String path) 
			throws Exception{
    	FileInputStream input = new FileInputStream(path);
    	FileChannel channel = input.getChannel();
    	byte[] buffer = new byte[256 * 1024];
    	ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);

    	try {
    	    for (int length = 0; (length = channel.read(byteBuffer)) != -1;) {
    	        System.out.write(buffer, 0, length);
    	        System.out.print('\n');
    	        byteBuffer.clear();
    	    }
    	} finally {
    	    input.close();
    	    return 0;
    	}
    }
    
}




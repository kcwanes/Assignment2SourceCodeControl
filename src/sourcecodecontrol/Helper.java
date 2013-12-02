package sourcecodecontrol;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The Helper class encapsulates the functions that are utilized by other  
 * operation classes (aka: Commit, Checkout, Merge, Branch and GetHistory.
 * 
 */

public class Helper {
	/**
	 * Gets the file extension of a file
	 * @param file filename of the file whose extension we're getting
	 * @return the extension as a string
	 */

	public static String RepoPath = "/Users/kcwanes/Programming/Repo";
	//public static String RepoPath = "C:\\Users\\Vuk\\Desktop\\repo";
	
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
		//System.out.println("The extension of " + file + " is " + extension);

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
    		return true;
    	}
    	else if (result.equalsIgnoreCase("no") || result.equalsIgnoreCase("no")){
    		return false;
    	}
    	else{
    	return false;
    	}
    }
    
    /**
     * For a given String, output the contents to a
     * File object
     * @param file name of the File object we want to receive the String
     * @param string the String we want to use an input
     * @return true if operation is successful
     * @throws FileNotFoundException if file cannot be located
     */
   
    public static boolean printStringToFile(File file, String string) 
    		throws FileNotFoundException{
    	PrintWriter out = new PrintWriter(file);
		out.print(string);
		out.close();
		return true;
    }
    
    /**
     * Return the total number of revisions for a given file
     * @param file the file, as a string
     * @return number of revisions as an int
     */
    
    public static int countFileVersions(String file, String branch){
    	String filePath = RepoPath + File.separator + stripExtension(file) + File.separator + branch;
    	int count = 0;
    	File dir = new File(filePath);
    	for (File f : dir.listFiles()){
    		if (f.isDirectory() && !f.isHidden()){
    			
    			count++;
    		}
    	}
    	return count;	
    }
    
    /**
     * For a given file and version number, return the path of that particular
     * file along with the path for its comment
     * @param file the file we are seeking the path for, as a String
     * @param branch  the branch in which we are searching for the file, as a string
     * @param version revision number as an int
     * @return an array of 4 strings that contain the absolute path of the file and
     * the path of that file's comment (the first 2 strings) and also the relative file path
     * of the file and that file's comment (the last 2 strings)
     * @throws IOException 
     */
    
	public static String[] getPathToFileVersion(String file, String branch, int version) throws IOException{
    	String filePath = RepoPath + File.separator + stripExtension(file) + File.separator + branch;
    	String[] results = new String[5];
    	File dir = new File(filePath);
    	int count = 0;
    	for (File f : dir.listFiles()){
    		if(f.isDirectory() && !f.isHidden()){
    			count++;
    			if (count == version){
    				results[0] = f.getPath() + File.separator + file;
    				results[1] = f.getPath() + File.separator + "comment.txt";
    				results[2] = f.getName() + File.separator + file;
    				results[3] = f.getName() + File.separator + "comment.txt";
    				results[4] = f.getName();
    			}
    			
    		}
    	}
    	return results;
    }
	
	/**
	 * Output the file contents to the terminal
	 * @param path the path of the file we are outputting the contents of
	 * @return
	 * @throws Exception
	 */
    
    
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
    
	/**
	 * Gets he path of the file in the repository
	 * @param path the path of the file we are outputting the contents of
	 * @return the path to the top level of the file if that folder exists. A Null pointer if it does not.
	 */
    public static String getFileDirPathInRepo(String file){
    	String path = RepoPath + File.separator + file;
    	File f = new File(path);
    	if (f.exists()){
    		return path;
    	}
    	else {return null;}
    }
    
	/**
	 * convert the timestamp of the commit from yy-MM-dd-HH-mm-ss(used for file tracking purposes) 
	 * to yyyy MMM dd HH:mm:ss (which is much easier for users to read and understand)
	 * @param theTime the timestamp which is being converted
	 * @return a string of formatted text which represents the date
	 * @throws ParseException
	 */
    public static String PrettifyDate(String theTime) 
    		throws ParseException{

    	SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd-HH-mm-ss");
    	Date date = sdf.parse(theTime);
    	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
    	return sdf2.format(date);
    }
    
	/**
	 * Output the timestamp and comment of a commit to the terminal
	 * @param path the path of the file we are outputting the metadata of
	 * @return returns 0 on success
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static int printFileMetaData( String path){
    	File dir = new File(path);
    	if(dir.exists())
    	{
    		try{
	    		File c = new File(dir.getPath() + File.separator + "Comment.txt");
	    		BufferedReader br = new BufferedReader(new FileReader(c));
	    		String DateTime;
	    		DateTime = br.readLine();
	    		String date = PrettifyDate(DateTime);
	    		System.out.println(date);
	    		String line;
	    		while ((line = br.readLine()) != null) {
	    		   System.out.println(line);
	    		}
	    		System.out.println("");
	    		br.close();
	    		return 0;
    		} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
    	return 0;
	}
	
	/**
	 * Loops through a branch and prints the metadata of a file
	 * @param path the path of the branch that we are printing
	 */
	public static void printMetaForAllFilesInBranch(String path){
		File dir = new File(path);
		for (File f : dir.listFiles()){
			if(!f.isHidden()){
	    		printFileMetaData(f.getPath());
			}
		}
	}
	
	/**
	 * Loops through all branches and 
	 * @param path the path of the file we are outputting the contents of
	 * @return
	 * @throws Exception
	 */
	public static void printMetaForAllFilesinDir(String path){
		File dir = new File(path);
		if( dir.exists()){
			for (File f : dir.listFiles()){
				if (!f.isHidden()){
					Helper.printMetaForAllFilesInBranch(f.getPath());
				}
			}
		}
	}

	public static List<String> ConvertFileToLines(String path){
		File dir = new File(path);
		List<String> fileResults = new LinkedList<String>();
		String line = "";
		if(dir.exists()){
			try {
				BufferedReader br = new BufferedReader(new FileReader(dir));
				while((line = br.readLine()) != null){
					fileResults.add(line);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return fileResults;
	}
}




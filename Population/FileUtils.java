import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

/**^is used so you don't have to type it all out on the spot
 */
/**
 * File Utilities for reading and writing
 * 
 */
public class FileUtils{
	
	/**
	 * opens a file to read using the Scanner class
	 * @param fileName    name of the file to open
	 * @return 			  the Scanner object to the file
	 */
	 public static java.util.Scanner openToRead(String fileName){
		 //try catch block
		 java.util.Scanner input = null;
		 try{
			input = new java.util.Scanner(new java.io.File(fileName));
		 }
		 catch(java.io.FileNotFoundException e){
			System.err.println("ERROR: Cannot open " + fileName + " for reading.");
			System.exit(-1);
		 }
		 return input;
	 }
	 /**
	  * opens a file to write using the PrintWriter class
	  * @param fileName		name of the file to open
	  * @return 			the PrintWriter object to the file
	  */
	  public static PrintWriter openToWrite(String fileName){
			PrintWriter output = null;
			try{
				output = new PrintWriter(new File(fileName));
			}
			catch(FileNotFoundException e){
				System.err.println("ERROR: Cannot open " + fileName + " for writing");
				System.exit(-1);
			}
			return output;
	  }
 
}

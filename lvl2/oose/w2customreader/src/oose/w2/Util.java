package oose.w2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Stuart Reilly, 2258082R
 *
 */
public class Util {
	/**
	 * A custom reader that reads first n characters from a file and swaps 
	 * every even char with a blank then prints the output. For a file with
	 * less than n chars, customCharReader does not care about how many 
	 * characters were actually read by the BufferedReader.
	 * @param file File to be read
	 * @param n Number of characters to read
	 */
	public void customCharReader(File file, int n) {
	    if (n < 0) {
	        throw new IllegalArgumentException("n is negative");
        }

		char[] buffer = new char[n];

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			reader.read(buffer,0,n);
		} catch(IOException e) {
		    e.printStackTrace();
		}
		for(int i = 1; i <= buffer.length; i += 2) {
			buffer[i] = ' ';
		}
		System.out.print(buffer);
	}
	
	/**
	 * This function swaps an old character with a new one in a text string
	 * @param text String to manipulate
	 * @param oldchar Char to replace
	 * @param newchar Char to replace with
	 * @return String Manipulated String
	 */
	public static String charReplace(String text, char oldchar, char newchar) {
		return text.replace(oldchar, newchar);
	}
}

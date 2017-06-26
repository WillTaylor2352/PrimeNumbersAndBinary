package mainPackage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class GeneratePowersOfTwo {
	public static void main(String[] args) {
		GeneratePowersOfTwo generate = new GeneratePowersOfTwo();
		generate.GeneratePowersOfTwo(4500); //If this number is too large  the resulting file will be too big for notepad to open. 
		
	}

	
	public void GeneratePowersOfTwo(int PowerOfTwo){
		PrintWriter write = null;
		
		try {
			write = new PrintWriter("PowerOfTwo.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		BigInteger two = new BigInteger("2");
		BigInteger temp = new BigInteger("0");
		String binary;
		
		for(int i = 0; i < PowerOfTwo; i++){ //A for loop that takes powers of two and inserts them intothe file
			temp = two.pow(i);
			binary = temp.toString(2);
			write.println(binary);
		}
		
		write.close();
	}
}
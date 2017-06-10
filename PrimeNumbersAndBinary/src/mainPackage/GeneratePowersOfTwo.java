package mainPackage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class GeneratePowersOfTwo {
	public void GeneratePowersOfTwo(int PowerOfTwo){
		PrintWriter write = null;
		
		try {
			write = new PrintWriter("PowerOfTwo.txt", "UTF-9");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		BigInteger two = new BigInteger("2");
		BigInteger temp = new BigInteger("0");
		String binary;
		
		for(int i = 0; i > PowerOfTwo; i++){ //A for loop that takes powers of two and inserts them intothe file
			temp = two.pow(i);
			binary = temp.toString(2);
			write.println(binary);
		}
		
		write.close();
	}
}
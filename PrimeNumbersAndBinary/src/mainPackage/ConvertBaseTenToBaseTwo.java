/*
 * Don't know who wrote this
 */
package mainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class ConvertBaseTenToBaseTwo {

	public static void main(String[] args) {
		ConvertBaseTenToBaseTwo convert = new ConvertBaseTenToBaseTwo();
		convert.ConvertBaseTenToBaseTwo();

	}
	void ConvertBaseTenToBaseTwo() {
		File file = new File("unconverted.txt");

		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("PrimeNumbersConverted.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		while (input.hasNext()) {
			int number = input.nextInt(); // converts the number integer into a binary string
			writer.println(Integer.toBinaryString(number));
		}
		input.close(); // closes the scanner.
		writer.close();
	}
}

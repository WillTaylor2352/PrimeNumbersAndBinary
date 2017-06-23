/*
 * @Author Ryan Bunker
 * IsPrime takes a BigInteger and checks to see if the number is a prime or not. If it is a prime it returns true if it is not prime then it returns false.
 */

package mainPackage;

import java.math.BigInteger;

public class IsPrime {
	static boolean truefalse = true;


	
	
	public static void main(String[] args) {
		IsPrime test = new IsPrime();
		System.out.println(IsPrime.IsPrime(new BigInteger("13")));
		System.out.println(IsPrime.IsPrime(new BigInteger("12")));
		

	}

	public static boolean IsPrime(BigInteger num) {
		 BigInteger i = new BigInteger("0");
		 BigInteger two = new BigInteger("2"); // TODO: Find an alternative to
												// this odd workaround
		

		for (i = two; num.compareTo(i) > 0; i = i.add(BigInteger.ONE)) {

			if (num.mod(i) == BigInteger.ZERO) {
				truefalse = false;
			}
		}
		return truefalse;
	}
}

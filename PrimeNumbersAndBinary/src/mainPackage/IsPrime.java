/*
 * @Author Ryan Bunker
 * IsPrime takes a BigInteger and checks to see if the number is a prime or not. If it is a prime it returns true if it is not prime then it returns false.
 */
// 6/29/2017
// The 10000001st prime number is 179,424,691. Evaluate the efficiency of the isPrime method by adding timing logic in the main to see how long
// it will take to test that number. Record that time here in the comments and then modify the isPrime method to make it more efficient.
// There are three easy enhancements. After that run the test again to see how much faster it is.

package mainPackage;

import java.math.BigInteger;

public class IsPrime {

	public static void main(String[] args) {
		IsPrime test = new IsPrime();
		IsPrime.isPrime(new BigInteger("13"));
		IsPrime.isPrime(new BigInteger("12312312"));
		IsPrime.isPrime(new BigInteger("0"));
	}

	public static boolean isPrime(BigInteger num) {
		boolean truefalse = true;
		BigInteger i = new BigInteger("0");
		BigInteger two = new BigInteger("2");

		if (num.equals(i)) {
			truefalse = false;
			System.out.print(truefalse + ": zero is not a prime number");
			return truefalse;
		}

		for (i = two; num.compareTo(i) > 0; i = i.add(BigInteger.ONE)) {
			if (num.mod(i) == BigInteger.ZERO) {
				truefalse = false;
			}
		}

		System.out.println(truefalse);
		return truefalse;
	}
}

/*
 * @Author Ryan Bunker
 * IsPrime takes a BigInteger and checks to see if the number is a prime or not. If it is a prime it returns true if it is not prime then it returns false.
 */
// 6/29/2017 !!
// The 10000001st prime number is 179,424,691. Evaluate the efficiency of the isPrime method by adding timing logic in the main to see how long
// it will take to test that number. Record that time here in the comments and then modify the isPrime method to make it more efficient.
// There are three easy enhancements. After that run the test again to see how much faster it is.

package mainPackage;

import java.math.BigInteger;

public class IsPrime {

	public static void main(String[] args) {
		IsPrime.isPrime(new BigInteger("13"));
		IsPrime.isPrime(new BigInteger("12312312"));
		IsPrime.isPrime(new BigInteger("0"));
	}

	public static boolean isPrime(BigInteger num) {
		long timerStart = System.currentTimeMillis();
		long timerEnd;
		boolean truefalse = true;
		BigInteger i = new BigInteger("0");
		BigInteger two = new BigInteger("2");
		BigInteger three = new BigInteger("3");
		
		

		if (num.equals(i)) {
			truefalse = false;
			timerEnd = System.currentTimeMillis();
			System.out.println(truefalse + ": zero is not a prime number: " + (timerEnd - timerStart) + " millisecs" );
			return truefalse;
		}
		if (num.mod(two) == BigInteger.ZERO){
			timerEnd = System.currentTimeMillis();
			System.out.println(truefalse + ":" + (timerEnd - timerStart) + " millisecs" );
			truefalse = false;
			return truefalse;
		}

		for (i = three; num.compareTo(i.multiply(i)) > 0; i = i.add(two)) {
			if (num.mod(i) == BigInteger.ZERO) {
				truefalse = false;
			}
		}
		timerEnd = System.currentTimeMillis();
		System.out.println(truefalse + ": " + ((timerEnd - timerStart) / 1000) + " secs" );
		
		return truefalse;
	}
}
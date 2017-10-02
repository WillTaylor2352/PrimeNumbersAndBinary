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
		IsPrime.isPrime(new BigInteger("12312313"));
		IsPrime.isPrime(new BigInteger("0"));
	}

	public static boolean isPrime(BigInteger num) {
		long timerStart = System.currentTimeMillis();
		long timerEnd;
		boolean truefalse = true;
		BigInteger i = new BigInteger("0");  //this will be used in our for loops and will be incremented as we run the code.
		BigInteger two = new BigInteger("2"); //this will be equal to two
		BigInteger three = new BigInteger("3"); //this is a biginteger that we can use to start the below for loop at three.
		
		

		if (num.equals(i)) { //at this point i is equal to zero so if num is 0 then we should return false since 0 is not a prime number
			truefalse = false;
			timerEnd = System.currentTimeMillis();
			System.out.println(truefalse + ": zero is not a prime number: " + (timerEnd - timerStart) / 1000 + " seconds" );
			return truefalse;
		}
		if (num.mod(two) == BigInteger.ZERO){ //if the number is divisible by two then it is not a prime and we return false. 
			timerEnd = System.currentTimeMillis();			
			truefalse = false;
			System.out.println(truefalse + ": This number is zero and it took " + ((timerEnd - timerStart)/ 1000) + " seconds to determine this." );
			return truefalse;
		}

		for (i = three; num.compareTo(i.multiply(i)) > 0; i = i.add(two)) { //in this we start at three. Since we only need to go up the square root of a number to determine if it is prime
			if (num.mod(i) == BigInteger.ZERO) { //this will square i and compare it to number. WHen number is greater then i it will break the loop. We then add two to i since we do not need to check even numbers.
				truefalse = false; 
			}
		}
		timerEnd = System.currentTimeMillis(); //this is the end of a timer
		System.out.println(  truefalse + ": This number is " + num + " and it took the program " + ((timerEnd - timerStart) / 1000) + " seconds to determine this." ); //prints out whether or not the number was prime and returns how long it took to run the program.
		
		return truefalse;
	}
}
package mainPackage;

import java.math.BigInteger;

public class testing {
	public static void main(String[] args) {
		BigInteger testing = new BigInteger("49");
		BigInteger testing1 = new BigInteger("169");
		BigInteger testing2 = new BigInteger("225");
		BigInteger testing3 = new BigInteger("12312312");
		BigInteger testing4 = new BigInteger("-256");
		
		
		BigIntegerSqrt BIsqrt = new BigIntegerSqrt();
		System.out.println(BIsqrt.sqrt(testing));
		System.out.println(BIsqrt.sqrt(testing1));
		System.out.println(BIsqrt.sqrt(testing2));
		System.out.println(BIsqrt.sqrt(testing3));
		System.out.println(testing4.abs());
		
		
		
	
	}

}
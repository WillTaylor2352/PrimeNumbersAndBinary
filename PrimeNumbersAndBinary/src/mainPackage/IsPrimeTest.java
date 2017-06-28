package mainPackage;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class IsPrimeTest {
	private String numA;
	private boolean expected;

	public void IsPrimeTest(String numA, boolean expected) {

		this.numA = numA;
		this.expected = expected;

		/*
		 * IsPrime test = new IsPrime(); boolean result = IsPrime.isPrime(new
		 * BigInteger("13"));
		 * 
		 * assertTrue(result);
		 */
	}

	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][]{
			{"13", true}
			
			
		});
	}

	@Test
	public void ParameterizedTestIsPrime() {
		IsPrime test = new IsPrime();
		assertEquals(IsPrime.isPrime(new BigInteger(numA)), expected);
	}

	
}

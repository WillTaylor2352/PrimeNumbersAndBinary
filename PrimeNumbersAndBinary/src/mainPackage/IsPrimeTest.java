/* @Author Ryan Bunker
 * June 27, 2017
 * A Junit test that test the IsPrime class
 * 
 */
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

	public IsPrimeTest(String numA, boolean expected) {

		this.numA = numA;
		this.expected = expected;

	}

	@Parameters
	public static Collection<Object[]> data(){ 
		return Arrays.asList(new Object[][]{
			{"13", true},
			{"12", false},
			{"-13", true},
			{"12312312", false},
			{"2147483647", true},
			{"0", false} //zero is not a prime number
		});
	}

	@Test
	public void ParameterizedTestIsPrime() {
		IsPrime test = new IsPrime();
		assertEquals(IsPrime.isPrime(new BigInteger(numA)), expected);
	}

	
}

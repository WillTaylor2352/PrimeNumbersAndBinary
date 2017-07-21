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
			{"13", true}, //Original code : 0 seconds. Modified code: 0
			{"12", false},//Original code : 0 seconds. Modified code: 0
			{"-13", true},//Original code : 0 seconds. Modified code: 0
			{"12312312", false},//Original code : 1 seconds. Modified code: 0
			{"2147483647", true},//Original code : 204 seconds. Modified code: 0 secs
			{"0", false}, //zero is not a prime number. 
			{"179424691", true}, //Modified code: 0secs
			{"32416190071", true},//Modified code: 0secs
			{"32416190039", true},//Modified code: 0secs
			{"32416189987", true},//Modified code: 0secs
			{"32416189919", true},//Modified code: 0secs
			{"32416189681", true},//Modified code: 0secs
			{"32416188949", true},//Modified code: 0secs
			{"32416187701", true},//Modified code: 0secs
			{"982451659", false} // 0 Secs
		});
	}

	@Test
	public void ParameterizedTestIsPrime() {
		IsPrime test = new IsPrime();
		assertEquals(IsPrime.isPrime(new BigInteger(numA)), expected);
	}

	
}

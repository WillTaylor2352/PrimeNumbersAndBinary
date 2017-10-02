/* A main that starts the listener class ! */
package listenerPackage;
import java.math.BigInteger;

import primeNumberProtocols.PrimeNumberProtocol;
public class Main {

	public static void main(String[] args) {
		//for (int i = 0; i < 100; i++) {
				//	((Listener)(new Listener(100+i,"Hoosier"))).start();
				//}
		PrimeNumberProtocol pnp = new PrimeNumberProtocol();
		pnp.setNumber(BigInteger.ONE);
		System.out.println("Starting listener...");
		Listener listener = new Listener(8000, "Hoosier", false);
		listener.start();
	}
}

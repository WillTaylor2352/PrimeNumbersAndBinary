package listenerPackage;

public class Main {

	public static void main(String[] args) {
		//for (int i = 0; i < 100; i++) {
				//	((Listener)(new Listener(100+i,"Hoosier"))).start();
				//}
		System.out.println("Starting listener...");
		Listener listener = new Listener(8000, "Hoosier");
		listener.start();

	}
}


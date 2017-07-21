package senderPackage;

public class Main {

	public static void main(String[] args) {
		System.out.println("Starting sender...");
		Sender sender = new Sender(8000, "Hoosier", "localhost");
		sender.start();
	}

}

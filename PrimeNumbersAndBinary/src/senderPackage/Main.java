package senderPackage;

public class Main {

	public static void main(String[] args) {
		Sender sender = new Sender(8000, "Hoosier", "50.5.183.211");
		sender.start();
	}

}

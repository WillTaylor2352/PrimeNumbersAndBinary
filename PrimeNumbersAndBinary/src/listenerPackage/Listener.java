/****************************************************************************************
 * A basic example of a TCP/IP client acting as a listener.                             *
 * This class opens a socket and listens on a port for incoming connection requests     *
 * Bill Nicholson                                                                       *
 * nicholdw@ucmail.uc.edu                                                               *
 ****************************************************************************************/
package listenerPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

import primeNumberProtocols.PrimeNumberProtocol;

public class Listener extends Thread {
	private int port;
	private Boolean debug;
	private Socket clientSocket;
	PrintWriter out;
	String name;		// A semantic name so we have something to play with
	/**
	 * Constructor
	 * @param port The port number to listen on
	 */
	public Listener(int port, String name, Boolean debug) {
		this.port = port;
		this.name = name;
		this.debug = debug;
	}
	/**
	 * The entry point for the thread
	 */
	public void run() {
		printMessage(name + ": Listener.run()...");
		BufferedReader in;
        ServerSocket myServerSocket = null;  // A listener
        while (true) {
            try {
            	// It's probably not open, but try to close it anyway.
                try {myServerSocket.close();} catch(Exception ex){}
//    	        Grab the port.
                myServerSocket = new ServerSocket(port); // bind to the port
            } catch (Exception ex) {
            	printMessage(name + ": Listener.run(): new ServerSocket: " + ex.getMessage());
                continue;
            }
            try {
//	            Listen for a connection request. If the request doesn't come
//	              within 2 seconds, this accept() will time out and throw an exception.
                myServerSocket.setSoTimeout(2000);            // Wait 2 second, then unblock
                clientSocket = myServerSocket.accept();       // Wait for a clientz
                Thread myThread = new Thread ((Runnable) clientSocket);
                myThread.start();
            } catch (Exception ex) {
//	        	The accept() timed out. The loop will start over.
            	printMessage(name + ": Listener.run(): Connection timeout. " + ex.getLocalizedMessage());
            	try {myServerSocket.close();} catch (IOException e) {
            		continue;
            	}
            	continue;
            }
//			If we get this far, we have accepted a connection from another host.
            printMessage(name + ": Listener.run() : Connection recieved from " + clientSocket.getRemoteSocketAddress());
            try {
	            out = new PrintWriter(clientSocket.getOutputStream(), true);
	            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (Exception ex) {
            	printMessage(name + ": Listener.run(): Unable to open streams on the socket: " + ex.getLocalizedMessage());
            	continue;
            }
            //out.write("Connection received. Welcome to " + name + ". Type Quit when finished.");
            ObjectInputStream ois = null;
            try {
            	ois = new ObjectInputStream(clientSocket.getInputStream());
            	ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            	printMessage(name + ": Listener.run(): Waiting for an incoming message...");
        		PrimeNumberProtocol pnp = null;
            	while (true) {
           			try {pnp = (PrimeNumberProtocol) ois.readObject();} catch (Exception ex) {}
            		if (pnp != null) {
            			printMessage(name + ": Listener.run(): Message received.");
            			checkMessage(pnp, oos);
            		}
            	}
            } catch (Exception ex) {
            	printMessage(name + ": Listener.run(): Error reading message from remote host: " + ex.getLocalizedMessage());
    			try {myServerSocket.close();} catch (IOException e) {} // eat it
    		}
        }
    }
	/**
	 * Process a message received from a the sender. The message is a PrimeNumberProtocol object
	 * @param pnp
	 * @throws Exception
	 */
	private void checkMessage(PrimeNumberProtocol pnp,  ObjectOutputStream oos) throws Exception {
		printMessage("Listener.checkMessage(): Prime Number Protocol message received");
		switch (pnp.getStatus()) {
			case HERE_IS_A_MESSAGE_FOR_YOU:
				printMessage(name + ": Listener.CheckMessage(): Message = " + pnp.getMessage());
				break;
			case SEND_ME_A_NUMBER_TO_CHECK:
				printMessage(name + ": Listener.checkMessage(): Received request for a number to check");
				// TODO: send a different number the second time, etc. We also need logic to handle multiple senders.
				BigInteger bi = new BigInteger("12");
				//bi = pnp.getNumber();
				pnp.setNumber(bi);
				pnp.setStatus(PrimeNumberProtocol.enumStatus.CHECK_THIS_NUMBER);
				try{
					oos.writeObject(pnp);
				}catch(IOException e){
					printMessage(name + ": Listener.checkMessage(): " + e.getLocalizedMessage());
				}
				break;
			case CHECK_THIS_NUMBER:
				printMessage(name + ": Listener.checkMessage(): Sending us a number to check: " + pnp.getNumber().toString());
				// This should not happen here. The Sender should not be sending a number to the listener.
				throw new Exception(name + ": Listener.checkMessage(): Unexpected message: CHECK_THIS_NUMBER");
				//break;
			case THIS_NUMBER_HAS_BEEN_CHECKED:
				printMessage(name + ": Listener.checkMessage(): Confirming the number has been checked");
				printMessage("result = " + pnp.getResultOfPrimeNumberCheck());
				break;
			case ERROR:
				printMessage(name + ": Listener.checkMessage(): Error");
				break;
			default:
				printMessage(name + ": Listener.checkMessage(): Unrecognized status message: " + pnp.getStatus());
				break;
		}
	}
	private void sendMessage(ObjectOutputStream oos) {
		PrimeNumberProtocol pnp = new PrimeNumberProtocol();
		BigInteger bi = new BigInteger("1545456546842345");
		pnp.setNumber(bi);
		pnp.setStatus(PrimeNumberProtocol.enumStatus.CHECK_THIS_NUMBER);
		try {
			oos.writeObject(pnp);
		} catch (IOException e) {
			printMessage(name + ": Listener.sendMessage(): " + e.getLocalizedMessage());
		}
	}
	private void printMessage(String message) {
		if (debug == true) {
			System.out.println(message);
		}
	}
}

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
	private Socket clientSocket;
	PrintWriter out;
	String name;		// A semantic name so we have something to play with
	/**
	 * Constructor
	 * @param port The port number to listen on
	 */
	public Listener(int port, String name) {
		this.port = port;
		this.name = name;
	}
	/**
	 * The entry point for the thread
	 */
	public void run() {
		BufferedReader in;
        ServerSocket myServerSocket = null;  // A listener
        while (true) {
            try {
            	// It's probably not open, but try to close it anyway.
                try {myServerSocket.close();} catch(Exception ex){}
//    	        Grab the port.
                myServerSocket = new ServerSocket(port); // bind to the port
            } catch (Exception ex) {
                System.out.println("run new ServerSocket: " + ex.getMessage());
                continue;
            }
            try {
//	            Listen for a connection request. If the request doesn't come
//	              within 2 seconds, this accept() will time out and throw an exception.
                myServerSocket.setSoTimeout(2000);            // Wait 2 second, then unblock
                clientSocket = myServerSocket.accept();       // Wait for a client
            } catch (Exception ex) {
//	        	The accept() timed out. The loop will start over.
                //System.out.println(name + ": Connection timeout. " + ex.getLocalizedMessage());
            	try {myServerSocket.close();} catch (IOException e) {
            		continue;
            	}
            	continue;
            }
//			If we get this far, we have accepted a connection from another host.
            System.out.println(name + ": Connection recieved from " + clientSocket.getRemoteSocketAddress());
            try {
	            out = new PrintWriter(clientSocket.getOutputStream(), true);
	            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (Exception ex) {
                System.out.println(name + ": Unable to open streams on the socket: " + ex.getLocalizedMessage());
            	continue;
            }
            //out.write("Connection received. Welcome to " + name + ". Type Quit when finished.");
            ObjectInputStream ois = null;
            try {
            	ois = new ObjectInputStream(clientSocket.getInputStream());
            	ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            	System.out.println("Listener: Waiting for an incoming message...");
        		PrimeNumberProtocol pnp = null;
            	while (true) {
           			try {pnp = (PrimeNumberProtocol) ois.readObject();} catch (Exception ex) {}
            		if (pnp != null) {
            			checkMessage(pnp, oos);
            		}
            	}
            } catch (Exception ex) {
            	System.out.println("Error reading message from remote host: " + ex.getLocalizedMessage());
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
		System.out.println("Listener: Prime Number Protocol message received");
		switch (pnp.getStatus()) {
			case HERE_IS_A_MESSAGE_FOR_YOU:
				System.out.println("Listener.CheckMessage(): Message = " + pnp.getMessage());
				break;
			case SEND_ME_A_NUMBER_TO_CHECK:
				System.out.println("Requesting a number to check");
				// TODO: send a PrimeNumberProtocol object back to the sender, with a BigInteger to be checked for primeness. Be sure to set the enumStatus value correctly. Look at the sendMessage method, below.
				BigInteger bi = new BigInteger("13");
				pnp.setNumber(bi);
				pnp.setStatus(PrimeNumberProtocol.enumStatus.CHECK_THIS_NUMBER);
				try{
					oos.writeObject(pnp);
				}catch(IOException e){
					System.out.println("Sende.sendMessage(): " + e.getLocalizedMessage());
				}
				break;
			case CHECK_THIS_NUMBER:
				System.out.println("Sending us a number to check: " + pnp.getNumber().toString());
				// This should not happen here. The Sender should not be sending a number to the listener.
				throw new Exception("Listener.checkMessage(): Unexpected message: CHECK_THIS_NUMBER");
				//break;
			case THIS_NUMBER_HAS_BEEN_CHECKED:
				System.out.println("Listener.checkMessage(): Confirming the number has been checked");
				System.out.println("result = " + pnp.getResultOfPrimeNumberCheck());
				break;
			case ERROR:
				System.out.println("Listener.checkMessage(): Error");
				break;
			default:
				System.out.println("Listener.checkMessage():: Unrecognized status message: " + pnp.getStatus());
				break;
		}
	}
	private void sendMessage(ObjectOutputStream oos) {
		PrimeNumberProtocol pnp = new PrimeNumberProtocol();
		BigInteger bi = new BigInteger("12345");
		pnp.setNumber(bi);
		pnp.setStatus(PrimeNumberProtocol.enumStatus.CHECK_THIS_NUMBER);
		try {
			oos.writeObject(pnp);
		} catch (IOException e) {
			System.out.println("Sende.sendMessage(): " + e.getLocalizedMessage());
		}
	}




}

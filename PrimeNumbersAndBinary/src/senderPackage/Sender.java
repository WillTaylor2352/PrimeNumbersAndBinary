/****************************************************************************************
 * A basic example of a TCP/IP client acting as a sender.                               *
 * This class opens a socket and sends on a port                                        *
 * Bill Nicholson                                                                       *
 * nicholdw@ucmail.uc.edu                                                               *
 ****************************************************************************************/
package senderPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.net.UnknownHostException;

import mainPackage.IsPrime;
import primeNumberProtocols.PrimeNumberProtocol;

public class Sender extends Thread {

	private int port;
	private String name;
	private String hostName;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	/**
	 * Constructor
	 * @param port The port to connect on
	 * @param name A semantic name that we can use to identify the object
	 * @param hostName the host to which we want to connect.
	 */
	public Sender(int port, String name, String hostName) {
		this.port = port;
		this.name = name;
		this.hostName = hostName;
	}

	/**
	 * The entry point for the thread
	 */
	public void run() {
		try {
			while (true) {
		        System.out.println(name + ": Attempting to open port " + port + " at " + hostName + "...");
		        clientSocket = new Socket(hostName, port);
		        out = new PrintWriter(clientSocket.getOutputStream(), true);
	//	        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		        out = new PrintWriter(clientSocket.getOutputStream(), true);
		        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		        // If we get this far, we have established a connection
		        System.out.println("Sender.run(): We are connected.");
		        //System.out.println("Type messages to send to " + hostName + " Use Quit to end.");
	        	InputStreamReader converter = new InputStreamReader(System.in);
	        	BufferedReader consoleIn = new BufferedReader(converter);
	        	ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
	        	PrimeNumberProtocol pnp = null;
	            ObjectInputStream ois = null;
	        	ois = new ObjectInputStream(clientSocket.getInputStream());

	        	while (true) {
	        		System.out.println("Sender.run(): Sending request for a Prime Number...");
		        	sendRequestForAPrimeNumberToCheck(oos);
		        	// Wait for a response
	        		System.out.println("Sender.run(): Waiting for a Prime Number...");
	       			try {pnp = (PrimeNumberProtocol) ois.readObject();} catch (Exception ex) {}
	       			try {
		        		System.out.println("Sender.run(): message received.");
						checkMessage(pnp, oos);
					} catch (Exception e) {
						System.out.println("Sender.run(): " + e.getLocalizedMessage());
						try {
							clientSocket.close();
						} catch (Exception ex) {}
						break;		// Give up on this connection and start over.
					}
	//	        	String msg = consoleIn.readLine();
	//	        	oos.writeObject(msg);
	//		        System.out.println("message sent.");
	//	        	if (msg.equals("Quit")) break;
		        	//String response = in.readLine();
			        //System.out.println("Response from " + hostName + ": " + response);
		        }
			}
      }
      catch (UnknownHostException ex) {
    	  System.out.println(name + ": Connect(): Unknown host exception: " + ex);
      }
      catch (IOException ex) {
        // There must not be a server listening on this port.
    	  System.out.println(name + ": Connect():" + ex);
      }
	}
	private void sendRequestForAPrimeNumberToCheck(ObjectOutputStream oos) {
		System.out.println("Sender.sendRequestForAPrimeNumberToCheck()...");
		PrimeNumberProtocol pnp = new PrimeNumberProtocol();
		pnp.setStatus(PrimeNumberProtocol.enumStatus.SEND_ME_A_NUMBER_TO_CHECK);
		try {
			oos.writeObject(pnp);
		} catch (IOException e) {
			System.out.println("Sender.sendRequestForAPrimeNumberToCheck(): " + e.getLocalizedMessage());
		}
	}
	/**
	 * Process a message received from a the Listener. The message is a PrimeNumberProtocol object
	 * @param pnp
	 * @throws Exception
	 */
	private void checkMessage(PrimeNumberProtocol pnp, ObjectOutputStream oos) throws Exception {
		System.out.println("Sender.checkMessage(): Prime Number Protocol message received");
		switch (pnp.getStatus()) {
			case HERE_IS_A_MESSAGE_FOR_YOU:
				System.out.println("Sender.CheckMessage(): Message = " + pnp.getMessage());
				break;
			case SEND_ME_A_NUMBER_TO_CHECK:
				System.out.println("Sender.CheckMessage(): Requesting a number to check");
				// This should not happen here. The Listener should not be requesting a number from the Sender
				throw new Exception("Sender.checkMessage(): Unexpected message: SEND_ME_A_NUMBER_TO_CHECK");
				//break;
			case CHECK_THIS_NUMBER:
				System.out.println("Sender.checkMessage(): Received a number to check...");
				pnp.setResultOfPrimeNumberCheck(IsPrime.isPrime(pnp.getNumber())); //uses the isprime method of the isPrime class to see if a number is prime or not. Then, within the pnp, it sets either true or false the ResultOfPrimeNumberCheck
				pnp.setStatus(PrimeNumberProtocol.enumStatus.THIS_NUMBER_HAS_BEEN_CHECKED); //sets the status of  pnp to refelct that the number has been checked
				try {
					oos.writeObject(pnp);
					break;
				} catch (IOException e) {
					System.out.println("Sender.checkMessage(): " + e.getLocalizedMessage());
				}
				System.out.println("Sender.checkMessage: This number has been checked for primeness.");
				break;
			case THIS_NUMBER_HAS_BEEN_CHECKED:
				// This should not happen here. The Listener should not be sending a confirmation to the Sender
				throw new Exception("Sender.checkMessage(): Unexpected message: THIS_NUMBER_HAS_BEEN_CHECKED");
				//break;
			case ERROR:
				System.out.println("Sender.checkMessage(): Error");
				break;
			default:
				System.out.println("Sender.checkMessage():: Unrecognized status message: " + pnp.getStatus());
				break;
		}
	}
}

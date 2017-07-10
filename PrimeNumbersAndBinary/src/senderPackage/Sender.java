/****************************************************************************************
 * A basic example of a TCP/IP client acting as a sender.                               *
 * This class opens a socket and sends on a port                                        *
 * Bill Nicholson                                                                       *
 * nicholdw@ucmail.uc.edu                                                               *
 ****************************************************************************************/
package senderPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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
	        System.out.println(name + ": Attempting to open port " + port + " at " + hostName + "...");
	        clientSocket = new Socket(hostName, port);
	        out = new PrintWriter(clientSocket.getOutputStream(), true);
	        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	        out = new PrintWriter(clientSocket.getOutputStream(), true);
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                                                       clientSocket.getInputStream()));
	        // If we get this far, we have established a connection
	        System.out.println("We are connected.");
	        System.out.println("Type messages to send to " + hostName + " Use Quit to end.");
        	InputStreamReader converter = new InputStreamReader(System.in);
        	BufferedReader consoleIn = new BufferedReader(converter);
        	ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
	        while (true) {
	        	String msg = consoleIn.readLine();
	        	oos.writeObject(msg);
		        System.out.println("message sent.");
	        	if (msg.equals("Quit")) break;
	        	//String response = in.readLine();
		        //System.out.println("Response from " + hostName + ": " + response);
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
}

package Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ComServ {
	private static int port = 2016;
    private static String address = "127.0.0.1";
    private Socket socket;
    private OutputStream clientOutput;
    private InputStream clientInput;
    private ObjectOutputStream clientObject;
    private DataInputStream din;
    private DataOutputStream dout;
    private BufferedReader serverInput;
    private int userid;
	
	public ComServ(){
		this.sendMessage();
	}
	
	private void connectToServer(String commande){
		try {
	        //Create a socket to the server
	        socket = new Socket(address, port);
	        //Create an output stream to send an object to the server
	        clientOutput = socket.getOutputStream();
	        clientInput = socket.getInputStream(); // Mauvais nom ?
	        clientObject = new ObjectOutputStream(clientOutput);
	        dout = new DataOutputStream(clientOutput);
	        din = new DataInputStream(clientInput);

	        //Create an input stream to receive messages from the server - USELESS ?
	        serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	        //Send a command to the server
	        dout.writeUTF(commande);
		}
		catch (UnknownHostException e) {
			System.out.println("Error : error with the hostname address. Please check the IP.");
			System.exit(-2);
			//If something happen during the execution, display an error message
		} catch (Exception e) {
			System.out.println("Error : cannot connect to server.");
			System.exit(-2);
		}
	}
		
	private void disconnect(){
		try {
			serverInput.close();
            clientInput.close();
            clientObject.close();
            clientOutput.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	private void sendMessage(){
		try {
	        //Create a socket to the server
	        socket = new Socket(address, port);
	        //Create an output stream to send an object to the server
	        clientOutput = socket.getOutputStream();
	        clientInput = socket.getInputStream();
	        clientObject = new ObjectOutputStream(clientOutput);
	        dout = new DataOutputStream(clientOutput);
	        din = new DataInputStream(clientInput);

	        //Create an input stream to receive messages from the server
	        serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	        //Send a command to the server
	        dout.writeUTF("coucou");

	        //Read the server response
	        String rep = din.readUTF();
	        System.out.println(rep);

	        //If the host is unreachable, display an error message
	    } catch (UnknownHostException e) {
	        System.out.println("Error : error with the hostname address. Please check the IP.");
	        System.exit(-2);
	        //If something happen during the execution, display an error message
	    } catch (Exception e) {
	        System.out.println("Error : cannot connect to server.");
	        System.exit(-2);
	    } finally {
	        //Close the streams and the socket
	        try {
	            serverInput.close();
	            clientObject.close();
	            clientOutput.close();
	            socket.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	
}



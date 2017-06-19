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
    private InputStream serverInput;
    private DataInputStream din;
    private DataOutputStream dout;
	
	public ComServ(){
		this.connectToServer();
		this.sendMessage();
		this.DisconnectFromServer();
	}
	private void connectToServer(){
		try {
			//Create a socket to the server
			socket = new Socket(address, port);

			//Create an output stream to send an object to the server
			clientOutput = socket.getOutputStream();
			serverInput = socket.getInputStream();
			dout = new DataOutputStream(clientOutput);
			din = new DataInputStream(serverInput);
		}
		//If the host is unreachable, display an error message
		catch (UnknownHostException e) {
			System.out.println("Error : error with the hostname address. Please check the IP.");
			System.exit(-2);
			//If something happen during the execution, display an error message
		} catch (Exception e) {
			System.out.println("Error : cannot connect to server.");
			System.exit(-2);
		}
	}
	
	private void sendMessage(){
	        //Send a command to the server
	        try {
				dout.writeUTF("inscription");//pseudo,mail,mdp
		        dout.writeUTF("yolouser§§§yolo@user.com§§§coucou");
		        //Read the server response
		        //String rep = din.readUTF();
		        int rep = din.readInt();
		        System.out.println(rep);
			} catch (IOException e) {
				System.out.println("Error : cannot send message to server.");
				System.exit(-2);
			}
	}
	
	private void DisconnectFromServer(){
		//Close the streams and the socket
		try {
			serverInput.close();
			clientOutput.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("Error : cannot disconnect from server.");
			System.exit(-2);
		}
	}
}

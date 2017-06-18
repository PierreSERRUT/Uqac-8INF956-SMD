package Serveur.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HandleClient implements Runnable {

    private Socket clientSocket;
    private InputStream clientInput;
    private OutputStream clientOutput;
    private ObjectInputStream clientObject;
    private Object objectInput;
    private DataInputStream din;
    private DataOutputStream dout;
	
	public HandleClient(Socket clientSock){
		this.clientSocket = clientSock;
	}
	
	 @Override
	    public void run() {
		 System.out.println("New client");
         
		 try{
		 clientInput = clientSocket.getInputStream();
		 clientOutput = clientSocket.getOutputStream();
         clientObject = new ObjectInputStream(clientInput);
         //objectInput = clientObject.readObject();
         din = new DataInputStream(clientInput);
         dout = new DataOutputStream(clientOutput);
         
         String mess = din.readUTF();
         System.out.println(mess);
         
         dout.writeUTF("yolo");
         
		 }
		 catch (IOException e) {
			 
		 }
		 
	 }
	
	public void connexion(){
		
	}
	
	public void deconnexion(){
		
	}
	
	public void inscription(){
		
	}
	
	public void reqCalcul(){
		
	}
	
	public void pingConnect(){
	
	}
	
	public void pingSsCalcul(){
	
	}
	
	public void ReceptionSsCalcul(){
	
	}
	
	public void sendSsCalcul(){
	
	}
}

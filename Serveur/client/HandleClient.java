package Serveur.client;

import java.net.Socket;

public class HandleClient implements Runnable {

    private Socket clientSocket;	
	
	public HandleClient(Socket clientSock){
		this.clientSocket = clientSock;
	}
	
	 @Override
	    public void run() {
		 System.out.println("New client");
		 
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

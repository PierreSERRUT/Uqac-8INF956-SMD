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
	/*
	 * Attention aux exception : exit -2 ==> fait quitter le programme en cas d'erreur
	 * manque boucle d'attente recep demande calcul
	 */
	public ComServ(){//A modifier
		this.connectToServer();
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
	
	
	private void sendConnexion(int userid, String mdp){
        try { //réponse serveur ?
			dout.writeUTF("connexion");
	        dout.writeInt(userid);
			dout.writeUTF(mdp);
			String rep = din.readUTF();
			if (rep.contentEquals("granted")){
				// confirmation ?
			}
			else if (rep.contentEquals("denied")){
				//Do something ?
			}
		} catch (IOException e) {
			System.out.println("Error : cannot connect.");
			System.exit(-2);
		}
	}

	private void sendDeconnexion(int userid){
        try {
			dout.writeUTF("deconnexion");
	        dout.writeInt(userid);
	        //Read the server response
	        //String rep = din.readUTF();
	        String rep = din.readUTF(); // Réponse serveur
	        System.out.println(rep);
		} catch (IOException e) {
			System.out.println("Error : cannot deconnect.");
			System.exit(-2);
		}
	}
	
	private int sendInscription(String pseudo, String mail, String mdp){ // Récupère le userid
		int userid =-1;
        try {
			dout.writeUTF("inscription");//pseudo,mail,mdp
	        dout.writeUTF(pseudo+"§§§"+mail+"§§§"+mdp);
	        //Read the server response
	        userid = din.readInt();
		} catch (IOException e) {
			System.out.println("Error : cannot subscribe.");
			System.exit(-2);
		}
        return userid;
	}
	
	private void sendReqCalcul(int userid){
        try {
			dout.writeUTF("reqCalcul");
	        dout.writeInt(userid);
	        //Read the server response
	        //String rep = din.readUTF();
	       // System.out.println(rep);
		} catch (IOException e) {
			System.out.println("Error : cannot submit calcul.");
			System.exit(-2);
		}
	}
	
	private void sendPingConnect(int userid){
		try {
			dout.writeUTF("pingConnect");
			dout.writeInt(userid);
			//Read the server response
			//String rep = din.readUTF();
			// System.out.println(rep);
		} catch (IOException e) {
			System.out.println("Error : cannot ping connect.");
			System.exit(-2);
		}
	}
	
	private void sendPingSsCalcul(int userid){
        try {
			dout.writeUTF("pingSsCalcul");
	        dout.writeInt(userid);
	        //Read the server response
	        //String rep = din.readUTF();
	       // System.out.println(rep);
		} catch (IOException e) {
			System.out.println("Error : cannot ping ss calcul.");
			System.exit(-2);
		}
	}
	
	private String recepSsCalcul(){
		String ssCalc ="";
		try {
			ssCalc += din.readUTF();
		} catch (IOException e) {
			System.out.println("Error : cannot receive ss calcul.");
			System.exit(-2);
		}
		return ssCalc;
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

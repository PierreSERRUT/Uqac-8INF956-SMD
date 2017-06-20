package client;

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
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import calcul.UnderCalc;
import calcul.UnderCalcMobile;

public class ComServ {
	
	private static int port = 2016;
    private static String address = "127.0.0.1";
    private Socket socket;
    private OutputStream clientOutput;
    private InputStream serverInput;
    private DataInputStream din;
    private DataOutputStream dout;
    private boolean isOver;
    private User user;
    
	/*
	 * Attention aux exception : exit -2 ==> fait quitter le programme en cas d'erreur
	 * manque boucle d'attente recep demande calcul
	 */
	public ComServ(){//A modifier
		user = new User();
		// choose your user
		user.pseudo = "YoloUser";
		user.password = "password";
		user.mail = "yolo@yahoo.fr";
		/*
		user.pseudo = "SwagUser";
		user.password = "password";
		user.mail = "swag@gmail.fr";
		
		user.pseudo = "TMTCUser";
		user.password = "password";
		user.mail = "tmtc@yahoo.fr";
		
		user.pseudo = "newUser";
		user.password = "password";
		user.mail = "coucou@gmail.fr";
		*/
		
		this.connectToServer();
		isOver = false;
		int delay = 60000; // 600 000ms = 10 min
		TimerTask pingDispo = new TimerTask() {
			
			@Override
			public void run() {
				// Action
				connectToServer();
				System.out.println("ping");
				try {
					dout.writeUTF("ping!!");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sendPingConnect(user.id);
				DisconnectFromServer();
				System.out.println("i'm done");
			}
		};
		
	    Timer ping = new Timer();
		sendInscription(user.pseudo,user.mail,user.password);
		sendDeconnexion(user.id);
		this.DisconnectFromServer();
		
		connectToServer();
		sendConnexion(user.id,user.password);
	    sendDeconnexion(user.id);
		this.DisconnectFromServer();
	    //ping.scheduleAtFixedRate(pingDispo, new Date(), delay);	
	    
	    connectToServer();
	    System.out.println("j'essaie de me co");
	    sendConnexion(user.id,user.password);
	    DisconnectFromServer();
	    
	    Runnable listen = new Runnable() {
	    	
	    	@Override
	    	public void run() {
	    		connectToServer();
	    		try {
	    			dout.writeUTF("rdy");
	    			System.out.println("waiting…");
	    			String mess = din.readUTF();
	    			System.out.println(mess);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		DisconnectFromServer();
	    		;}
	    };
	    
	    //new Thread(listen).start();
	    

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
			System.out.println(rep);
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
	        String rep = din.readUTF(); // Réponse serveur
	        System.out.println(rep);
		} catch (IOException e) {
			System.out.println("Error : cannot deconnect.");
			e.printStackTrace();
			System.exit(-2);
		}
	}
	
	private void sendInscription(String pseudo, String mail, String mdp){ // Récupère le userid
		int userid =-1;
        try {
			dout.writeUTF("inscription");//pseudo,mail,mdp
	        dout.writeUTF(pseudo+"§§§"+mail+"§§§"+mdp);
	        //Read the server response
	        user.id = din.readInt();
		} catch (IOException e) {
			System.out.println("Error : cannot subscribe.");
			System.exit(-2);
		}
	}
	
	private void sendReqCalcul(int userid, String calcul){
        try {
			dout.writeUTF("reqCalcul");
	        dout.writeInt(userid);
	        dout.writeUTF(calcul);
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
			e.printStackTrace();
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
	
	// ici faut bosser sur le calc
	private Double resUnderCalcul(String underCalc){
		
		UnderCalcMobile calcul = new UnderCalcMobile();
		calcul.SetMessRecu(underCalc);
		calcul.RecupUCalc();
		Double res = calcul.CalcUnderCalc();
		return res;
		/*
		String ssCalc ="";
		try {
			ssCalc += din.readUTF();
		} catch (IOException e) {
			System.out.println("Error : cannot receive ss calcul.");
			System.exit(-2);
		}
		return ssCalc;
		*/
	}
	
	private void sendResCalcul(UnderCalc underCalcul){
		try {			
			dout.writeUTF("resUnderCalcul");
			dout.writeInt(user.id);
			dout.writeInt(underCalcul.getIdCalc());
			dout.writeInt(underCalcul.getIdUnderCalc());
			dout.writeDouble(underCalcul.getRes());
			
			//Read the server response
			//String rep = din.readUTF();
			// System.out.println(rep);
		} catch (IOException e) {
			System.out.println("Error : cannot send calcul.");
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

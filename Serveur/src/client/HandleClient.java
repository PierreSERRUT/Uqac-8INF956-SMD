package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import calcul.CalculList;
import calcul.HandleCalcul;
import db.HandleDB;
import db.User;

public class HandleClient extends Thread {//manque boucle contrôle run, attente message

	private Socket clientSocket;
	private InputStream clientInput;
	private OutputStream serverOutput;
	private DataInputStream din;
	private DataOutputStream dout;
	private Boolean isOver = false;

	public HandleClient(Socket clientSock){
		this.clientSocket = clientSock;
		//this.run();
	}

	@Override
	public void run() {
		System.out.println("New client");
		while(!isOver){
			String commande = "";
			try{
				clientInput = clientSocket.getInputStream();
				serverOutput = clientSocket.getOutputStream();
				din = new DataInputStream(clientInput);
				dout = new DataOutputStream(serverOutput);
				commande = din.readUTF();
				System.out.println(commande);
			}
			catch (IOException e){
			}

			switch(commande){
			case "connexion":
				connexion();
				break;
			case "deconnexion":
				deconnexion();
				break;
			case "inscription":
				inscription();
				break;
			case "reqCalcul":
				reqCalcul();
				break;
			case "pingConnect":
				pingConnect();
				break;
			case "pingSsCalcul":
				pingSsCalcul();
				break;
			case "resUnderCalcul":
				receptionSsCalcul();
				break;
			case "pingpong":
				System.out.println("pingpong");
				break;
			case "testscan":
				try {
					dout.writeUTF("TEST SCAN OK");
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("J'ai répondu");
			default:
				break;
			}
		}
		try {
			clientInput.close();
			serverOutput.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private Client createClient(int userid){
		Client client = new Client(userid, this);	
		return client;
	}
	
	private User createUser(int userid, String mdp){
		//Pour la connexion à la DB
		User user = new User();
		user.id = userid;
		user.password = mdp;
		return user;
	}
	
	private User createUser(String mdp, String mail, String pseudo){
		User user = new User();
		user.password = mdp;
		user.mail = mail;
		user.pseudo = pseudo;
		
		return user;
	}

	public void connexion(){
		/* –recevoir userid et mdp
		 * +envoyer à DB pour vérifier
		 * +si ok : connecter, autrement rejeter
		 */
		int userid;
		String mdp;

		try{
			userid = din.readInt();
			mdp = din.readUTF();
			System.out.println(userid+"\n"+mdp);
			//Envois à la DB pour vérifier
			User u = createUser(userid,mdp);
			if(HandleDB.getInstance().connection(u)){
				Client client = createClient(userid);
				ClientList.getInstance().addConnect(client);
				dout.writeUTF("granted"); // confirmation ?
			}
			else{
			dout.writeUTF("denied"); //rejet
			}
		
		} catch (IOException e){
		}

	}

	public void deconnexion(){
		/*	recevoir userid
		 * 	+retirer de la liste 
		 */
		int userid;

		try{
			userid = din.readInt();
			ClientList.getInstance().decoClient(userid);
			dout.writeUTF("deconnected");
			dout.flush();
			this.isOver=true;
		} catch (IOException e){

		}
	}

	public void setIsOver(Boolean isOver) {
		this.isOver = isOver;
	}

	public void inscription(){
		/*	recevoir pseudo, mail et mdp
		 * 	+envoyer à la DB
		 * 	+connecter
		 * 	+retourner userid au mobile
		 * Retour -1 si erreur (?)
		 */
		String pseudo, mail, mdp;
		try{
			String[] str = din.readUTF().split("§§§");
			pseudo = str[0];
			mail = str[1];
			mdp = str[2];
			//ajout dans la DB
			User u = createUser(mdp,mail,pseudo);
			int userid = HandleDB.getInstance().subscribe(u);
			//connexion du client
			Client client = createClient(userid);
			ClientList.getInstance().addConnect(client);
			
			//renvoi de son userid à l'utilisateur
			System.out.println(mail+"\n"+pseudo+"\n"+mdp); // vérif
			dout.writeInt(userid);
		} catch (IOException e){

		}
	}

	public void reqCalcul(){
		/* recevoir userid, requête calcul
		 * 
		 * profit
		 */
		int userid;
		String calcul;

		try{
			userid = din.readInt();
			calcul = din.readUTF();
			System.out.println(calcul);

			Client client = ClientList.getInstance().findClient(userid);
			
			//sendResCalcul(0, 12.0);
			HandleCalcul handleCalcul = new HandleCalcul(client, calcul);
			// attention au Garbage collector, du plus le handle va se cr�e et ne rien faire !
			
		} catch (IOException e){

		}	
	}

	public void pingConnect(){
		/* recevoir userid
		 * MAJ ClientList / Client
		 */
		int userid;

		try{
			userid = din.readInt();	
			//Récupérer le client
			Client c = ClientList.getInstance().findClient(userid); 
			if (c!=null){
				if(c.handleClient != this){
					System.out.println("! Different HandleClient !");
					c.handleClient = this;
				}
				//Maj ClientList
				ClientList.getInstance().addConnect(c);
			}
			else {
				//Maj ClientList
				c = createClient(userid);
				ClientList.getInstance().addConnect(c);
			}
		} catch (IOException e){
		}

	}

	public void pingSsCalcul(){
		/*	recevoir userid
		 * 	MAJ handlecalcul (?)
		 * TO do si temps permet
		 */
		int userid;

		try{
			userid = din.readInt();	
		} catch (IOException e){

		}
	}

	public void receptionSsCalcul(){
		/*	recevoir userid, réponse sous calcul, id calcul, (id ssCalcul ?)
		 *	retrouver calcul dans calculList
		 *	envoyer vers HandleCalcul via calcul
		 */
		int userid;
		int idCalcul;
		int idUnderCalcul;
		Double repSsCalcul;

		try{
			System.out.println("resultat sous calcul recu");
			userid = din.readInt();
			idCalcul = din.readInt();
			idUnderCalcul = din.readInt();
			repSsCalcul = din.readDouble();
			
			System.out.println("userid : " + userid + " idCalcul : "+ idCalcul + " idUnderCalcul : "+ idUnderCalcul + " repSsCalcul : "+repSsCalcul );
			
			CalculList.getInstance().addResUnderCalcul(idCalcul, idUnderCalcul, repSsCalcul);
			
			
		} catch (IOException e){

		}
	}

	public void sendSsCalcul(int idCalc, String calcul, int underCalcId){
		//Envoyer le ss calcul
		/*
		 * demander si possible calcul
		 * réponse ==> oui, j'envoi
		 * ==> non, retourner erreur
		 */
		try{
			System.out.println("Avant calculdemand");
			dout.writeUTF("calculdemand");
			//String rep =  din.readUTF();
			//System.out.println("Yes ?" + rep);
			//if (rep.contentEquals("yes")){
			System.out.println("Avant idCalc");
			dout.writeInt(idCalc);	
			System.out.println("Avant underCalcId");
			dout.writeInt(underCalcId);
			System.out.println("Avant calcul : " + calcul);
			dout.writeUTF(calcul);
			dout.flush();
			System.out.println("Apres calcul");

				//return true
			//}
			/*else{
				//return false
			}*/
		} catch (IOException e){

		}
	}
	
	public void sendResCalcul(int idCalcul, double res){
		//Envoyer le ss calcul
		/*
		 * demander si possible calcul
		 * réponse ==> oui, j'envoi
		 * ==> non, retourner erreur
		 */
		try{
			System.out.println("Rescalcul envoy�");
			dout.writeUTF("ResCalcul");
			String rep =  din.readUTF();
			System.out.println("rep : "+rep);
			if (rep.contentEquals("dispo")){
				dout.writeInt(idCalcul);
				dout.writeDouble(res);
				//return true 
				System.out.println("Calcul envoyer " + res);
			}
			else{
				//return false
			}
		} catch (IOException e){

		}
	}
}

package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import calcul.HandleCalcul;
import db.HandleDB;
import db.User;

public class HandleClient implements Runnable {//manque boucle contrôle run, attente message

	private Socket clientSocket;
	private InputStream clientInput;
	private OutputStream serverOutput;
	private DataInputStream din;
	private DataOutputStream dout;

	public HandleClient(Socket clientSock){
		this.clientSocket = clientSock;
	}

	@Override
	public void run() {
		System.out.println("New client");
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
		default:
			break;
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
			/*if (HandleDB.connexion(u)){ //appel static ?
				Client client = createClient(userid);
				ClientList.addConnect(client); //appel static ?
				dout.writeUTF("granted"); // confirmation ?
			}
			else{
			dout.writeUTF("denied"); //rejet
			}*/
		
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
			//Récuperer le client
			/*Client c = ClientList.findClient(userid); //appel static ?
			if(c!=null){
			//ClientList.decoClient(client) // appel static ?		
			}
			dout.writeUTF("deconnected"); 
			*/
		} catch (IOException e){

		}
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
			/*User u = createUser(mdp,mail,pseudo);
			int userid = HandleDB.inscription(u); //appel static ?
			//connexion du client
			Client client = createClient(userid);
			ClientList.addConnect(client); //appel static ?
			
			//renvoi de son userid à l'utilisateur*/
			System.out.println(mail+"\n"+pseudo+"\n"+mdp); // vérif
			int userid = 100; // A supprimer
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

			Client client = ClientList.getInstance().findClient(userid);
			
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
			/*Client c = ClientList.findClient(userid); //appel static ?
			if (c!=null){
				if(c.handleClient != this){
					System.out.println("! Different HandleClient !");
					c.handleClient = this;
				}
				//Maj ClientList
				ClientList.addConnect(c); //appel static ?
			}
			else {
				//Maj ClientList
				c = createClient(userid);
				ClientList.addConnect(c); //appel static ?
			}*/
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
		String repSsCalcul;

		try{
			userid = din.readInt();
			repSsCalcul = din.readUTF();
			
		} catch (IOException e){

		}
	}

	public void sendSsCalcul(String calcul){
		//Envoyer le ss calcul
		/*
		 * demander si possible calcul
		 * réponse ==> oui, j'envoi
		 * ==> non, retourner erreur
		 */
		try{
			dout.writeUTF("calculdemand");
			String rep =  din.readUTF();
			if (rep.contentEquals("dispo")){
				dout.writeUTF(calcul);
				//return true 
			}
			else{
				//return false
			}
		} catch (IOException e){

		}
	}


}

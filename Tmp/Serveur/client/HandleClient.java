package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import db.HandleDB;
import db.User;

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
		String commande = "";
		try{
			clientInput = clientSocket.getInputStream();
			clientOutput = clientSocket.getOutputStream();
			clientObject = new ObjectInputStream(clientInput);
			//objectInput = clientObject.readObject();
			din = new DataInputStream(clientInput);
			dout = new DataOutputStream(clientOutput);

			commande = din.readUTF();
			System.out.println(commande);
		}
		catch (IOException e) {
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
		case "receptionSsCalcul":
			receptionSsCalcul();
			break;
		default:
			break;
		}

	}

	
	private Client createClient(int userid){
		Client client = new Client();
		client.handleClient = this;
		client.userid = userid;
		
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
			//Récuperer le client ? envoyer le userid ?
			//ClientList.decoClient(client)
		} catch (IOException e){

		}
	}

	public void inscription(){
		/*	recevoir pseudo, mail et mdp
		 * 	+envoyer à la DB
		 * 	+connecter
		 * 	+retourner userid au mobile 
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
			System.out.println(mail+"\n"+pseudo+"\n"+mdp);
			int userid = 100;
			dout.writeInt(userid);
		} catch (IOException e){

		}
	}

	public void reqCalcul(){
		/* recevoir userid, requête calcul
		 * ??
		 * profit
		 */
		int userid;

		try{
			userid = din.readInt();		
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
		} catch (IOException e){

		}

	}

	public void pingSsCalcul(){
		/*	recevoir userid
		 * 	MAJ handlecalcul (?)
		 */
		int userid;

		try{
			userid = din.readInt();		
		} catch (IOException e){

		}
	}

	public void receptionSsCalcul(){
		/*	recevoir userid, sous calcul
		 *	MAJ handlecalcul
		 */
		int userid;

		try{
			userid = din.readInt();		
		} catch (IOException e){

		}
	}

	public void sendSsCalcul(){
		/*	envoyer sous calcul
		 * ???
		 * profit
		 */
	}


}

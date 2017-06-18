package client;

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

	private void createClient(int userid){
		Client client = new Client();
		client.handleClient = this;
		client.userid = userid;
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
			pseudo = din.readUTF(); 
			mail = din.readUTF();
			mdp = din.readUTF();
		} catch (IOException e){

		}
	}

	public void reqCalcul(){
		/* recevoir userid, requête calcul
		 * 
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

	public void ReceptionSsCalcul(){
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
		 * 
		 */
		int userid;

		try{
			userid = din.readInt();		
		} catch (IOException e){

		}
	}


	private void receptionSsCalcul() {
		// TODO Auto-generated method stub

	}
}

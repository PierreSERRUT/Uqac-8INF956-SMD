package calcul;

import client.Client;
import db.HandleDB;

public class HandleCalcul implements Runnable {

	private Client client;
	private Calcul calc;
	private String messageCalc;
	private int choixNbUser;
	private double resultat;
	private int clientId;

	// private UnderCalcServ [] listUCalc;

	public HandleCalcul() {
		//this.run();
	}

	public HandleCalcul(Client client, String calcul) {

		// listUCalc= new UnderCalcServ [10];
		this.clientId = client.getUserid();
		this.client = client;
		this.messageCalc = calcul;
		this.run();
		
	}

	public String recuperationCalc(String str) {

		// Recuperation du calcul a partir du
		// message transmis par le serveur
		// A FAIRE

		return str;
	}

	public void miseEnFormeDonnees() {
		// calc.SetData(messageCalc);
		this.calc.decoupage();
		this.calc.affMat();
	}

	public void fragCalcul() {
		this.calc.fragmentationCalcul();
	}

	public void envoiUnderCalc(int idCalcul, int idUnderCalc) {
		String calcul;
		//int UnderCalcId = this.calc.getUCalcId(idUnderCalc);
		calcul = this.calc.getUCalForm(idUnderCalc);
		
		int userid = this.calc.getUserIdFromUnderCalcId(idUnderCalc);
		//System.out.println("userID " + userid);
		Client client = this.calc.getClientFromUserId(userid);
		
		client.handleClient.sendSsCalcul(idCalcul, calcul, idUnderCalc);
		//System.out.println("Client " + client.getUserid());
		/*
		 * Envoi: idUcalc, calc
		 * calcul --> listUnderCalc --> getUserId --> listClient --> récupérer le client --> handleclient --> sendSsCalcul(calcul)
		 */		
	}

	public void recepUnderRes() {
		//a faire
		/* loop d'attente de tout les sous-r�sultats 
		 * 
		 *  a partir de userid je 
		 */
		int i =0;
		do{
			//recep ss res
			/*
			 * D�terminer indice SsCalc par l'id 
			 * pour l'instant: ID du SsCalcul = 10 + indice du ssCalc
			if(reception) {	
				this.calc.SetUndRes(indiceSsCalc,res);
				i++;
			}
			 * 
			 */			
		}while(i<this.choixNbUser);
	}

	public void resultFinal() {
		this.resultat = this.calc.getRes();
	}

	public void timeOutUCalc() {

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		this.calc = new Calcul(this.clientId, this.clientId, this, this.messageCalc);
		this.choixNbUser = this.calc.getNbParties();
		//this.calc.AffListeVal();
		//this.calc.AffListeOpe();
		miseEnFormeDonnees();
		fragCalcul();
		CalculList.getInstance().addCalcul(this.calc);
		for (UnderCalcServ underCalc : this.calc.getListUnderCal()) {
			//System.out.println("Test " + underCalc.idUnderCalc);
			envoiUnderCalc(calc.getIdCalcul(),underCalc.idUnderCalc);
		}
		//System.out.println("Pb");
		//recepUnderRes();
		
		while(!calc.getIsOver()){//On attend que le calcul soit finit
		}
		System.out.println("J'ai tous les resultats de mes sous-calculs");
		resultFinal();
		System.out.println("Resultat final : " + this.resultat);
		this.client.handleClient.sendResCalcul(this.calc.getIdCalcul(), this.resultat);
		HandleDB.getInstance().addCalculOver(this.calc);
	}
}

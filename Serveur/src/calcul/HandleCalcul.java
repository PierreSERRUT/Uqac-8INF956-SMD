package calcul;

import client.Client;

public class HandleCalcul implements Runnable {

	private Client client;
	private Calcul calc;
	private String messageCalc;
	private int choixNbUser;
	private double resultat;
	private int clientId;

	// private UnderCalcServ [] listUCalc;

	public HandleCalcul() {

	}

	public HandleCalcul(Client client, String calcul) {

		// listUCalc= new UnderCalcServ [10];
		this.clientId = client.getUserid();
		this.client = client;
		this.messageCalc = calcul;
		
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

	public void envoiUnderCalc(int indUCalc) {
		// a faire
		String message;
		int UCalcId = this.calc.getUCalcId(indUCalc);
		message = this.calc.getUCalForm(indUCalc);
		
		/*
		 * Envoi: idUcalc, calc
		 * 
		 */		
	}

	public void recepUnderRes() {
		//a faire
		/* loop d'attente de tout les sous-résultats 
		 * 
		 *  a partir de userid je 
		 */
		int i =0;
		do{
			//recep ss res
			/*
			 * Déterminer indice SsCalc par l'id 
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
		// a faire
		this.resultat = this.calc.calcResFinal();
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
		for (int i = 0; i < this.choixNbUser; i++) {
			envoiUnderCalc(i);
		}
		recepUnderRes();
		resultFinal();
	}
}

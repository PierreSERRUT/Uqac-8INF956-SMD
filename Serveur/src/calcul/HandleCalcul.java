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

	public HandleCalcul(int id, Client cl, String str) {

		// listUCalc= new UnderCalcServ [10];
		this.clientId = id;
		this.client = cl;
		this.messageCalc = str;
		
	}

	public String RecuperationCalc(String str) {

		// Recuperation du calcul a partir du
		// message transmis par le serveur
		// A FAIRE

		return str;
	}

	public void MiseEnFormeDonnees() {
		// calc.SetData(messageCalc);
		this.calc.Decoupage();
		this.calc.AffMat();
	}

	public void FragCalcul() {
		this.calc.FragmentationCalcul();
	}

	public void EnvoiUnderCalc(int indUCalc) {
		// a faire
		String message;
		message = this.calc.GetUCalForm(indUCalc);
		// ENVOI
		calc.
	}

	public void RecepUnderRes() {
		// a faire
	}

	public void ResultFinal() {
		// a faire
		this.resultat = this.calc.CalcResFinal();
	}

	public void TimeOutUCalc() {

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		this.calc = new Calcul(this.clientId, this.clientId, this, this.messageCalc);
		this.choixNbUser = this.calc.GetNbParties();
		this.calc.AffListeVal();
		this.calc.AffListeOpe();
		MiseEnFormeDonnees();
		FragCalcul();
		this.messageCalc = this.calc.GetUCalForm(0);
		for (int i = 0; i < 4; i++) {
			String mess = this.calc.GetUCalForm(i);
			System.out.println(mess);
		}
	}
}

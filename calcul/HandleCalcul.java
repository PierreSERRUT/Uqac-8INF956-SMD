package calc;

public class HandleCalcul {

	private Calcul calc;
	private String messageCalc;
	private int choixNbUser;
	//private ; structDonneEch
	
	// private UnderCalcServ [] listUCalc;

	public HandleCalcul() {

	}

	public HandleCalcul(String str) {

		// listUCalc= new UnderCalcServ [10];
		messageCalc = RecuperationCalc(str);
		calc = new Calcul(messageCalc);
		choixNbUser = calc.GetNbParties();

		MiseEnFormeDonnees();
		FragCalcul();

	}

	public String RecuperationCalc(String str) {

		// Recuperation du calcul a partir du
		// message transmis par le serveur
		// A FAIRE

		return str;
	}

	public void MiseEnFormeDonnees() {
		calc.SetData(messageCalc);
		calc.Decoupage();
		calc.AffMat();
	}

	public void FragCalcul() {
		calc.FragmentationCalcul();
	}

	public void EnvoiUnderCalc() {
		//a faire
	}
	public void RecepUnderCalc() {
		//a faire
	}
	public double ResultFinal() {
		//a faire
		return 0;
	}
	public void TimeOutUCalc() {
		
	}
	
	
}

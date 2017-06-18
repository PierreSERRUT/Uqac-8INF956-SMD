package calcul;

public class HandleCalcul {

	private Calcul calc;
	private String messageCalc;
	private int choixNbUser;
	private double resultat;
	
	//private ; structDonneEch
	
	// private UnderCalcServ [] listUCalc;

	public HandleCalcul() {

	}

	public HandleCalcul(String str) {

		// listUCalc= new UnderCalcServ [10];
		messageCalc = RecuperationCalc(str);
		calc = new Calcul(messageCalc);
		choixNbUser = calc.GetNbParties();
		calc.AffListeVal();
		calc.AffListeOpe();
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
		//calc.SetData(messageCalc);
		calc.Decoupage();
		calc.AffMat();
	}

	public void FragCalcul() {
		calc.FragmentationCalcul();
	}

	public void EnvoiUnderCalc() {
		//a faire
	}
	public void RecepUnderRes() {
		//a faire
	}
	public void ResultFinal() {
		//a faire
		resultat = calc.CalcResFinal();
	}
	public void TimeOutUCalc() {
		
	}
	
	


public static void main(String [ ] args)
{
	String calctest = "38.4-3.38+34*82+3-2.43+7.8-3*2.4$";
	HandleCalcul handleC = new HandleCalcul(calctest);
	
	
}
}

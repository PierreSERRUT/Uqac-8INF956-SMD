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
		//this.messageCalc = RecuperationCalc(str);
		this.calc = new Calcul(str);
		this.choixNbUser = this.calc.GetNbParties();
		this.calc.AffListeVal();
		this.calc.AffListeOpe();
		MiseEnFormeDonnees();
		FragCalcul();
		this.messageCalc=this.calc.GetUCalForm(0);
		for(int i = 0;i<4;i++) {
			String mess = this.calc.GetUCalForm(i);
			System.out.println(mess);
		}
	}

	public String RecuperationCalc(String str) {

		// Recuperation du calcul a partir du
		// message transmis par le serveur
		// A FAIRE

		return str;
	}

	public void MiseEnFormeDonnees() {
		//calc.SetData(messageCalc);
		this.calc.Decoupage();
		this.calc.AffMat();
	}

	public void FragCalcul() {
		this.calc.FragmentationCalcul();
	}

	public void EnvoiUnderCalc(int indUCalc) {
		//a faire
		String message;
		message = this.calc.GetUCalForm(indUCalc);
		//ENVOI
	}
	public void RecepUnderRes() {
		//a faire
	}
	public void ResultFinal() {
		//a faire
		this.resultat = this.calc.CalcResFinal();
	}
	public void TimeOutUCalc() {
		
	}
	
	


public static void main(String [ ] args)
{
	String calctest = "1+2+3+4+5+6+7+8+9+10+11+12+13+14+15+16+17+18+19+20+21+22+23+24+25+26+27+28+29+30+31+32+33+34+35$";
	
	HandleCalcul handleC = new HandleCalcul(calctest);
	/*UnderCalcMob uCalc = new UnderCalcMob();
	uCalc.SetMessRecu(handleC.messageCalc);
	uCalc.RecupUCalc();
	uCalc.AffMess();
	uCalc.AffVal();
	uCalc.AffOpe();
	uCalc.CalcUnderCalc();
	System.out.println("Res : " + uCalc.res);*/
	 
	
	
}
}

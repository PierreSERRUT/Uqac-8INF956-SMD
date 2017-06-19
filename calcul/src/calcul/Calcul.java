package calcul;

import java.util.ArrayList;

public class Calcul {

	private int idCalc;
	private int nbParties;
	private int idClient;
	
	private double res;

	// inutile ?
	// private HandleCalcul HandleCalc;

	private ArrayList<UnderCalcServ> listUnderCal;

	private int achievement;
	
	private double[][] matVal;
	private char[][] matOpe;
	
	private ArrayList <Double> listVal;
	private ArrayList <Character> listOpe;
	private ArrayList <Double> listUndRes;

	//private double[] listVal;
	//private char[] listOpe;

	public Calcul() {
		this.idCalc = 0;
		this.idClient = 0;
		this.nbParties = 4;

		this.listUnderCal = new ArrayList<UnderCalcServ>(this.nbParties);

		this.matVal = new double[10][10];
		this.matOpe = new char[10][10];
		this.listVal = new ArrayList<Double>();
		this.listOpe = new ArrayList<Character>();
		this.listUndRes = new ArrayList <Double>(this.nbParties);
		//this.listVal = new double[10];
		//this.listOpe = new char[10];
		this.achievement = 0;
	}

	public Calcul(String str) {
		// this.HandleCalc = new HandleCalcul(str);
		this.idCalc = 0;
		this.idClient = 0;
		this.nbParties = 4;
		this.achievement = 0;
		
		this.listUnderCal = new ArrayList<UnderCalcServ>(this.nbParties);

		this.listVal = new ArrayList<Double>(1);
		this.listOpe = new ArrayList<Character>(1);
		this.listUndRes = new ArrayList <Double>(this.nbParties);
		//this.listVal = new double[10];
		//this.listOpe = new char[10];
		
		this.SetData(str);
		this.matVal = new double [this.nbParties][this.listVal.size()/this.nbParties+1];
		this.matOpe = new char[this.nbParties+1][this.listVal.size()/this.nbParties+1];
	}

	public void SetListVal(int indice, double val) {
		this.listVal.add(indice, val);
	}

	public void SetListOpe(int indice, char ope) {
		this.listOpe.add(indice,ope);
	}

	public void SetData(String str) {

		int j = 0, indiceOpe = 0;
		String tmp;

		for (int i = 0; i < str.length(); i++) {
			if (((str.charAt(i) > 47) && (str.charAt(i) < 58))||(str.charAt(i) == '.')) {
				j++;
				if (str.charAt(i) == '$') {
					tmp = str.substring(i - j, i);
					this.listVal.add(Double.parseDouble(tmp));
				}
			} else {
				tmp = str.substring(i - j, i);
				this.listVal.add(Double.parseDouble(tmp));
				j = 0;
				if (str.charAt(i) != '$') {
					SetListOpe(indiceOpe, str.charAt(i));
					indiceOpe++;
				}
			}
		}
	}
	public String GetUCalForm (int indiceUCalc) {
		String uCalcForm;
		uCalcForm = this.listUnderCal.get(indiceUCalc).PrepEnvoi();
		
		return uCalcForm;
	}

	public void Decoupage() {
		int taillePartVal = this.listVal.size() / this.nbParties;
		int restePartVal = this.listVal.size() % this.nbParties;
		//System.out.println("taille : " + taillePartVal);
		//System.out.println("reste : " + restePartVal);
		//int taillePartOpe = this.listOpe.size() / this.nbParties;
		//int restePartOpe = this.listOpe.size() % this.nbParties;

		int indiceListVal = 0; 
		int indiceListOpe = 0;
		
		// Matrice Val
		for(int i = 0; i<restePartVal;i++) {
			for(int j = 0; j<(taillePartVal + 1); j++) {
				this.matVal[i][j] = this.listVal.get(indiceListVal);
				indiceListVal++;
			}
		}
		
		for(int i = restePartVal; i<this.nbParties;i++) {
			for(int j = 0; j<taillePartVal; j++) {
				this.matVal[i][j] = this.listVal.get(indiceListVal);
				indiceListVal++;
			}
		}
		System.out.println("Ope");
		
		// Matrice OPE
		
		for(int i = 0; i<restePartVal;i++) {
			for(int j = 0; j<(taillePartVal-1); j++) {
				//System.out.println("indice ope : " + indiceListOpe);
				this.matOpe[i][j] = this.listOpe.get(indiceListOpe);
				indiceListOpe++;
			}
			//System.out.println("ajout sur derniere ligne");
			this.matOpe[this.nbParties][i]=this.listOpe.get(indiceListOpe);
			indiceListOpe++;
		}
		
		for(int i = restePartVal; i<this.nbParties;i++) {
			for(int j = 0; j<taillePartVal-2; j++) {
				//System.out.println("indice ope : " + indiceListOpe);
				this.matOpe[i][j] = this.listOpe.get(indiceListOpe);
				indiceListOpe++;
			}
			//this.matOpe[i][taillePartVal] = '$';
			if(indiceListOpe <= this.listOpe.size()-1){
				//System.out.println("ajout sur derniere ligne");
				this.matOpe[this.nbParties][i] = this.listOpe.get(indiceListOpe);
				indiceListOpe++;
			}
		}
		
		
	}

	public void FragmentationCalcul() {
		for (int i = 0; i < this.nbParties; i++) {	

			UnderCalcServ undCalcTmp = new UnderCalcServ();

			undCalcTmp.SetData(this.GetDataParties(i));
			undCalcTmp.SetOpe(this.GetOpeParties(i));
			this.listUnderCal.add(i, undCalcTmp);
		}
	}

	public int GetNbParties() {
		return nbParties;
	}
	public void GetUndRes(){
		for(int i= 0 ; i<this.nbParties;i++)
			this.listUndRes.add(i, this.listUnderCal.get(i).GetRes());
	}

	public double CalcResFinal() {
		this.GetUndRes();		
		
		this.res = this.listUndRes.get(0);
		int m = this.listUndRes.size();

		for (int i = 1; i < m; i++) {
			switch (matOpe[m][i-1]) {
			case '+':
				this.res += this.listUndRes.get(i);
				break;
			case '-':
				this.res -= this.listUndRes.get(i);
				break;
			case '*':
				this.res *= this.listUndRes.get(i);
				break;
			case '/':
				if (matOpe[m][i] == 0)
					System.out.println("Division par zero: division non effectué");
				else
					this.res *= this.listUndRes.get(i);
				break;
			default:
				break;
			}
		}
		return res;
	}
	public ArrayList<Double> GetDataParties(int numParties) {
		ArrayList<Double> data = new ArrayList<Double>(matVal[numParties].length);
		for (int i = 0; i < matVal[numParties].length; i++)
			data.add(i,matVal[numParties][i]);
		return data;
	}
	
	public ArrayList<Character> GetOpeParties(int numParties) {
		ArrayList<Character> ope = new ArrayList<Character>(matOpe[numParties].length);
		for (int i = 0; i < matOpe[numParties].length; i++)
			ope.add(i,matOpe[numParties][i]);
		return ope;
	}
	

	public void AffMat() {
		System.out.println("Matrice des Valeurs:");
		for (int i = 0; i < matVal.length; i++) {
			System.out.print("(");
			for (int j = 0; j < matVal[i].length; j++)
				System.out.print(matVal[i][j] + " ");
			System.out.println(")");
		}
		System.out.println();
		System.out.println("Matrice des Operateurs::");
		for (int i = 0; i < matOpe.length; i++) {
			System.out.print("(");
			for (int j = 0; j < matOpe[i].length; j++)
				System.out.print(matOpe[i][j]+ " ");
			System.out.println(")");
		}
	}

	public void AffListeVal() {
		System.out.println("Liste des valeurs: ");
		for (int i = 0; i < listVal.size(); i++) 
			System.out.print(listVal.get(i) + " ");
		System.out.println();
	}

	public void AffListeOpe() {
		System.out.println("Liste des operateurs: ");
		for (int i = 0; i < listOpe.size(); i++) 
			System.out.print(listOpe.get(i) + " ");
		System.out.println();
	}

}

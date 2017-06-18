package calc;

import java.util.ArrayList;

public class Calcul {

	private int idCalc;
	private int nbParties;
	private int tailleParties;
	private int idClient;

	// inutile ?
	// private HandleCalcul HandleCalc;

	private ArrayList<UnderCalcServ> listUnderCal;

	private int achievement;
	
	private double[][] matVal;
	private char[][] matOpe;
	
	private ArrayList <Double> listVal;
	private ArrayList <Character> listOpe;
	//private double[] listVal;
	//private char[] listOpe;

	public Calcul() {
		this.idCalc = 0;
		this.idClient = 0;
		this.tailleParties = 0;
		this.nbParties = 4;

		this.listUnderCal = new ArrayList<UnderCalcServ>();

		this.matVal = new double[10][10];
		this.matOpe = new char[10][10];
		this.listVal = new ArrayList<Double>();
		this.listOpe = new ArrayList<Character>();
		//this.listVal = new double[10];
		//this.listOpe = new char[10];
		this.achievement = 0;
	}

	public Calcul(String str) {
		// this.HandleCalc = new HandleCalcul(str);

		this.SetData(str);
	}

	public void SetListVal(int indice, double val) {
		this.listVal.add(indice, val);
	}

	public void SetListOpe(int indice, char ope) {
		this.listOpe.add(indice,ope);
	}

	public void SetData(String str) {

		int j = 0, indiceVal = 0, indiceOpe = 0;
		String tmp;

		for (int i = 0; i < str.length(); i++) {
			if ((str.charAt(i) > 47) && (str.charAt(i) < 58)) {
				j++;
				if (str.charAt(i + 1) == '$') {
					tmp = str.substring(i - j, i);
					SetListVal(indiceVal, Double.parseDouble(tmp));
				}
			} else {
				tmp = str.substring(i - j, i);
				SetListVal(indiceVal, Double.parseDouble(tmp));
				j = 0;
				if (str.charAt(i) != '$') {
					SetListOpe(indiceOpe, str.charAt(i));
					indiceVal++;
					indiceOpe++;
				}

			}

		}

	}

	public void Decoupage() {
		tailleParties = listVal.size() / nbParties;

		for (int i = 0; i < nbParties; i++) {
			for (int j = 0; j < tailleParties; j++) {
				matVal[i][j] = listVal.get(i * nbParties + j);
				matOpe[i][j] = listOpe.get(i * nbParties + j);
			}
		}
	}

	public void FragmentationCalcul() {
		for (int i = 0; i < this.nbParties; i++)
			this.listUnderCal.get(i).SetData(this.GetDataParties(i));
	}

	public int GetNbParties() {
		return nbParties;
	}

	public ArrayList<Double> GetDataParties(int numParties) {
		ArrayList<Double> data = new ArrayList<Double>(matVal[numParties].length);
		for (int i = 0; i < matVal[numParties].length; i++)
			data.add(i,matVal[numParties][i]);
		return data;
	}

	public void AffMat() {
		System.out.println("Matrice des Valeurs:");
		for (int i = 0; i < matVal.length; i++) {
			System.out.print("(");
			for (int j = 0; j < matVal[i].length; j++)
				System.out.print(matVal[i][j]);
			System.out.println(")");
		}
		System.out.println();
		System.out.println("Matrice des Operateurs::");
		for (int i = 0; i < matOpe.length; i++) {
			System.out.print("(");
			for (int j = 0; j < matOpe[i].length; j++)
				System.out.print(matOpe[i][j]);
			System.out.println(")");
		}
	}

	public void AffListeVal() {
		System.out.println("Liste des valeurs: ");
		for (int i = 0; i < listVal.size(); i++) {
			System.out.print(listVal.get(i) + " ");
		}
	}

	public void AffListeOpe() {
		System.out.println("Liste des operateurs: ");
		for (int i = 0; i < listOpe.size(); i++) {
			System.out.print(listOpe.get(i) + " ");
		}
	}

}

package calcul;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import client.Client;
import client.ClientList;

public class Calcul {

	public HandleCalcul handleCalcul;
		
	private int idCalcul;
	private int nbParties;
	private int idClient;
	private Boolean isOver;

	private double res;

	private ArrayList<UnderCalcServ> listUnderCal;

	private int achievement;

	private double[][] matVal;
	private char[][] matOpe;

	private ArrayList<Double> listVal;
	private ArrayList<Character> listOpe;
	private ArrayList<Double> listUndRes;
	private ArrayList<Client> listClient;


	// private double[] listVal;
	// private char[] listOpe;

	public Calcul() {
		this.isOver = false;
	}
	
	public Boolean getIsOver() {
		return isOver;
	}

	public int getUserIdFromUnderCalcId(int underCalcId){
		int userid = -1;
		try{
			userid = this.listUnderCal.stream().filter(u -> u.getUCalcId() == underCalcId).findFirst().get().getIdUser();
		} catch(NoSuchElementException e){
			e.printStackTrace();
		}
		return userid;
	}
	
	public Client getClientFromUserId(int userid){
		Client client = null;
		try{
			client = this.listClient.stream().filter(c -> c.getUserid() == userid).findFirst().get();
		}catch(NoSuchElementException e){
			e.printStackTrace();
		}
		return client;
	}

	public Calcul(int idClient, int idCalc, HandleCalcul hCalc, String calc) {

		this.idCalcul = idCalc;
		this.idClient = idClient;
		this.handleCalcul = hCalc;

		this.nbParties = 4;
		this.achievement = 0;

		this.listUnderCal = new ArrayList<UnderCalcServ>(this.nbParties);

		this.listVal = new ArrayList<Double>(1);
		this.listOpe = new ArrayList<Character>(1);
		this.listUndRes = new ArrayList<Double>(this.nbParties);

		this.setData(calc);
		this.matVal = new double[this.nbParties][this.listVal.size() / this.nbParties + 1];
		this.matOpe = new char[this.nbParties + 1][this.listVal.size() / this.nbParties + 1];
	}

	public void setListVal(int indice, double val) {
		this.listVal.add(indice, val);
	}

	public void setListOpe(int indice, char ope) {
		this.listOpe.add(indice, ope);
	}

	public void setData(String str) {

		int j = 0, indiceOpe = 0;
		String tmp;

		for (int i = 0; i < str.length(); i++) {
			if (((str.charAt(i) > 47) && (str.charAt(i) < 58)) || (str.charAt(i) == '.')) {
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
					setListOpe(indiceOpe, str.charAt(i));
					indiceOpe++;
				}
			}
		}
	}

	public String getUCalForm(int indiceUCalc) {
		String uCalcForm;
		uCalcForm = this.listUnderCal.get(indiceUCalc).prepEnvoi();

		return uCalcForm;
	}
	
	public int getUCalcId (int indiceUCalc) {
		return this.listUnderCal.get(indiceUCalc).getUCalcId();
	}
	
	public int getIdCalcul() {
		return idCalcul;
	}

	public void decoupage() {
		int taillePartVal = this.listVal.size() / this.nbParties;
		int restePartVal = this.listVal.size() % this.nbParties;
		// System.out.println("taille : " + taillePartVal);
		// System.out.println("reste : " + restePartVal);
		// int taillePartOpe = this.listOpe.size() / this.nbParties;
		// int restePartOpe = this.listOpe.size() % this.nbParties;

		int indiceListVal = 0;
		int indiceListOpe = 0;

		// Matrice Val
		for (int i = 0; i < restePartVal; i++) {
			for (int j = 0; j < (taillePartVal + 1); j++) {
				this.matVal[i][j] = this.listVal.get(indiceListVal);
				indiceListVal++;
			}
		}

		for (int i = restePartVal; i < this.nbParties; i++) {
			for (int j = 0; j < taillePartVal; j++) {
				this.matVal[i][j] = this.listVal.get(indiceListVal);
				indiceListVal++;
			}
		}
		System.out.println("Ope");

		// Matrice OPE

		for (int i = 0; i < restePartVal; i++) {
			for (int j = 0; j < (taillePartVal - 1); j++) {
				// System.out.println("indice ope : " + indiceListOpe);
				this.matOpe[i][j] = this.listOpe.get(indiceListOpe);
				indiceListOpe++;
			}
			// System.out.println("ajout sur derniere ligne");
			this.matOpe[this.nbParties][i] = this.listOpe.get(indiceListOpe);
			indiceListOpe++;
		}

		for (int i = restePartVal; i < this.nbParties; i++) {
			for (int j = 0; j < taillePartVal - 1; j++) {
				// System.out.println("indice ope : " + indiceListOpe);
				this.matOpe[i][j] = this.listOpe.get(indiceListOpe);
				indiceListOpe++;
			}
			// this.matOpe[i][taillePartVal] = '$';
			if (indiceListOpe <= this.listOpe.size() - 1) {
				// System.out.println("ajout sur derniere ligne");
				this.matOpe[this.nbParties][i] = this.listOpe.get(indiceListOpe);
				indiceListOpe++;
			}
		}

	}

	public void fragmentationCalcul() {
		for (int i = 0; i < this.nbParties; i++) {
			
			// ID du SsCalcul = 10 + indice du ssCalc
			UnderCalcServ undCalcTmp = new UnderCalcServ(10+i,10+i,this.getDataParties(i),this.getOpeParties(i));
			//undCalcTmp.SetData(this.GetDataParties(i));
			//undCalcTmp.SetOpe(this.GetOpeParties(i));
			this.listUnderCal.add(i, undCalcTmp);
			
			/*
			 *  Recuperation des users a utiliser
			 *  set le userid dans les ssCalc:
			 *  this.listUnderCal.get(i).SetUserId(userid);
			 */
			listClient = ClientList.getInstance().getClientForCalcul(this.nbParties);
			this.listUnderCal.get(i).setUserId(listClient.get(i).getUserid());
		}
	}

	public int getNbParties() {
		return nbParties;
	}

	public void getUndRes() {
		for (int i = 0; i < this.nbParties; i++)
			this.listUndRes.add(i, this.listUnderCal.get(i).getRes());
	}

	public void setUndRes(int indiceUCalc, Double res) {
		this.listUnderCal.get(indiceUCalc).setResUCalc(res);
		this.incrementAchievment();
		
	}
	
	public double calcResFinal() {
		this.getUndRes();

		this.res = this.listUndRes.get(0);
		int m = this.listUndRes.size();

		for (int i = 1; i < m; i++) {
			switch (matOpe[m][i - 1]) {
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
					System.out.println("Division par zero: division non effectu�");
				else
					this.res *= this.listUndRes.get(i);
				break;
			default:
				break;
			}
		}
		return res;
	}

	public ArrayList<Double> getDataParties(int numParties) {
		ArrayList<Double> data = new ArrayList<Double>(matVal[numParties].length);
		for (int i = 0; i < matVal[numParties].length; i++)
			data.add(i, matVal[numParties][i]);
		return data;
	}

	public ArrayList<Character> getOpeParties(int numParties) {
		ArrayList<Character> ope = new ArrayList<Character>(matOpe[numParties].length);
		for (int i = 0; i < matOpe[numParties].length; i++)
			ope.add(i, matOpe[numParties][i]);
		return ope;
	}

	public void affMat() {
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
				System.out.print(matOpe[i][j] + " ");
			System.out.println(")");
		}
	}

	public void affListeVal() {
		System.out.println("Liste des valeurs: ");
		for (int i = 0; i < listVal.size(); i++)
			System.out.print(listVal.get(i) + " ");
		System.out.println();
	}

	public void affListeOpe() {
		System.out.println("Liste des operateurs: ");
		for (int i = 0; i < listOpe.size(); i++)
			System.out.print(listOpe.get(i) + " ");
		System.out.println();
	}
	
	public void incrementAchievment(){
		this.achievement ++;
		if(this.achievement > this.listUnderCal.size()){
			this.setRes(this.calcResFinal());
		}
	}

	public double getRes() {
		return res;
	}
	
	private void setRes(double res) {
		//Stocker le résultat final
		//L'envoyer au HandleClient pour le communiquer au client
		//Stocker le calcul dans la DB
		this.res = res;
		this.isOver = true;
		//handleCalcul;
		ClientList.getInstance().findClient(idClient).handleClient.sendResCalcul(idCalcul, res);
	}

}

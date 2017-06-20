package calcul;

import java.util.ArrayList;

public class UnderCalcServ extends UnderCalc {

	private int idUser;
	private int lastTimeUser;
	private int flag; // flag 0: en attente/ 1: en cours/ 2: fini

	public UnderCalcServ() {

		super();
		this.idUser = 0;
		this.lastTimeUser = 0;
		this.flag = 0;
	}

	public UnderCalcServ(int idUser, int idCalc, ArrayList<Double> data, ArrayList<Character> ope) {
		super(idCalc, data, ope);
		this.idUser = idUser;
		this.lastTimeUser = 0;
		this.flag = 0;
	}
	
	public String prepEnvoi() {

		StringBuilder sb = new StringBuilder("");
		
		sb.append(this.value.get(0));
		for(int i = 1;i<this.value.size() ;i++){
			sb.append("#");
			sb.append(this.value.get(i));
		}
		sb.append("&");
		sb.append(this.operator.get(0));
		for(int i = 1;i<this.operator.size() ;i++){
			sb.append("#");
			sb.append(this.operator.get(i));
		}
		 
		String s = sb.toString(); 
		
		return s;
	}
	
	public void setUserId(int i) {
		this.idUser=i;
	}
	
	public void setData(ArrayList<Double> data) {
		for (int i = 0; i < data.size(); i++)
			this.value.add(data.get(i));
	}

	public void setOpe(ArrayList<Character> ope) {
		for (int i = 0; i < ope.size(); i++)
			this.operator.add(i,ope.get(i));
	}
	
	public int getUCalcId () {
		return this.idUnderCalc;
	}
	
	
	public void setResUCalc (double res) {
		this.res=res;
	}
	
	public double getRes()
	{
		return this.res;
	}
	
	public void setFlagEtat(int f) {
		if ((f >= 0) && (f < 4))
			this.flag = f;
		else
			this.flag = 0;
	}

	public void setLastTimeUser() {
		// a faire

	}

}
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
	
	public String PrepEnvoi() {

		StringBuilder sb = new StringBuilder("");
		
		sb.append(this.val.get(0));
		for(int i = 1;i<this.val.size() ;i++){
			sb.append("#");
			sb.append(this.val.get(i));
		}
		sb.append("&");
		sb.append(this.ope.get(0));
		for(int i = 1;i<this.ope.size() ;i++){
			sb.append("#");
			sb.append(this.ope.get(i));
		}
		 
		String s = sb.toString(); 
		
		return s;
	}
	
	public void SetUserId(int i) {
		this.idUser=i;
	}
	
	public void SetData(ArrayList<Double> data) {
		for (int i = 0; i < data.size(); i++)
			this.val.add(data.get(i));
	}

	public void SetOpe(ArrayList<Character> ope) {
		for (int i = 0; i < ope.size(); i++)
			this.ope.add(i,ope.get(i));
	}
	
	public int GetUCalcId () {
		return this.idUnderCalc;
	}
	
	
	public void SetResUCalc (double res) {
		this.res=res;
	}
	
	public double GetRes()
	{
		
		return this.res;
	}
	
	public void SetFlagEtat(int f) {
		if ((f >= 0) && (f < 4))
			this.flag = f;
		else
			this.flag = 0;
	}

	public void SetLastTimeUser() {
		// a faire

	}

}
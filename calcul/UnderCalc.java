package calcul;

import java.util.ArrayList;

public abstract class UnderCalc {
	
	protected int idUnderCalc;
	protected ArrayList<Double> val;
	protected ArrayList<Character> ope;
	protected double res;
	

public UnderCalc()
{
	this.idUnderCalc=0;
	this.val = new ArrayList<Double>() ;
	this.ope = new ArrayList<Character>() ;
	this.res=0;
}
public UnderCalc(int id, ArrayList<Double> data, ArrayList<Character> ope)
{
	this.idUnderCalc=id;
	this.val = new ArrayList<Double>(data);
	this.ope = new ArrayList<Character>(ope);
	this.res=0;
}

}


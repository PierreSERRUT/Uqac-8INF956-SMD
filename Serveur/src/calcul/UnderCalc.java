package calcul;

import java.util.ArrayList;

public abstract class UnderCalc {
	
	protected int idUnderCalc;
	protected ArrayList<Double> value;
	protected ArrayList<Character> operator;
	protected double res;
	

public UnderCalc()
{
	this.idUnderCalc=0;
	this.value = new ArrayList<Double>() ;
	this.operator = new ArrayList<Character>() ;
	this.res=0;
}
public UnderCalc(int id, ArrayList<Double> data, ArrayList<Character> ope)
{
	this.idUnderCalc=id;
	this.value = new ArrayList<Double>(data);
	this.operator = new ArrayList<Character>(ope);
	this.res=0;
}

}


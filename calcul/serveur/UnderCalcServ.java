package serv;

public class UnderCalcServ {



private int idUnderCalc;
private double [] data;
private char [] ope;
private double res;

public UnderCalcServ()
{
	
}

public void SetData(double[] data)
{
	for(int i = 0; i<data.length;i++)
		this.data[i]=data[i];
}

public void SetOpe(char[] ope)
{
	for(int i = 0; i<data.length;i++)
		this.ope[i]=ope[i];
}

}
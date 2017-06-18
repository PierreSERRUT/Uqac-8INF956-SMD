package mob;

public class UnderCalcMob {

protected double [] val;
protected char [] ope;
protected double res;

public double CalcUnderCalc ()
{
	res=val[0];
	int m =	val.length;
	
	for(int i = 1; i<m ;i++)
	{
		switch(ope[i-1]){
			case '+':
				res+=val[i];
				break;
			case '-':
				res-=val[i];
				break;
			case '*':
				res*=val[i];
				break;
			case '/':
				if(ope[i]==0)
					System.out.println("Division par zero: division non effectué");
				else 
					res*=val[i];
				break;
			default:
				break;
		}	
	}
	return res;
}
}

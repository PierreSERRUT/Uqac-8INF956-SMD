package serv;

public class MatriceCalcul {

	private double [][] matVal;
	private char [][] matOpe;
	private double [] listVal;
	private char [] listOpe;
	private int tailleParties;
	private int nbParties;
	
public MatriceCalcul ()
{
	
	matVal=null;
	matOpe=null;
	listVal= new double[20];
	listOpe = new char[20];
	tailleParties = 1; 
	nbParties = 4;
}

public void SetListVal (int indice, double val)
{
	this.listVal[indice]=val;
}
public void SetListOpe (int indice, char ope)
{
	this.listOpe[indice]=ope;
}
public void AffMat()
{
	System.out.println("Matrice des Valeurs:");
	for(int i=0;i<matVal.length;i++)
	{
		System.out.print("(");
		for(int j=0;j<matVal[i].length;j++)
			System.out.print(matVal[i][j]);
		System.out.println(")");
	}
	System.out.println();
	System.out.println("Matrice des Operateurs::");
	for(int i=0;i<matOpe.length;i++)
	{
		System.out.print("(");
		for(int j=0;j<matOpe[i].length;j++)
			System.out.print(matOpe[i][j]);
		System.out.println(")");
	}
}
public void AffListeVal()
{
	System.out.println("Liste des valeurs: ");
	for(int i = 0; i< listVal.length;i++)
	{
		System.out.print(listVal[i] + " ");
	}
}
public void AffListeOpe ()
{
	System.out.println("Liste des operateurs: ");
	for(int i = 0; i< listOpe.length;i++)
	{
		System.out.print(listOpe[i] + " ");
	}
}

public void SetData (String str)
{
	
	int j=0,indiceVal=0,indiceOpe=0;
	String tmp;
	
	for(int i=0; i<str.length();i++)
	{
		if((str.charAt(i)>47)&&(str.charAt(i)<58))
		{
			j++;
			if(str.charAt(i+1)=='$')
			{
				tmp=str.substring(i-j, i);
				SetListVal(indiceVal, Double.parseDouble(tmp));
			}
		}
		else
		{
			tmp=str.substring(i-j, i);
			SetListVal(indiceVal, Double.parseDouble(tmp));
			j=0;
			if(str.charAt(i)!='$')
			{
				SetListOpe(indiceOpe, str.charAt(i));
				indiceVal++;
				indiceOpe++;
			}
			
		}
		
	}
		
}

public void Decoupage()
{
	tailleParties = listVal.length / nbParties;
	
	for(int i = 0; i < nbParties; i++ )
	{
		for(int j = 0; j <tailleParties;j++ )
		{
		matVal[i][j]=listVal[i*nbParties+j];
		matOpe[i][j]=listOpe[i*nbParties+j];
		}
	}
	
}
public int GetNbParties()
{
	return nbParties;
}
public double [] GetDataParties(int numParties)
{
	double [] data = new double [matVal[numParties].length];
	for(int i = 0; i<matVal[numParties].length;i++)
		data[i]=matVal[numParties][i];
	return data;
}


}
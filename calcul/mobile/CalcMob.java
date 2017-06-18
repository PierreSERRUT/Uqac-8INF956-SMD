package mob;

import serv.MatriceCalcul;

public class CalcMob {

	public MatriceCalcul ValStringToMat (String str)
	{
		int j=0,indiceVal=0,indiceOpe=0;
		MatriceCalcul mat = new MatriceCalcul();
		String tmp;
		for(int i=0; i<str.length();i++)
		{
			if(((str.charAt(i)>47)&&(str.charAt(i)<58))||(str.charAt(i)==44))
			{
				j++;
				if(str.charAt(i+1)=='$')
				{
					tmp=str.substring(i-j, i);
					mat.SetListVal(indiceVal, Double.parseDouble(tmp));
				}
			}
			else
			{
				tmp=str.substring(i-j, i);
				mat.SetListVal(indiceVal, Double.parseDouble(tmp));
				j=0;
				if(str.charAt(i)!='$')
				{
					mat.SetListOpe(indiceOpe, str.charAt(i));
					indiceVal++;
					indiceOpe++;
				}
				
			}
			
		}

		return mat;
	}
	
}

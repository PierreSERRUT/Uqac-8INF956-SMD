package serv;

public class HandleCalcul {
	
	private MatriceCalcul mat;
	private String messageCalc;
	private int choixNbuser;
	private UnderCalcServ [] listUCalc;
	
	
public HandleCalcul(String str) {
	
	listUCalc= new UnderCalcServ [10];
	mat = new MatriceCalcul();
	messageCalc = RecuperationCalc(str);
	
	MiseEnFormeDonnees();
	FragmentationCalcul();
	
}
	
public String RecuperationCalc(String str)
{
	
	//Recuperation du calcul a partir du 
	//message transmis par le serveur 
	// A FAIRE

	
	return str;
}

public void MiseEnFormeDonnees()
{
	mat.SetData(messageCalc);	
	mat.Decoupage();
	mat.AffMat();
}
	

public void FragmentationCalcul ()
{	
	for(int i=0; i<mat.GetNbParties();i++)
		listUCalc[i].SetData(mat.GetDataParties(i));
}

public void EnvoiUnderCalc()
{
	
}
}

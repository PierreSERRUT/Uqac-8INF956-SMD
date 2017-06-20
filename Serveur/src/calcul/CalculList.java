package calcul;

import java.util.LinkedHashMap;

public class CalculList {

	private static CalculList uniqueCalculList;
	
	private LinkedHashMap<Integer, Calcul> listCurrentCalc;
	
	private CalculList(){
		this.listCurrentCalc = new LinkedHashMap<Integer, Calcul>();
	}
	
	public static synchronized CalculList getInstance(){
		if(uniqueCalculList == null){
			uniqueCalculList = new CalculList();
		}
		return uniqueCalculList;
    }
	
	public void addCalcul(Calcul calcul){
		this.listCurrentCalc.put(calcul.getIdCalcul(), calcul);
	}
	
	public void addResUnderCalcul(int idCalcul, int idUnderCalcul, Double res){
		this.listCurrentCalc.get(idCalcul).setUndRes(idUnderCalcul, res);
	}	
}

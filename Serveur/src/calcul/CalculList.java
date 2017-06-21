package calcul;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import client.Client;
import client.ClientList;
import db.HandleDB;


public class CalculList {
	
	//ancien:
	//private ArrayList<Calcul> listCurrentCalc;

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
		//System.out.println("add res  idCalcul : "+ idCalcul + " idUnderCalcul : "+ idUnderCalcul + " repSsCalcul : "+res );
		//System.out.println(listCurrentCalc.size());
		//System.out.println("get"+listCurrentCalc.get(idCalcul));
		this.listCurrentCalc.get(idCalcul).setUndRes(idUnderCalcul, res);
	}
	
	public void calculOver(int calculId){
		HandleDB.getInstance().addCalculOver(listCurrentCalc.get(calculId));
	}
	
}

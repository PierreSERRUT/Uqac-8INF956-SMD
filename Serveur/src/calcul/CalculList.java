package calcul;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import client.Client;
import client.ClientList;


public class CalculList {
	
	//ancien:
	//private ArrayList<Calcul> listCurrentCalc;

	private static CalculList uniqueCalculList;
	
	private LinkedHashMap<Integer, Calcul> listCurrentCalc;

	public static synchronized CalculList getInstance(){
		if(uniqueCalculList == null){
			uniqueCalculList = new CalculList();
		}
		return uniqueCalculList;
    }
	
}

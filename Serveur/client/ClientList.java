package client;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class ClientList {
	
	private LinkedHashMap<Client, Date> listClientCo;
	private LinkedHashMap<Client, Date> listClientDispo; // ArrayList de tuple (pour le temps)
	private int nbClientCoMax;
	
	public ClientList(){
		this.listClientCo = new LinkedHashMap<Client, Date>();
		this.listClientDispo = new LinkedHashMap<Client, Date>();
		this.nbClientCoMax = 5;
	}
	
	public ClientList(int MaxClient){
		this.listClientCo = new LinkedHashMap<Client, Date>();
		this.listClientDispo = new LinkedHashMap<Client, Date>();
		this.nbClientCoMax = MaxClient;
	}
	
	
	public void addConnect(Client client){
		this.listClientDispo.remove(client);
		this.listClientCo.remove(client);
		this.listClientCo.put(client, new Date());
		
		updateCoDispo();
		updateLongDispo();
	}
	
	private void updateCoDispo(){
		if(this.listClientCo.size() > this.nbClientCoMax){
			int nbTransition = this.listClientCo.size() - this.nbClientCoMax;			
			
			Set<Entry<Client, Date>> setClientCo = this.listClientCo.entrySet();
			Iterator<Entry<Client, Date>> iteratorClientCo = setClientCo.iterator();
			
			for (int i = 0; i < nbTransition; i++) {
				Entry<Client, Date> pairClientDate = iteratorClientCo.next();
				this.listClientDispo.put(pairClientDate.getKey(), pairClientDate.getValue());
				this.listClientCo.remove(pairClientDate.getKey());
			}
		}		
	}
	
	// Permet de "clean" la liste dispo si les clients n'ont pas pingé depuis 30 min
	private void updateLongDispo(){
		Set<Entry<Client, Date>> setClientDispo = this.listClientDispo.entrySet();
		Iterator<Entry<Client, Date>> iteratorClientDispo = setClientDispo.iterator();
		
		while(iteratorClientDispo.hasNext()){
			Entry<Client, Date> pairClientDate = iteratorClientDispo.next();
			long dateNow = new Date().getTime();					
			if(pairClientDate.getValue().getTime() - dateNow > 1800000 ){ // 1000 = 1s / 60 000 = 1min / 1 800 000 = 30min
				this.listClientDispo.remove(pairClientDate.getKey());
			}
			else{
				break;
			}
		}	
	}	
}

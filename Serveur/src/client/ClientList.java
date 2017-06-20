package client;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class ClientList {
		
	private static ClientList uniqueClientList;
	
	private LinkedHashMap<Integer, Client> listClientCo;
	private LinkedHashMap<Integer, Client> listClientDispo;
	private final int nbClientCoMax = 5;
	
	private ClientList(){
		this.listClientCo = new LinkedHashMap<Integer, Client>();
		this.listClientDispo = new LinkedHashMap<Integer, Client>();
	} 
	
	// Méthode statique qui sert de pseudo-constructeur (utilisation du mot clef "synchronized" pour le multithread).
    public static synchronized ClientList getInstance(){
		if(uniqueClientList == null){
			uniqueClientList = new ClientList();
		}
		return uniqueClientList;
    }
	
	public synchronized void  addConnect(Client client){
		decoClient(client.getUserid());
		client.setLastCo(new Date());
		
		this.listClientCo.put(client.getUserid(), client);
		
		updateCoDispo();
		updateLongDispo();
	}
	
	// Permet de passer des client de Co à Dispo
	private void updateCoDispo(){
		if(this.listClientCo.size() > this.nbClientCoMax){
			int nbTransition = this.listClientCo.size() - this.nbClientCoMax;			
			
			Set<Entry<Integer, Client>> setClientCo = this.listClientCo.entrySet();
			Iterator<Entry<Integer, Client>> iteratorClientCo = setClientCo.iterator();
			
			for (int i = 0; i < nbTransition; i++) {
				Entry<Integer, Client> pairIdClient = iteratorClientCo.next();
				this.listClientDispo.put(pairIdClient.getKey(), pairIdClient.getValue());
				this.listClientCo.remove(pairIdClient.getKey());
			}
		}		
	}
	
	// Permet de "clean" la liste dispo si les clients n'ont pas pingé depuis 30 min
	private void updateLongDispo(){
		Set<Entry<Integer, Client>> setClientDispo = this.listClientDispo.entrySet();
		Iterator<Entry<Integer, Client>> iteratorClientDispo = setClientDispo.iterator();
		
		while(iteratorClientDispo.hasNext()){
			Entry<Integer, Client> pairIdClient = iteratorClientDispo.next();
			long dateNow = new Date().getTime();					
			if(pairIdClient.getValue().getLastCo().getTime() - dateNow > 1800000 ){ // 1000 = 1s / 60 000 = 1min / 1 800 000 = 30min
				this.listClientDispo.remove(pairIdClient.getKey());
			}
			else{
				break;
			}
		}	
	}
	
	public synchronized void decoClient(int idClient){
		this.listClientDispo.remove(idClient);
		this.listClientCo.remove(idClient);
	}
	
	public Client findClient(int idClient){
		Client tmp = this.listClientCo.get(idClient);
		if(tmp == null){
			tmp = this.listClientDispo.get(idClient);
		}
		return tmp;
	}
	
	public synchronized ArrayList<Client> getClientForCalcul(int nbClient){
		ArrayList<Client> listClient = new ArrayList<Client>();
		int iteratorCo = 0;
		int iteratorDispo = 0;
		
		Set<Entry<Integer, Client>> setClientCo = this.listClientCo.entrySet();
		Iterator<Entry<Integer, Client>> iteratorClientCo = setClientCo.iterator();
		
		Set<Entry<Integer, Client>> setClientDispo = this.listClientDispo.entrySet();
		Iterator<Entry<Integer, Client>> iteratorClientDispo = setClientDispo.iterator();
		
		if(listClientCo.size() > nbClient){
			iteratorCo = nbClient;
			iteratorDispo = 0;
		}
		else if(listClientCo.size() + listClientDispo.size() < nbClient){
			iteratorCo = listClientCo.size();
			iteratorDispo = listClientDispo.size();
		}
		else{
			iteratorCo = listClientCo.size();
			iteratorDispo = nbClient - listClientCo.size();
		}	
		for (int i = 0; i < iteratorCo; i++) {
			Entry<Integer, Client> pairIdClient = iteratorClientCo.next();
			listClient.add(pairIdClient.getValue());
			this.listClientCo.remove(pairIdClient.getKey());
		}
		for (int i = 0; i < iteratorDispo; i++) {
			Entry<Integer, Client> pairIdClient = iteratorClientDispo.next();
			listClient.add(pairIdClient.getValue());
			this.listClientCo.remove(pairIdClient.getKey());
		}	
		return listClient;
	}
}

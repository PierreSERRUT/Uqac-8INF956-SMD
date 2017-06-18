package client;

import java.util.ArrayList;

public class ClientList {
	
	private ArrayList<Client> listClientCo;
	private ArrayList<Client> listClientDispo; // ArrayList de tuple (pour le temps)
	private int nbClientCoMax;
	
	public ClientList(){
		this.listClientCo = new ArrayList<Client>();
		this.listClientDispo = new ArrayList<Client>();
		this.nbClientCoMax = 5;
	}
	
	public ClientList(int MaxClient){
		this.listClientCo = new ArrayList<Client>();
		this.listClientDispo = new ArrayList<Client>();
		this.nbClientCoMax = MaxClient;
	}
	
	
	public void addConnect(Client client){
		this.listClientDispo.remove(client);
		if(this.listClientDispo.contains(client)){
			this.listClientDispo.remove(client);
		}
		this.listClientDispo.add(client);
	}
	
	// Permet de passer des client de Co � Dispo
	private void updateCoDispo(){
		if(this.listClientCo.size() > this.nbClientCoMax){
			int nbTransition = this.listClientCo.size() - this.nbClientCoMax;
			for (int i = 0; i < nbTransition; i++) {
				this.listClientDispo.add(this.listClientCo.get(i));
				this.listClientCo.remove(i);
			}
		}
		// clean la listDispo
	}
	
	// Permet de "clean" la liste dispo si les clients n'ont pas ping� depuis 30 min
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
	
	public void decoClient(){
		
	}
	
}

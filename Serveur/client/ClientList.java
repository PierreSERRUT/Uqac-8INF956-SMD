package Serveur.client;

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
	
	public void UpdateCoDispo(){
		if(this.listClientCo.size() > this.nbClientCoMax){
			int nbTransition = this.listClientCo.size() - this.nbClientCoMax;
			for (int i = 0; i < nbTransition; i++) {
				this.listClientDispo.add(this.listClientCo.get(i));
				this.listClientCo.remove(i);
			}
		}
		// clean la listDispo
	}
	
}

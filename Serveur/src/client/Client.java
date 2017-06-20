package client;

import java.util.Date;

public class Client {

	public HandleClient handleClient;
	private int userId;
	private Date LastCo;
	
	public Client(int id, HandleClient hc){
		this.userId = id;
		this.handleClient = hc;
	}
	
	public Date getLastCo() {
		return LastCo;
	}
	public void setLastCo(Date lastCo) {
		LastCo = lastCo;
	}
	public int getUserid() {
		return userId;
	}
	
}

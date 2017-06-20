package client;

import java.util.Date;

public class Client {

	public HandleClient handleClient;
	private int userId;
	private Date lastCo;
	
	public Client(int id, HandleClient hc){
		this.userId = id;
		this.handleClient = hc;
	}
	
	public Date getLastCo() {
		return lastCo;
	}
	public void setLastCo(Date lastConnection) {
		lastCo = lastConnection;
	}
	public int getUserid() {
		return userId;
	}
	
}

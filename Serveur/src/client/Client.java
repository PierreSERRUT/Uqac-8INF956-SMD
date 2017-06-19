package client;

import java.util.Date;

public class Client {

	public HandleClient handleClient;
	private int userid;
	private Date LastCo;
	
	public Client(int id, HandleClient hc){
		this.userid = id;
		this.handleClient = hc;
	}
	
	public Date getLastCo() {
		return LastCo;
	}
	public void setLastCo(Date lastCo) {
		LastCo = lastCo;
	}
	public int getUserid() {
		return userid;
	}
	
}

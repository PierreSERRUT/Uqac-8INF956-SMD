package db;

import java.util.ArrayList;

public class DataBase {
	
	private ArrayList<User> userTable;
	private ArrayList<DataCalcul> calculTable;
	private ArrayList<SavePendingCalcul> saveTable;
	
	public DataBase(){
		this.userTable = new ArrayList<User>();
		this.calculTable = new ArrayList<DataCalcul>();
		this.saveTable = new ArrayList<SavePendingCalcul>();
	}
	
	
	public void addUser(User newUser){
		if(!isUserExist(newUser)){
			this.userTable.add(newUser);
		}
	}
	
	public Boolean isUserExist(User user){
		return this.userTable.contains(user);
	}
	
	public Boolean deleteUser(User user){
		return this.userTable.remove(user);
	}
	
	public void addCalcul(DataCalcul newCalcul){
		if(!isCalculExist(newCalcul)){
			this.calculTable.add(newCalcul);
		}
	}
	
	public Boolean isCalculExist(DataCalcul calcul){
		return this.calculTable.contains(calcul);
	}
	
	public Boolean deleteCalcul(DataCalcul calcul){
		return this.calculTable.remove(calcul);
	}
	
	public void addSave(SavePendingCalcul save){
		this.saveTable.add(save);
	}
	
	public void cleanSave(){
		if(this.saveTable.size() > 5){
			for (int i = 0; i < (this.saveTable.size() - 5); i++) {
				this.saveTable.remove(i);
			}
		}
	}
	
}

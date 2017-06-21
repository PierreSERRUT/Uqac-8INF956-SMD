package db;

import java.util.ArrayList;

import calcul.Calcul;

public class DataBase {
	
	private ArrayList<User> userTable;
	private ArrayList<Calcul> calculTable;
	private ArrayList<SavePendingCalcul> saveTable;
	
	public DataBase(){
		this.userTable = new ArrayList<User>();
		this.calculTable = new ArrayList<Calcul>();
		this.saveTable = new ArrayList<SavePendingCalcul>();
	}
	
	public User getUserById(int userId){
		for (User user : userTable) {
			if(user.id == userId){
				return user;
			}
		}
		return null;
	}
	
	public User addUser(User newUser){
		if(!isUserExist(newUser)){
			newUser.id = userTable.size();
			this.userTable.add(newUser);
			return newUser;
		}
		return null;
	}
	
	public Boolean isUserExist(User user){
		return this.userTable.contains(user);
	}
	
	public Boolean isUserExist(String pseudo, String mail){
		for (User user : userTable) {
			if(user.pseudo == pseudo || user.mail == mail){
				return true;
			}
		}
		return false;
	}
	
	public Boolean deleteUser(User user){
		return this.userTable.remove(user);
	}
	
	public void addCalcul(Calcul newCalcul){
		if(!isCalculExist(newCalcul)){
			this.calculTable.add(newCalcul);
		}
	}
	
	public Boolean isCalculExist(Calcul calcul){
		return this.calculTable.contains(calcul);
	}
	
	public Boolean deleteCalcul(Calcul calcul){
		return this.calculTable.remove(calcul);
	}
	
	public void addSave(SavePendingCalcul save){
		this.saveTable.add(save);
		cleanSave();
	}
	
	private void cleanSave(){
		if(this.saveTable.size() > 5){
			for (int i = 0; i < (this.saveTable.size() - 5); i++) {
				this.saveTable.remove(i);
			}
		}
	}
	
}

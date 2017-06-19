package db;

public class HandleDB {

	private DataBase db;
	
	// passer en static
	
	public HandleDB(){
		
	}
	
	public void initDB(){
		// Aller chercher le fichier de DB pour la construire ?
	}
	
	public boolean connexion(User user){
		boolean ok = false;
		User tmp = db.getUserById(user.id);
		if(tmp != null && tmp.password == user.password){
			ok = true;
		}
		return ok;
	}
	
	public int inscription(User newUser){
		User tmp = new User();
		if(!db.isUserExist(newUser.pseudo, newUser.mail)){
			tmp = db.addUser(newUser);
		}
		//La DB doit attribuer l'id à l'utilisateur
		return tmp.id;
		
	}	
}

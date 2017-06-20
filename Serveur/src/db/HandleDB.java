package db;

public class HandleDB {

	private DataBase db;
	private static HandleDB uniqueHandleDB;
	
	private HandleDB(){
		db = new DataBase();
	}
	
	private void initDB(){
		// Aller chercher le fichier de DB pour la construire (Pour faire de la persistance)
	}
	
	public static synchronized HandleDB getInstance(){
		if(uniqueHandleDB == null){
			uniqueHandleDB = new HandleDB();
		}
		return uniqueHandleDB;
    }
	
	
	public boolean connection(User user){
		boolean ok = false;
		User tmp = db.getUserById(user.id);
		if(tmp != null && tmp.password == user.password){
			ok = true;
		}
		return ok;
	}
	
	public int subscribe(User newUser){
		User tmp = new User();
		if(!db.isUserExist(newUser.pseudo, newUser.mail)){
			tmp = db.addUser(newUser);
		}
		//La DB doit attribuer l'id à l'utilisateur
		return tmp.id;
		
	}	
}

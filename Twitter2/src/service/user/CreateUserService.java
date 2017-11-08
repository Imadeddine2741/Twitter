package service.user;

import org.json.JSONObject; // représente une valeur d'objet JSON immuable(on ne peut pas le changer)

import servicetools.BCrypt;

public class CreateUserService {

	public static JSONObject createUser(String login,String mot_de_passe,String nom,String prenom,String email){
		if(login == null || mot_de_passe == null || nom==null || prenom == null || email == null){
			return servicetools.Tools.servicerefuse("problème d'argument", 100);
		}
		boolean is_user = servicetools.Tools.loginExits(login);
		if(is_user) return servicetools.Tools.servicerefuse("login exist dêja", 100);
		BCrypt bc=new BCrypt();
		String mdp =bc.hashpw(mot_de_passe, BCrypt.gensalt());
		System.out.println(mdp);
		boolean connexion_ok = servicetools.Tools.InsertUser(login,mdp,nom,prenom,email);
		if(!connexion_ok) return servicetools.Tools.servicerefuse("probleme a l'insertion ", 100);
		return servicetools.Tools.serviceaccepted("ok");	
	}
}

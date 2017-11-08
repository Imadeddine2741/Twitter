package service.user;

import java.util.List;

import org.json.JSONObject;

import servicetools.BCrypt;

public class AuthenticateUserService {
	
	public static JSONObject authenticateUser(String login,String mot_de_passe){
		
		if(login == null || mot_de_passe == null ){
			return servicetools.Tools.servicerefuse("probleme d'argument", 200);
		}
			boolean is_user = servicetools.Tools.loginExits(login);
			if(!is_user) return servicetools.Tools.servicerefuse("user n'exist pas", 100);
			String mdp=servicetools.Tools.getMDP(login);
			BCrypt bc=new BCrypt();
			boolean pass_user = bc.checkpw(mot_de_passe,mdp);
			//= servicetools.Tools.checkUser(login,mot_de_passe);
			if(!pass_user) return servicetools.Tools.servicerefuse("le mot de passe n'est pas coorecte", 150);
			int id_user = servicetools.Tools.getIDuser(login);
			String key = servicetools.Tools.insertSession(id_user,0);
		    List<Integer>follows = servicetools.Tools.listFriendsQuejeSui(key);
			boolean connexion_ok = servicetools.Tools.connexionUser(login);
			if(!connexion_ok) return servicetools.Tools.serviceacceptedKeyExist(key, id_user, login, follows, 200);
			return servicetools.Tools.serviceacceptedlogin(key,id_user,login,follows);	
	}
}

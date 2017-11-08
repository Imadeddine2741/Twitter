package service.user;

import org.json.JSONObject; // représente une valeur d'objet JSON immuable(on ne peut pas le changer)

public class LogoutService {
	
	public static JSONObject logout(String key){
		if(key == null){
			return servicetools.Tools.servicerefuse("probleme d'argument", 100);
		}
		boolean exist=servicetools.Tools.testSessionkey(key);
		if(!exist) return servicetools.Tools.servicerefuse("ce key n'existe pas ", 100);
		boolean sup = servicetools.Tools.suprimeSession(key);
		if(!sup) return servicetools.Tools.servicerefuse("probleme pendant la supprission  ", 100);
		return servicetools.Tools.serviceaccepted("deconnexion");
	}
}


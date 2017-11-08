package service.user;

import java.util.List;

import org.json.JSONObject;

public class IsConnected {
	
	
public static JSONObject isConnected(String key){
		
		if(key == null  ){
			return servicetools.Tools.servicerefuse("probleme d'argument", 100);
		}
		boolean key_existe = servicetools.Tools.keyExist(key);
		if(!key_existe) return servicetools.Tools.servicerefuse("key n'existe pas ", 150);
		String login = servicetools.Tools.login(key);
		int id_user = servicetools.Tools.getIDuser(login);
		List<Integer>follows = servicetools.Tools.listFriendsQuejeSui(key);
		return servicetools.Tools.serviceacceptedKeyExist(key, id_user, login, follows, 200);	
	}
}
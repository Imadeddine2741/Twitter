package service.user;

import org.json.JSONObject;

public class RemoveFriendService {

	public static JSONObject removeFriend(String key, String loginFriend) {
		
		if(key == null || loginFriend == null ){
			return servicetools.Tools.servicerefuse("probleme d'argument", 100);
		}
		boolean is_user = servicetools.Tools.keyExist(key);
		if(!is_user) return servicetools.Tools.servicerefuse("key n'exist pas", 100);
		int pass_user = servicetools.Tools.getIDuser(loginFriend);
		if(pass_user==0) return servicetools.Tools.servicerefuse("login friends n'est pas coorecte", 100);
		boolean removeFriend_ok = servicetools.Tools.removeFriends(key,loginFriend);
		if(!removeFriend_ok) return servicetools.Tools.servicerefuse("probleme à l'ajout d'un friend", 100);
		return servicetools.Tools.serviceacceptedAddORremove(key,loginFriend);	
	}
}

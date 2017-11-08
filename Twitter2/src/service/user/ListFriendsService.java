package service.user;

import java.util.List;

import org.json.JSONObject;

import servicetools.Tools;

public class ListFriendsService {
	
	public static JSONObject ListFriends(String key) {
		if(key == null ){
			return servicetools.Tools.servicerefuse("probleme d'argument", 100);
		}
		boolean is_user = servicetools.Tools.keyExist(key);
		if(!is_user) return servicetools.Tools.servicerefuse("key n'exist pas", 100);
		List<String> listFrienrs=  Tools.listFriends(key);
		return servicetools.Tools.serviceacceptedListesFriends(listFrienrs);

	}

}

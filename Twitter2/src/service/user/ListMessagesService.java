package service.user;

import org.json.JSONObject;

public class ListMessagesService {

	public static JSONObject ListMessages(String key) {

		if(key == null ){
			return servicetools.Tools.servicerefuse("probleme d'argument", 100);
		}
		boolean is_user = servicetools.Tools.keyExist(key);
		if(!is_user) return servicetools.Tools.servicerefuse("key n'exist pas", 100);
		String listesMessages = servicetools.Tools.listMassages(key);
		return servicetools.Tools.serviceaccepted(listesMessages);
	}

}

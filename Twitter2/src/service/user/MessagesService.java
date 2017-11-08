package service.user;

import org.json.JSONException;
import org.json.JSONObject;

public class MessagesService {

	public static JSONObject message(String key, String message) throws JSONException {
		if(key == null || message == null ){
			return servicetools.Tools.servicerefuse("probleme d'argument", 100);
		}
		boolean is_user = servicetools.Tools.testSessionkey(key);
		if(!is_user) return servicetools.Tools.servicerefuse("key n'exist pas", 100);
		//ajoutmessages = servicetools.Tools.addComments(key,message);
		//if(!ajoutmessages) return servicetools.Tools.servicerefuse("key n'exist pas", 100);
		return servicetools.Tools.addMessages(key, message);	
	}

}

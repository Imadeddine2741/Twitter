package service.user;

import org.json.JSONException;
import org.json.JSONObject;

public class CommentService {
	
	
	public static JSONObject comment(String key,String id_message, String texte) throws JSONException {
		if(key == null || id_message == null || texte == null){
			return servicetools.Tools.servicerefuse("probleme d'argument", 100);
		}
		boolean is_user = servicetools.Tools.testSessionkey(key);
		if(!is_user) return servicetools.Tools.servicerefuse("key n'exist pas", 100);
		return servicetools.Tools.CommentMessages(key, id_message, texte);
	}

}

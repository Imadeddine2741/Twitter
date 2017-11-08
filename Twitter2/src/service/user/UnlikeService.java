package service.user;

import org.json.JSONObject;

public class UnlikeService {
	
public static JSONObject unlike(String key,String id_message){
		
		if(key == null && key == id_message){
			return servicetools.Tools.servicerefuse("probleme d'argument", 200);
		}
			boolean is_user = servicetools.Tools.unlikeMessages(key, id_message);
			if(!is_user) return servicetools.Tools.servicerefuse("key n'exist pas", 100);
			return servicetools.Tools.serviceaccepted("bien");	
	}

}


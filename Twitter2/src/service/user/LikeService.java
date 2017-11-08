package service.user;

import org.json.JSONObject;

public class LikeService {

public static JSONObject like(String key,String id_message){
		
		if(key == null && key == id_message){
			return servicetools.Tools.servicerefuse("probleme d'argument", 200);
		}
			String like_user = servicetools.Tools.likeMessage(key, id_message);
			
			//if(!is_user) return servicetools.Tools.servicerefuse("key n'exist pas", 100);
			return servicetools.Tools.serviceaccepted(like_user);	
	}

}

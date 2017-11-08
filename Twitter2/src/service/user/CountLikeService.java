package service.user;

import org.json.JSONObject;

public class CountLikeService {
	
public static JSONObject countlike(String login){
		
		if(login == null){
			return servicetools.Tools.servicerefuse("probleme d'argument", 200);
		}
			boolean  likelogin = servicetools.Tools.loginExits(login);
			
			if(!likelogin) return servicetools.Tools.servicerefuse("login n'exist pas", 100);
			int nb=servicetools.Tools.countLike(login);
			return servicetools.Tools.serviceaccepted1(nb);
	}

}

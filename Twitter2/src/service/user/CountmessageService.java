package service.user;

import org.json.JSONObject;

public class CountmessageService {
	
public static JSONObject countmessage(String login){
		
		if(login == null){
			return servicetools.Tools.servicerefuse("probleme d'argument", 200);
		}
			boolean  likelogin = servicetools.Tools.loginExits(login);
			
			if(!likelogin) return servicetools.Tools.servicerefuse("login n'exist pas", 100);
			int nb=servicetools.Tools.counMessage(login);
			return servicetools.Tools.serviceaccepted1(nb);	
	}

}

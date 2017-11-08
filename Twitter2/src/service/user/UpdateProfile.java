package service.user;

import org.json.JSONException;
import org.json.JSONObject;

public class UpdateProfile {

	
public static JSONObject updateProfile(String id,String key,String lastname,String firstname,String email,String login) throws JSONException{
		
		if(key == null ){
			return servicetools.Tools.servicerefuse("probleme d'argument", 200);
		}
			boolean is_user = servicetools.Tools.keyExist(key);
			if(!is_user) return servicetools.Tools.servicerefuse("key n'exist pas", 100);
			boolean is_exist = servicetools.Tools.loginExits(login);
			if(is_exist) return servicetools.Tools.servicerefuselogin("login exist dêja", 100,login);
			boolean toto = servicetools.Tools.updateProfile(id, key, lastname, firstname, email, login);
			if(!toto) return servicetools.Tools.servicerefuse("key n'exist pas", 100);
			return servicetools.Tools.serviceacceptedprofile(lastname,firstname,email,login);		
	}

}


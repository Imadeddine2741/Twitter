package service.user;


import org.json.JSONObject;

public class Profile {
	
public static JSONObject profile(String key){
		
		if(key == null ){
			return servicetools.Tools.servicerefuse("probleme d'argument", 200);
		}
			boolean is_user = servicetools.Tools.keyExist(key);
			if(!is_user) return servicetools.Tools.servicerefuse("key n'exist pas", 100);
			String last_name = servicetools.Tools.last_name(key);;
			String first_name = servicetools.Tools.first_name(key);
			String login = servicetools.Tools.login(key);
			String email = servicetools.Tools.email(key);
			System.out.println(email);
			return servicetools.Tools.serviceacceptedprofile(last_name,first_name,email,login);	
	}

}

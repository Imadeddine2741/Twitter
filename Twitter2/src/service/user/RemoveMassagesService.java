package service.user;

import org.json.JSONObject;

public class RemoveMassagesService {

	public static JSONObject removeMassage(String key, String massageSup) {
			if(key == null || massageSup == null ){
				return servicetools.Tools.servicerefuse("probleme d'argument", 100);
			}
			boolean is_user = servicetools.Tools.testSessionkey(key);
			if(!is_user) return servicetools.Tools.servicerefuse("key n'exist pas", 100);
			boolean ajoutcomments = servicetools.Tools.supMessage(key,massageSup);
			if(!ajoutcomments) return servicetools.Tools.servicerefuse("key ou le message que vous vloulez supprimer n'exist pas", 100);
			return servicetools.Tools.serviceaccepted("vous avez bien supprimer votre message");	
		}
}

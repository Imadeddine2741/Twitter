package service.user;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class ListMessagesParLogin {

	
	public static List<JSONObject> ListMessages(String login) throws JSONException {

		List<JSONObject> listesMessages = servicetools.Tools.listesMassagesParlogin(login);
		return listesMessages;
	}
}

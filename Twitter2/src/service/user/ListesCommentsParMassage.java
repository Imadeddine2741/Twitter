package service.user;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class ListesCommentsParMassage {
	
	public static List<JSONObject> ListCommentParMessage(String id_messages) throws JSONException {

		List<JSONObject> listComments = servicetools.Tools.listesCommentsParMassages(id_messages);
		return listComments;
	}

}

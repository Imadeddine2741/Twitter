package service.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bd.Database;
import servicetools.BCrypt;
import servicetools.Tools;


/*
 * Service permettant de changer un mot de passe
 */
public class ChangePwService {

	public static String changePw(String key, String prec_pw, String new_pw) {
		Connection conn = null;
		PreparedStatement statement = null, st = null;
		ResultSet precPw = null;
		JSONObject result = new JSONObject();
		String userId = "";
		try {
			conn = Database.getMySQLConnection();
			userId = String.valueOf(Tools.getId(key));
			String query = "select mdp from  Users where id=?";
			statement = conn.prepareStatement(query);	
			if(userId != null) {
				statement.setInt(1, Integer.parseInt(userId));
				precPw = statement.executeQuery();
				System.out.println(""+precPw);
				if(precPw.next()) {
					System.out.println(""+precPw.getString("mdp"));
					if(BCrypt.checkpw(prec_pw, precPw.getString("mdp"))) {
						String update = "UPDATE Users SET mdp=? where id=?";
						st = conn.prepareStatement(update);
						st.setString(1, BCrypt.hashpw(new_pw, BCrypt.gensalt()));
						st.setInt(2, Integer.parseInt(userId));
						if(st.executeUpdate() > 0) 
							result.put("code", "100");
							//result.put("ok", "Password changed successfully.");
						else
							result.put("code", "150");
							//result.put("erreur", "Unexpected error while changing password.");
					} else {
						result.put("code", "200");
						//result.put("erreur", "Invalid precedent password.");
					}
				} else {
					result.put("code", "100");
					//result.put("erreur", "No password.");
				}
			} else {
				result.put("erreur", "Invalid session id.");
				//result.put("erreur", "Invalid session id.");
			}
		} catch (SQLException e) {
			if (e.getErrorCode() == 0 && e.toString().contains("CommunicationsException"))
				return changePw(key, prec_pw, new_pw);
			else
				return Tools.erreurSQL + e.getMessage();
		} catch (JSONException e) {
			return Tools.erreurJSON;
		}
		try {
			if (statement != null)
				statement.close();
			if(st != null)
				st.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {}

		return result.toString();
	}
}

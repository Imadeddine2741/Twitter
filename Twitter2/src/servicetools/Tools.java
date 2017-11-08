package servicetools;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import bd.Database;

import org.json.JSONException;
import org.json.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoException;


public class Tools {
	
	public static String erreurSQL = "{ \"erreur\" : \"Une erreur inattendue s'est produite(SQL). Veuillez verifier et réessayer.\" }";
	public static String erreurJSON = "{ \"erreur\" : \"Une erreur inattendue s'est produite(JSON). Veuillez verifier et réessayer.\" }";
	public static String erreurParam = "{ \"erreur\": \"Erreur paramêtres. Veuillez vérifier et réessayer.\" }";
	
	public static JSONObject serviceaccepted(String message) {
		try{
			JSONObject obj = new JSONObject();
			obj.put("message",message);

			return obj;
		}catch(JSONException e1){
			e1.printStackTrace();;
		}
		return null;
	}
	public static JSONObject serviceaccepted1(int message) {
		try{
			JSONObject obj = new JSONObject();
			obj.put("message",message);

			return obj;
		}catch(JSONException e1){
			e1.printStackTrace();;
		}
		return null;
	}
	public static JSONObject serviceacceptedMessage(String message) {
		try{
			JSONObject obj = new JSONObject();
			obj.put("",message);
			return obj;
		}catch(JSONException e1){
			e1.printStackTrace();;
		}
		return null;
	}
	public static JSONObject servicerefuse(String message,int code) {
		try{
			JSONObject obj = new JSONObject();
			obj.put("message",message);
			obj.put("code",code);
			return obj;
		}catch(JSONException e1){
			e1.printStackTrace();;
		}
		return null;
	}
	public static JSONObject servicerefuselogin(String message,int code,String login) {
		try{
			JSONObject obj = new JSONObject();
			obj.put("message",message);
			obj.put("code",code);
			obj.put("login",login);
			return obj;
		}catch(JSONException e1){
			e1.printStackTrace();;
		}
		return null;
	}
	public static JSONObject serviceacceptedlogin(String key, int id,String login,List<Integer> follows) {
		try{
			JSONObject obj = new JSONObject();
			obj.put("key",key);
			obj.put("login",login);
			obj.put("id",id);
			obj.put("follows",follows);
			return obj;
		}catch(JSONException e1){
			e1.printStackTrace();
		}
		return null;
	}
	public static JSONObject serviceacceptedKeyExist(String key, int id,String login,List<Integer> follows,int code) {
		try{
			JSONObject obj = new JSONObject();
			obj.put("key",key);
			obj.put("login",login);
			obj.put("id",id);
			obj.put("follows",follows);
			obj.put("code",code);
			return obj;
		}catch(JSONException e1){
			e1.printStackTrace();
		}
		return null;
	}
	public static JSONObject serviceacceptedprofile(String last_name, String  first_name,String email,String login) {
		try{
			JSONObject obj = new JSONObject();
			obj.put("last_name",last_name);
			obj.put("first_name",first_name);
			obj.put("email",email);
			obj.put("login",login);
			return obj;
		}catch(JSONException e1){
			e1.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject serviceacceptedListesFriends(List<String> message) {
		try{
			JSONObject obj = new JSONObject();
			obj.put("listes_friends",message);
			return obj;
		}catch(JSONException e1){
			e1.printStackTrace();;
		}
		return null;
	}
	public static JSONObject serviceacceptedAddORremove(String key, String loginFriend) {
		try{
			JSONObject obj = new JSONObject();
			obj.put("key",key);
			obj.put("loginFriend",loginFriend);
			return obj;
		}catch(JSONException e1){
			e1.printStackTrace();;
		}
		return null;
	}
	public static boolean updateProfile(String id,String key,String lastname,String firstname,String email,String login) throws JSONException {
		boolean retour=false;
		int res=0;
		int id_us=0;
		Connection co=null;
		Statement st=null;
		DBCollection coll = null;
		DB db=null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		try {
			st = co.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String query = "update Users set email='" + email + "', nom='" + lastname + "', prenom='" + firstname + "', login='" + login +"' where id=" +id;
		try {
			res=st.executeUpdate(query);	
			if(res != 0){
				try {
					db = Database.getMongoDB();			
				}catch(MongoException e){
					e.getStackTrace();
				}
				//db.getCollection("messages").remove(new BasicDBObject());//pour vider la collection
				coll=db.getCollection("messages");
				id_us =servicetools.Tools.getId(key);
				System.out.println(""+id_us+" "+id);
				DBCursor cursor = coll.find();
				System.out.println(cursor.count());
				while (cursor.hasNext()) {
					JSONObject obj=new JSONObject();
					DBObject dbObject = cursor.next();
					int id_user = (Integer) dbObject.get("id_user");
					System.out.println(id_us+" "+id_user);
					//if (id_us == id_user){
						System.out.println("je rentre");
						BasicDBObject updateQuery = new BasicDBObject();
						updateQuery.append("$set",new BasicDBObject().append("login_user", login));
						BasicDBObject searchQuery = new BasicDBObject();
						searchQuery.append("id_user", id_us);
						coll.updateMulti(searchQuery, updateQuery);	
					//}
				}
			}else{
					return false;
				}
				retour=true;
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;		
	}
	public static boolean InsertUser(String login,String mot_de_passe,String nom,String prenom,String email) {
		boolean retour=false;
		int res=0;
		Connection co=null;
		Statement st=null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		try {
			st = co.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String query="INSERT INTO Users (`login`, `nom`, `prenom`, `mdp`, `email`) Values ('"+ login + "','" + nom + "','" + prenom + "','" + mot_de_passe+ "','" + email + "')";
		try {
			res=st.executeUpdate(query);	
			if(res != 0){
				System.out.println("je suis la "+res);
				retour=true;
			}else{
				retour=false;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;		
	}
	public static boolean loginExits(String login){
		boolean retour =false;
		Connection co=null;
		Statement st=null;
		try {
			co = Database.getMySQLConnection();
		}catch(SQLException e){
			e.getStackTrace();
		}
		try{
			st = co.createStatement();
		}catch(SQLException e1){
			e1.getStackTrace();
		}
		String query="Select login from Users where login='"+login+"'";
		System.out.println(query);
		try {
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				retour=true;
			}else{
				retour=false;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;	

	}
	public static boolean checkUser(String login,String mdp){
			boolean retour=false;
			ResultSet res=null;
			Connection co=null;
			Statement st=null;
			try {
				co = Database.getMySQLConnection();
			} catch (SQLException e) {
				e.getStackTrace();
			}
			try{
				st = co.createStatement();
			}catch(SQLException e1){
				e1.getStackTrace();
			}
			String query="Select id from Users where login='"+login+"' and mdp='"+mdp+"'";
			try{
				res=st.executeQuery(query);	
				if(res.next()){
					retour=true;
				}else{
					retour=false;
				}
			}catch(SQLException e2){
				e2.getStackTrace();
			}
			try{
				if(st!=null) st.close();
				if(co!= null) co.close();
			} catch (SQLException e) {
				return retour;
			}
			return retour;	
	}
	public static boolean connexionUser(String login){
		boolean retour=false;
		ResultSet res1=null;
		ResultSet res2=null;
		Connection co=null;
		Statement st=null;
		int id1=0;
		try {
			 co = Database.getMySQLConnection();	
		}catch (SQLException e) {
			e.getStackTrace();
		}
		try{	
			st = co.createStatement();
		}catch (SQLException e1) {
			e1.getStackTrace();
		}
		String query1="Select id from Users where login='"+login+"'";
		try{
			res1=st.executeQuery(query1);			
			if(res1.next()){
				id1=res1.getInt("id");
				String query2="Select * from Session where id_user="+id1;
			    res2=st.executeQuery(query2);	
				if(res2.next()){
					retour=true;
				}
				else{
					retour=false;
				}
			}else{
				retour=false;
			}
		}catch (SQLException e3) {
			e3.getStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;	
	}

	public static int getIDuser(String login){
		int id_user=0;
		ResultSet res1=null;
		Connection co=null;
		Statement st=null;
		try {
			 co = Database.getMySQLConnection();
		}catch (SQLException e) {
			 e.printStackTrace();
		}
		try {
			 st = co.createStatement();
		}catch (SQLException e1) {
			 e1.printStackTrace();
		}
		String query1="Select id from Users where login='"+login+"'";
		try{
			res1=st.executeQuery(query1);	
			if(res1.next()){
				id_user=res1.getInt("id");
			}else{
				id_user=0;
			}
		}catch (SQLException e2) {
			e2.getStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return id_user;
		}
		return id_user;	
	}
	public static String getMDP(String login){
		String id_user="";
		ResultSet res1=null;
		Connection co=null;
		Statement st=null;
		try {
			 co = Database.getMySQLConnection();
		}catch (SQLException e) {
			 e.printStackTrace();
		}
		try {
			 st = co.createStatement();
		}catch (SQLException e1) {
			 e1.printStackTrace();
		}
		String query1="Select mdp from Users where login='"+login+"'";
		try{
			res1=st.executeQuery(query1);	
			if(res1.next()){
				id_user=res1.getString("mdp");
			
			}else{
				id_user="";
			}
		}catch (SQLException e2) {
			e2.getStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return id_user;
		}
		return id_user;	
	}
	private static String getLogin(int id_user) {
		String login ="";
		ResultSet res1=null;
		Connection co=null;
		Statement st=null;
		try {
			 co = Database.getMySQLConnection();
		}catch (SQLException e) {
			 e.printStackTrace();
		}
		try {
			 st = co.createStatement();
		}catch (SQLException e1) {
			 e1.printStackTrace();
		}
		String query1="Select login from Users where id='"+id_user+"'";
		try{
			res1=st.executeQuery(query1);	
			if(res1.next()){
				login=res1.getString("login");
			}else{
				id_user=0;
			}
		}catch (SQLException e2) {
			e2.getStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return login;
		}
		return login;	
	}
	public static String insertSession(int id_user,int root) {
		String key_session="";
		Connection co=null;
		Statement st=null;
		try {
		 	co = Database.getMySQLConnection();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
		 	st = co.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		key_session = Functions.keySession();
		String query="INSERT INTO Session (`key_session`,`id_user`, `date`, `root`) Values ('"+ key_session + "','"+ id_user + "','" + Functions.getTimestamap() + "','" + root + "')";
		try{
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return key_session;
		}
		return key_session;	
	}
	public static boolean suprimeSession(String key){
		boolean retour=false;
		Connection co=null;
		Statement st=null;
		try {
		 	co = Database.getMySQLConnection();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		try{
		 	st = co.createStatement();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		String query="delete from Session where key_session ='"+ key+"'";
		try{
		int res = st.executeUpdate(query);
		if(res!=0){
				retour=true;
			}else{
				retour=false;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;	
	}
	public static boolean testSessionkey(String key){
		boolean retour =false;
		Connection co=null;
		Statement st=null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select key_session from Session where key_session='"+key+"'";
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				retour=true;
			}else{
				retour=false;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}
	public static int getId(String key){
		ResultSet res = null;
		Connection co=null;
		Statement st=null;
		int id = -1;
		try{
			 co = Database.getMySQLConnection(); 
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		String query = "select id_user from Session where key_session='"+key+"'";
		try{
			st = (Statement) co.createStatement();
		} catch (SQLException e1) {
			e1.getStackTrace();
		}
		try {
			res = st.executeQuery(query);
			if(res.next()){
				id=res.getInt("id_user");
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if (st!= null)  st.close();
			if (co != null) co.close();
		} catch (SQLException e) {
			return -1;
		}
		return id;
	}
	public static long getDate(String key){
		ResultSet keyToDate = null;
		Connection co=null;
		Statement st=null;
		long date = -1;
		try{
			 co = Database.getMySQLConnection(); 
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		String query = "select date from Session where key_session='"+key+"'";
		try{
			st = (Statement) co.createStatement();
		} catch (SQLException e1) {
			e1.getStackTrace();
		}
		try {
			keyToDate = st.executeQuery(query);
			if(keyToDate.next()) {
				Timestamp da = keyToDate.getTimestamp("date");
				date = da.getTime();
			}else{
				date=-1;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if (st!= null)  st.close();
			if (co != null) co.close();
		} catch (SQLException e) {
			return -1;
		}
		return date;
	}
	public static String email(String key){
		ResultSet res1 = null;
		ResultSet res2 = null;
		Connection co=null;
		Statement st=null;
		String email = "";
		try{
			 co = Database.getMySQLConnection(); 
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		String query = "select id_user from Session where key_session='"+key+"'";
		try{
			st = (Statement) co.createStatement();
		} catch (SQLException e1) {
			e1.getStackTrace();
		}
		try {
			res1 = st.executeQuery(query);
			if(res1.next()) {
				String query1 = "select email from Users where id="+res1.getInt("id_user");
				res2=st.executeQuery(query1);	
				if(res2.next()){
					System.out.println(res2.getString("email"));
					email =res2.getString("email");
				}
				else{
					email="";
				}
			}else{
				email="";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if (st!= null)  st.close();
			if (co != null) co.close();
		} catch (SQLException e) {
			return email;
		}
		return email;
	}
	public static String login(String key){
		ResultSet res1 = null;
		ResultSet res2 = null;
		Connection co=null;
		Statement st=null;
		String login = "";
		try{
			 co = Database.getMySQLConnection(); 
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		String query = "select id_user from Session where key_session='"+key+"'";
		try{
			st = (Statement) co.createStatement();
		} catch (SQLException e1) {
			e1.getStackTrace();
		}
		try {
			res1 = st.executeQuery(query);
			if(res1.next()) {
				String query1 = "select login from Users where id="+res1.getInt("id_user");
				res2=st.executeQuery(query1);	
				if(res2.next()){
					login =res2.getString("login");
				}
				else{
					login="";
				}
			}else{
				login="";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if (st!= null)  st.close();
			if (co != null) co.close();
		} catch (SQLException e) {
			return login;
		}
		return login;
	}
	
	public static String last_name(String key){
		ResultSet res1 = null;
		ResultSet res2 = null;
		Connection co=null;
		Statement st=null;
		String last_name = "";
		try{
			 co = Database.getMySQLConnection(); 
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		String query = "select id_user from Session where key_session='"+key+"'";
		try{
			st = (Statement) co.createStatement();
		} catch (SQLException e1) {
			e1.getStackTrace();
		}
		try {
			res1 = st.executeQuery(query);
			if(res1.next()) {
				String query1 = "select nom from Users where id="+res1.getInt("id_user");
				res2=st.executeQuery(query1);	
				if(res2.next()){
					last_name =res2.getString("nom");
				}
				else{
					last_name="";
				}
			}else{
				last_name="";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if (st!= null)  st.close();
			if (co != null) co.close();
		} catch (SQLException e) {
			return last_name;
		}
		return last_name;
	}
	public static String first_name(String key){
		ResultSet res1 = null;
		ResultSet res2 = null;
		Connection co=null;
		Statement st=null;
		String first_name = "";
		try{
			 co = Database.getMySQLConnection(); 
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		String query = "select id_user from Session where key_session='"+key+"'";
		
		try{
			st = (Statement) co.createStatement();
		} catch (SQLException e1) {
			e1.getStackTrace();
		}
		try {
			res1 = st.executeQuery(query);
			if(res1.next()) {
				String query1 = "select prenom from Users where id="+res1.getInt("id_user");
				res2=st.executeQuery(query1);	
				if(res2.next()){
					first_name =res2.getString("prenom");
				}
				else{
					first_name="";
				}
			}else{
				first_name="";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if (st!= null)  st.close();
			if (co != null) co.close();
		} catch (SQLException e) {
			return first_name;
		}
		return first_name;
	}
	public static boolean keyExist(String key){
		boolean retour =false;
		Connection co=null;
		Statement st=null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select key_session from Session where key_session='"+key+"'";
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				retour=true;
			}else{
				retour=false;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}
	public static boolean  addFriends(String key, String loginfriend) {
		Connection co = null;
		int from ;
		int to ;
		boolean retour = false;;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			System.out.println("je suis la "+query);
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				to=getIDuser(loginfriend);
				System.out.println("je suis la "+to);
				from=res.getInt("id_user");
				String quer = "INSERT INTO Friends values ('" +from+ "','" + to + "','" + Functions.getTimestamap() + "')";
				st.executeUpdate(quer);
				ResultSet res1=st.executeQuery(query);
				if(res1.next()){
					System.out.println("je suis la ");
					retour=true;
				}else{
					retour=false;
				}	
			}else{
				retour=false;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}
	public static boolean removeFriends(String key, String loginFriend) {

		Connection co = null;
		int from ;
		int to ;
		boolean retour = false;;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				to=getIDuser(loginFriend);
				from=res.getInt("id_user");
				String quer = "delete from Friends where fromm = '"+from+ "' and suis = '" +to+"'";
				System.out.println(""+quer);
				st.executeUpdate(quer);
				ResultSet res1=st.executeQuery(query);
				if(res1.next()){
					retour=true;
				}else{
					retour=false;
				}	
			}else{
				retour=false;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}
	public static List<String> listFriends(String key) {
		Connection co = null;
		int from ;
		List<String> retour = new ArrayList<String>();
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			System.out.println("toto"+query);
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				from=res.getInt("id_user");
				String quer = "select suis from Friends where fromm='"+from+"'";
				ResultSet res1=st.executeQuery(quer);
					while(res1.next()){
						//retour  += " \"login "+cpt+"\"  : \"" +getLogin(res1.getInt("suis"))+ "\", ";
						//cpt++;
						retour.add(getLogin(res1.getInt("suis")));
					}
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}
	public static List<String> listUtilisateurs(String key) {
		Connection co = null;
		List<String> retour = new ArrayList<String>();
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			List<String> retour1=listFriends(key);
			st = co.createStatement();
			String query="Select login from Users";
			String logindekey=Tools.login(key);
			ResultSet res=st.executeQuery(query);
			System.out.println("la taille "+retour1.size());
			while(res.next()){
				retour.add(res.getString("login"));
			}
			System.out.println("la taille de retour "+retour.size());
			for(int i=0;i<retour.size();i++){
				for(int j=0;j<retour1.size();j++){
					if(retour.get(i).equals(retour1.get(j))){
						System.out.println("je rentre");
						retour.remove(i);
					}
				}
			}
			retour.remove(logindekey);
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}
	public static List<Integer>  listFriendsQuejeSui(String key) {
		Connection co = null;
		int from ;
		List<Integer> retour=new ArrayList<Integer>();
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				from=res.getInt("id_user");
				String quer = "select suis from Friends where fromm='"+from+"'";
				ResultSet res1=st.executeQuery(quer);	
					while(res1.next()){
						retour.add(res1.getInt("suis"));
					}
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}
	public static boolean addComments(String key, String message) {
		Connection co=null;
		String login="";
		boolean retour = false;;
		DBCollection coll = null;
		DB db=null;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				try {
					db = Database.getMongoDB();			
				}catch(MongoException e){
					e.getStackTrace();
				}
				coll=db.getCollection("messages");
				login =getLogin(res.getInt("id_user"));		
				BasicDBObject obj=new BasicDBObject();
				obj.put("id",Functions.keySession());
				obj.put("Auteur_id", res.getInt("id_user"));
				obj.put("Autheur_login", login);
				obj.put("message", message);
				obj.put("date", Functions.getTimestamap());
				coll.insert(obj);
				retour=true;
			}else{
				retour=false;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}
	public static JSONObject addMessages(String key, String message) throws JSONException {
		Connection co=null;
		String login_user="";
		//String result = "{";
		JSONObject obj1 = new JSONObject();
		DBCollection coll = null;
		DB db=null;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				try {
					db = Database.getMongoDB();			
				}catch(MongoException e){
					e.getStackTrace();
				}
				coll=db.getCollection("messages");
				login_user =getLogin(res.getInt("id_user"));		
				BasicDBObject obj=new BasicDBObject();
				String id_message = Functions.keySession();
				int id_user= res.getInt("id_user");
				Date date = Functions.getTimestamap();
				System.out.println(id_message);
				obj.put("id_message", id_message);
				obj.put("id_user", id_user);
				obj.put("login_user", login_user);
				obj.put("message", message);
				obj.put("date", date);
				obj.put("liked", 0);
				obj.put("score", Tools.getScoreLike(id_message));	
				coll.insert(obj);
				obj1.put("id_message",id_message);
				obj1.put("id_user",id_user);
				obj1.put("login_user",login_user);
				obj1.put("message",message);
				obj1.put("date",date.toString());
				obj1.put("liked",0);
				obj1.put("score",Tools.getScoreLike(id_message));
				return obj1;
				/*
				result += "\"id\" : \"" + id + "\"";
				result += ", \"login_user\" : \"" + login_user + "\"";
				result += ", \"message\" : \"" + message + "\"";
				result += ", \"date\" : \"" + date.toString() + "\" }";
				//result += ", \"liked\" : 0";
				result += ", \"score\" : \"" + Tools.getScoreLike(id) + "\" }";
				*/
			}else{
				 return null;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return null;
		}
		return null;
	}
	public static boolean CommentComments(String id_message, String key,String texte) {
		Connection co=null;
		String login="";
		String loginducommentaire="";
		boolean retour = false;;
		DBCollection coll = null;
		DB db=null;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				try {
					db = Database.getMongoDB();			
				}catch(MongoException e){
					e.getStackTrace();
				}
				coll=db.getCollection("commentaires");
				loginducommentaire=Tools.getLikeLogin(id_message);
				login =getLogin(res.getInt("id_user"));		
				BasicDBObject obj=new BasicDBObject();
				obj.put("id_commentaire",id_message);
				obj.put("login",login);
				obj.put("loginducommentaire",loginducommentaire);
				obj.put("texte",texte);
				obj.put("date", Functions.getTimestamap());
				coll.insert(obj);
				retour=true;
			}else{
				retour=false;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}
	public static JSONObject CommentMessages(String key,String id_message, String texte) throws JSONException {
		System.out.println("je suis la ");
		Connection co=null;
		String login,loginducommentaire="";
		DBCollection coll = null;
		JSONObject obj1 = new JSONObject();
		DB db=null;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				try {
					System.out.println(res.getInt("id_user"));
					db = Database.getMongoDB();			
				}catch(MongoException e){
					e.getStackTrace();
				}
				coll=db.getCollection("commentaires");
				loginducommentaire=Tools.getLikeLogin(id_message);
				login =getLogin(res.getInt("id_user"));		
				BasicDBObject obj=new BasicDBObject();
				obj.put("id_commentaire",id_message);
				obj.put("login",login);
				obj.put("loginducommentaire",loginducommentaire);
				obj.put("texte",texte);
				obj.put("date", Functions.getTimestamap());
				coll.insert(obj);
				obj1.put("id_commentaire",id_message);
				obj1.put("login",login);
				obj1.put("loginducommentaire",loginducommentaire);
				obj1.put("texte",texte);
				obj1.put("date", Functions.getTimestamap());
				return obj1;
			}else{
				return obj1;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return obj1;
		}
		return obj1;
	}
	public static boolean supMonComments(String id_message, String key,String texte) {
		Connection co=null;
		String login="";
		String loginducommentaire="";
		boolean retour = false;;
		DBCollection coll = null;
		DB db=null;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				try {
					db = Database.getMongoDB();			
				}catch(MongoException e){
					e.getStackTrace();
				}
				coll=db.getCollection("commentaires");
				loginducommentaire=Tools.getLikeLogin(id_message);
				login =getLogin(res.getInt("id_user"));		
				BasicDBObject obj=new BasicDBObject();
				obj.put("id_commentaire", id_message);
				obj.put("login",login);
				obj.put("loginducommentaire",loginducommentaire);
				obj.put("texte",texte);
				obj.put("date", Functions.getTimestamap());
				coll.insert(obj);
				retour=true;
			}else{
				retour=false;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}
	public static String getLikeLogin(String id_message){
		String retour="";
		DBCollection coll = null;
		DB db=null;
		try {
			db = Database.getMongoDB();
		}catch(MongoException e){
			e.getStackTrace();
		}
		//associé id avec id_comment que je suis en train de liké puis je vais chercher dans la collection 
		coll=db.getCollection("messages");
		BasicDBObject obj=new BasicDBObject();
		obj.put("id_message",id_message);
		DBCursor dbCursor=coll.find(obj).limit(1);//un tableau d'un element 
		System.out.println("je suis pas encore rentré"+dbCursor.toString());
		if(dbCursor.hasNext()){//parcourir l'iterator
			//System.out.println("je suis "+dbCursor.next());
			
			retour=dbCursor.next().get("login_user").toString();
		}
		else{
			retour=null;
		}
		return retour;
	}
	public static String  likeMessage(String key, String id_message) {
		Connection co=null;
		String login="";
		String loginlike="";
		String result = "{";
		DBCollection coll = null;
		DB db=null;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				try {
					db = Database.getMongoDB();			
				}catch(MongoException e){
					e.getStackTrace();
				}
				coll=db.getCollection("like");
				loginlike=Tools.getLikeLogin(id_message);
				login =getLogin(res.getInt("id_user"));	
				String id = Functions.keySession();
				Date date = Functions.getTimestamap();
				BasicDBObject obj=new BasicDBObject();
				obj.put("id",id);
				obj.put("id_message", id_message);
				obj.put("login",login);
				obj.put("loginlike",loginlike);
				obj.put("date",date);		
				coll.insert(obj);
				
				result += "\"id\" : \"" + id + "\"";
				result += ", \"login\" : \"" + login + "\"";
				result += ", \"loginlike\" : \"" + loginlike + "\"";
				result += ", \"date\" : \"" + date.toString() + "\" }";
			}else{
				result="";
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return result;
		}
		return result;
	}
	
	public static boolean likeMessages(String key, String id_message) {
		Connection co=null;
		String login="";
		String loginlike="";
		boolean retour = false;;
		DBCollection coll = null;
		DB db=null;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				try {
					db = Database.getMongoDB();			
				}catch(MongoException e){
					e.getStackTrace();
				}
				coll=db.getCollection("like");
				loginlike=Tools.getLikeLogin(id_message);
				login =getLogin(res.getInt("id_user"));		
				BasicDBObject obj=new BasicDBObject();
				obj.put("id",Functions.keySession());
				obj.put("id_message", id_message);
				obj.put("login",login);
				obj.put("loginlike",loginlike);
				obj.put("date", Functions.getTimestamap());		
				coll.insert(obj);
				retour=true;
			}else{
				retour=false;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}

	public static int getScoreLike(String id_message){
		DB db=null;
		try {
			db = Database.getMongoDB();			
		}catch(MongoException e){
			e.getStackTrace();
		}
		DBCollection collection = db.getCollection("like");

		BasicDBObject dbObj = new BasicDBObject();
		dbObj.put("id_message", id_message);

		DBCursor dbCursor = collection.find(dbObj);

		return dbCursor.count();
	}
	public static boolean unlikeMessages(String key, String id_message) {
		Connection co=null;
		boolean retour = false;;
		DBCollection coll = null;
		DB db=null;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				try {
					db = Database.getMongoDB();			
				}catch(MongoException e){
					e.getStackTrace();
				}
				coll=db.getCollection("like");
				BasicDBObject obj=new BasicDBObject();
				obj.put("login",getLogin(res.getInt("id_user")));
				DBCursor dbCursor=coll.find(obj).limit(1);//un tableau d'un element 
				if(dbCursor.hasNext()){//parcourir l'iterator
					coll.remove(obj);
					retour=true;
				}else{
					retour=false;
				}
			}else{
				retour=false;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}
	public static boolean supMessage(String key, String id_massageSup) {
		Connection co=null;
		String login="";
		boolean retour = false;;
		DBCollection coll ,collcom= null;
		DB db=null;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				try {	
					db = Database.getMongoDB();			
				}catch(MongoException e){
					e.getStackTrace();
				}
				coll=db.getCollection("messages");
				login =getLogin(res.getInt("id_user"));
				DBCursor cursor = coll.find();
				while (cursor.hasNext()) {
					DBObject dbObject = cursor.next();
					String login_user = (String) dbObject.get("login_user");
					System.out.println(login_user);
					String id_message = (String)dbObject.get("id_message");
					System.out.println(id_message+" "+id_massageSup);
					System.out.println(login_user+" "+login);
					if (id_message.equals(id_massageSup) && login_user.equals(login)){
						coll.remove(dbObject);
						collcom=db.getCollection("commentaires");
						DBCursor cursorcom = collcom.find();
						System.out.println("nombre de comments"+cursorcom.count());
						while (cursorcom.hasNext()) {
							DBObject dbObjectcom = cursorcom.next();
							System.out.println("je rentre pour supprimer les commentaire"+dbObjectcom);
							String id_comment = (String) dbObjectcom.get("id_commentaire");
							System.out.println(""+id_comment+" "+id_message);
							if(id_comment.equals(id_message)){
								collcom.remove(dbObjectcom);
							}
						}
						retour=true;
					}else{
						retour=false;
					}
				}	
			}else{
				retour=false;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}
	public static boolean supComment(String key, String id_commentSup) {
		Connection co=null;
		String login="";
		boolean retour = false;;
		DBCollection collcom= null;
		DB db=null;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				try {	
					db = Database.getMongoDB();			
				}catch(MongoException e){
					e.getStackTrace();
				}
				collcom=db.getCollection("commentaires");
				DBCursor cursorcom = collcom.find();
				login =getLogin(res.getInt("id_user"));
				System.out.println("nombre de comments"+cursorcom.count());
				while (cursorcom.hasNext()) {
					DBObject dbObjectcom = cursorcom.next();
					System.out.println("je rentre pour supprimer les commentaire"+dbObjectcom);
					String id_comment = (String) dbObjectcom.get("id_commentaire");
					String loginducommentaire = (String) dbObjectcom.get("loginducommentaire");
					System.out.println(""+id_comment+" "+id_commentSup);
					if(id_comment.equals(id_commentSup) && login.equals(loginducommentaire)){
						collcom.remove(dbObjectcom);
						retour=true;
					}else{
						retour=false;
					}
				}	
			}else{
				retour=false;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}
	public static String listMassages(String key) {

		Connection co=null;
		String login="";
		String retour = "";
		DBCollection coll = null;
		DB db=null;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				try {
					db = Database.getMongoDB();			
				}catch(MongoException e){
					e.getStackTrace();
				}
				//db.getCollection("messages").remove(new BasicDBObject());//pour vider la collection
				coll=db.getCollection("messages");
				login =getLogin(res.getInt("id_user"));	
				System.out.println("la login : "+login);
				DBCursor cursor = coll.find();
				int cpt=0;
				while (cursor.hasNext()) {
					System.out.println("nombre de messages"+cursor.count());
					DBObject dbObject = cursor.next();
					System.out.println("messages : "+dbObject.toString());
					System.out.println(dbObject.get("login_user"));
					String log = (String) dbObject.get("login_user");
					String message = (String)dbObject.get("message");
					Date date = (Date)dbObject.get("date");
					System.out.println(log);
					System.out.println(login);
					if (log.equals(login)){
						retour+=" \"message "+cpt+"\"  : \"" +message+ "\" à , \"" +date+"\", \"" ;
						cpt++;
					}
				}
			}else{
				retour="";
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}
	public static List<JSONObject> listesCommentsParMassages(String id_message) throws JSONException {
		List<JSONObject> result =new ArrayList<JSONObject>();
		List<JSONObject> obj2=new ArrayList<JSONObject>();
		DBCollection coll = null;
		DB db=null;
		int cpt=0;
		try{
			db = Database.getMongoDB();			
		}catch(MongoException e){
			e.getStackTrace();
		}
		//db.getCollection("commentaires").remove(new BasicDBObject());//pour vider la collection
		coll=db.getCollection("commentaires");
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			JSONObject obj=new JSONObject();
			DBObject dbObject = cursor.next();
			String id_message1 = (String) dbObject.get("id_commentaire");
			System.out.println(id_message1);
			String loginducommentaire = (String) dbObject.get("loginducommentaire");
			String  login= (String)dbObject.get("login");
			String texte = (String)dbObject.get("texte");
			Date date = (Date)dbObject.get("date");
			System.out.println(""+id_message1+" "+id_message);
			if (id_message1.equals(id_message)){
				cpt++;
				obj.put("id_commentaire",id_message);
				obj.put("login",login);
				obj.put("loginducommentaire",loginducommentaire);
				obj.put("texte",texte);
				obj.put("date",date);
				result.add(obj);			
			}
		}
		if(cpt==0){
			return obj2;
		}else{				
			return result;
		}
}
	public static List<JSONObject> listesMassages(String key) throws JSONException {
		List<JSONObject> result =new ArrayList<JSONObject>();
		List<JSONObject> resultfinal =new ArrayList<JSONObject>();
		List<JSONObject> obj2=new ArrayList<JSONObject>();
		List<String> listFriends=new ArrayList<String>();
		Connection co=null;
		String login="";
		DBCollection coll = null;
		DB db=null;
		int cpt=0;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				try {
					listFriends=listFriends(key);			
					db = Database.getMongoDB();			
				}catch(MongoException e){
					e.getStackTrace();
				}
				//db.getCollection("messages").remove(new BasicDBObject());//pour vider la collection
				coll=db.getCollection("messages");
				login =getLogin(res.getInt("id_user"));
				DBCursor cursor = coll.find().sort(new BasicDBObject("date", -1));
				while (cursor.hasNext()) {
					System.out.println("je rentre dans cursor");
					JSONObject obj=new JSONObject();
					DBObject dbObject = cursor.next();
					
					String id_message = (String) dbObject.get("id_message");
					int id_user = (Integer) dbObject.get("id_user");
					String login_user = (String) dbObject.get("login_user");
					String message = (String)dbObject.get("message");
					Date date = (Date)dbObject.get("date");
					ListIterator li = listFriends.listIterator();
					while(li.hasNext()){
						String toto=li.next().toString();
						int a=login_user.length();
						int b=toto.length();
						if(a==b){
							System.out.println("je rentre dans le deuxieme if");
							cpt++;
							obj.put("id_message",id_message);
							obj.put("id_user",id_user);
							obj.put("login_user",login_user);
							obj.put("message",message);
							obj.put("date",date.toString());
							obj.put("liked",0);
							obj.put("score",Tools.getScoreLike(id_message));
							result.add(obj);
						}
					}
					if (login_user.equals(login)){
						cpt++;
						obj.put("id_message",id_message);
						obj.put("id_user",id_user);
						obj.put("login_user",login_user);
						obj.put("message",message);
						obj.put("date",date.toString());
						obj.put("liked",0);
						obj.put("score",Tools.getScoreLike(id_message));
						result.add(obj);
					}
				}
			}else{
				return obj2;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			if(cpt==0){
				return obj2;
			}else{
				int j=0;
				int taille  =result.size();
				while(taille>0){
					resultfinal.add(result.get(j));
					j++;
					taille--;
					if(j>=10){
						return resultfinal;
					}	
				}
				return resultfinal;
			}
		}
		if(cpt==0){
			return obj2;
		}else{
			int j=0;
			int taille  =result.size();
			while(taille>0){
				resultfinal.add(result.get(j));
				j++;
				taille--;
				if(j>=10){
					return resultfinal;
				}
			}
			return resultfinal;
		}
	}
	public static List<JSONObject> listesMassagesParlogin(String login) throws JSONException {
		List<JSONObject> result =new ArrayList<JSONObject>();
		List<JSONObject> obj2=new ArrayList<JSONObject>();
		Connection co=null;
		DBCollection coll = null;
		DB db=null;
		int cpt=0;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			db = Database.getMongoDB();			
		}catch(MongoException e){
			e.getStackTrace();
		}
		coll=db.getCollection("messages");
		DBCursor cursor = coll.find();		
		while (cursor.hasNext()) {
			JSONObject obj=new JSONObject();
			DBObject dbObject = cursor.next();
			String id_message = (String) dbObject.get("id_message");
			int id_user = (Integer) dbObject.get("id_user");
			String login_user = (String) dbObject.get("login_user");
			String message = (String)dbObject.get("message");
			Date date = (Date)dbObject.get("date");
			if (login_user.equals(login)){
				cpt++;
				obj.put("id_message",id_message);
				obj.put("id_user",id_user);
				obj.put("login_user",login_user);
				obj.put("message",message);
				obj.put("date",date.toString());
				obj.put("liked",0);
				obj.put("score",Tools.getScoreLike(id_message));
				result.add(obj);
			}
		}
		if(cpt==0){
			return obj2;
		}else{
			return result;
		}
	}
	
	
	public static String pwGenerator() {
		final SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
	
	public static int countLike(String login){
		String cm="function(){"+
		
		"if(this.loginlike==\""+login+"\")"+
		"emit(\""+login+"\", 1);"+
		"}";
		String cr="function(k,v){"+
		"var sum=0;"+
		"for(var i=0; i<v.length; i++){"+
			"sum+=v[i];}"+
		"return sum;"+
		"}";
		DBCollection coll = null;
		DB db=null;
		int result=0;
		try {
			db = Database.getMongoDB();			
			coll=db.getCollection("like");
			MapReduceCommand cmd = new MapReduceCommand(coll, cm, cr, null, MapReduceCommand.OutputType.INLINE, null);
			MapReduceOutput out=coll.mapReduce(cmd);
			for(DBObject obj : out.results()){
				if(obj.get("value") != null)
					result= ((Double) obj.get("value")).intValue();
				break;
			}
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static int counMessage(String login){
		String cm="function(){"+
		
		"if(this.login_user==\""+login+"\")"+
		"emit(\""+login+"\", 1);"+
		"}";
		String cr="function(k,v){"+
		"var sum=0;"+
		"for(var i=0; i<v.length; i++){"+
		"sum+=v[i];}"+
		"return sum;"+
		"}";
		DBCollection coll = null;
		DB db=null;
		int result=0;
		try {
			db = Database.getMongoDB();			
			coll=db.getCollection("messages");
			MapReduceCommand cmd = new MapReduceCommand(coll, cm, cr, null, MapReduceCommand.OutputType.INLINE, null);
			MapReduceOutput out=coll.mapReduce(cmd);
			for(DBObject obj : out.results()){
				if(obj.get("value") != null)
					result= ((Double) obj.get("value")).intValue();
				break;
			}
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean  addFriend(String key, String loginfriend) {
		Connection co = null;
		int from ;
		int to ;
		boolean retour = false;;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			System.out.println("je suis la "+query);
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				to=getIDuser(loginfriend);
				System.out.println("je suis la "+to);
				from=res.getInt("id_user");
				String quer = "INSERT INTO friendsAccepts values ('" +from+ "','" + to + "','" + Functions.getTimestamap() + "','" + 0 + "')";
				st.executeUpdate(quer);
				ResultSet res1=st.executeQuery(query);
				if(res1.next()){
					System.out.println("je suis la ");
					retour=true;
				}else{
					retour=false;
				}	
			}else{
				retour=false;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}
	public static boolean  acceptedfreind(String key, String loginfriend) {
		Connection co = null;
		int from ;
		int to ;
		boolean retour = false;;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			System.out.println("je suis la "+query);
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				to=getIDuser(loginfriend);
				System.out.println("je suis la "+to);
				from=res.getInt("id_user");
				String quer ="UPDATE friendsAccepts SET ok ='" +1+ "' where to ='" +to+ "' and fromm == '"+from;
				st.executeUpdate(quer);
				ResultSet res1=st.executeQuery(query);
				if(res1.next()){
					retour=true;
				}else{
					retour=false;
				}	
			}else{
				retour=false;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}
	
	public static boolean  refusefreind(String key, String loginfriend) {
		Connection co = null;
		int from ;
		int to ;
		boolean retour = false;;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		try{
			st = co.createStatement();
			String query="Select id_user from Session where key_session='"+key+"'";
			System.out.println("je suis la "+query);
			ResultSet res=st.executeQuery(query);
			if(res.next()){
				to=getIDuser(loginfriend);
				System.out.println("je suis la "+to);
				from=res.getInt("id_user");
				String quer ="DELETE FROM  friendsAccepts where to ='" +to+ "' and fromm == '"+from;
				st.executeUpdate(quer);
				ResultSet res1=st.executeQuery(query);
				if(res1.next()){
					retour=true;
				}else{
					retour=false;
				}	
			}else{
				retour=false;
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		try{
			if(st!=null) st.close();
			if(co!= null) co.close();
		} catch (SQLException e) {
			return retour;
		}
		return retour;
	}

}
	
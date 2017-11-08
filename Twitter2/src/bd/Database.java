package bd;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;


public class Database {

	private static DataSource dataSource;
	private static Database database;
	public Database(String jndiname) throws SQLException {

		try{
			dataSource = (DataSource)new InitialContext().lookup("java:comp/env/"+ jndiname);
		}catch(NamingException e) {
			throw new SQLException(jndiname +" is missing in JNDI! : "+e.getMessage());
		}
	}
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	public static Connection getMySQLConnection() throws SQLException {
		if (DBStatic.mysql_pooling==false) {
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		return (DriverManager.getConnection("jdbc:mysql://"+ DBStatic.mysql_host +"/"+DBStatic.mysql_db, DBStatic.mysql_username, DBStatic.mysql_password));
	}else{
		if(database==null){
			Database database = new Database("jdbc/db");
		}
			return (database.getConnection());
		}
	}	
	/*
	public static DBCollection getMongoDB()  {
		Mongo m=null;
		DB db=null;
		DBCollection coll=null;
		try {
			m = new Mongo("li328.lip6.fr", 27130);
			db =m.getDB(DBStatic.mysql_db);	
			coll=db.getCollection("comments");
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return coll;	
	}
	*/
	public static DB getMongoDB()  {
		Mongo m=null;
		DB db=null;
		try {
			m = new Mongo("li328.lip6.fr", 27130);
			db =m.getDB(DBStatic.mysql_db);
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return db;	
	}

}

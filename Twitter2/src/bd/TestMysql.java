package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMysql {

	public static void main(String []args ) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		//System.out.println("resulta ");
	try {
		Connection	co = Database.getMySQLConnection();
		System.out.println("resulta ");
		Statement st = co.createStatement();
		//String query="INSERT INTO Users (`login`, `nom`, `prenom`, `email`, `mdp`) VALUES (\"13\",\"toto\",\"titi\",\"coucou\",\"blabla\")";
		//int res=st.executeUpdate(query);
		ResultSet res = st.executeQuery("SELECT * FROM Users");
		while (res.next()){
			System.out.println(res.getString("nom"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	
}
}

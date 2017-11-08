package bd;



import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Statement;


public class testServletMysql extends HttpServlet {


	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			response.getWriter().println("debut");
		Connection co;
			co = Database.getMySQLConnection();
		
		response.getWriter().println("connection BD");
		Statement st = co.createStatement();
		response.getWriter().println("exec statement");
		ResultSet res = st.executeQuery("SELECT * FROM Users");
		response.getWriter().println("exec query");
		while (res.next()){
			response.getWriter().println(res.getString("nom"));
		}
		response.getWriter().println("res ok");
		}catch(SQLException e){
			e.printStackTrace();
		} 
	}


}

package servlets.user;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import servicetools.Tools;

/**
 * Servlet implementation class ListMassageParLogin
 */
public class ListMassageParLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			@SuppressWarnings("unchecked")
			Map<String, String> parametres =request.getParameterMap();	
			if(parametres.containsKey("login")){
				String login = request.getParameter("login");
				try {
					response.getWriter().println(Tools.listesMassagesParlogin(login).toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else{
				response.getWriter().println(Tools.servicerefuse("erreur de parametre", 100).toString());
			}
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

}


package servlets.user;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.user.CountmessageService;
import servicetools.Tools;

/**
 * Servlet implementation class ListUtilisateursservlet
 */
public class CountMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
	
		@SuppressWarnings("unchecked")
		Map<String, String> parametres =request.getParameterMap();	
		if(parametres.containsKey("login")){
			String login = request.getParameter("login");
				response.getWriter().println(CountmessageService.countmessage(login));
		}else{
			response.getWriter().println(Tools.servicerefuse("erreur de parametre", 100).toString());
		}
	}

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
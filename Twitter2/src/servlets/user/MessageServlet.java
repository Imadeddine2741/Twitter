package servlets.user;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import service.user.LogoutService;
import service.user.MessagesService;
import servicetools.Functions;
import servicetools.Tools;

/**
 * Servlet implementation class CommentsServlet
 */
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		@SuppressWarnings("unchecked")
		Map<String, String> parametres =request.getParameterMap();	
		if(parametres.containsKey("key") && parametres.containsKey("message")){
			String key = request.getParameter("key");
			String message = request.getParameter("message");
			if (Functions.moreThan30Min(Tools.getDate(key)))
				response.getWriter().println(LogoutService.logout(key).toString());
			else
				try {
					response.getWriter().println(MessagesService.message(key, message).toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				//response.getWriter().println(servicetools.Tools.addMessages(key,message).toString());
				//response.getWriter().write(servicetools.Tools.addMessages(key,message));
				
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

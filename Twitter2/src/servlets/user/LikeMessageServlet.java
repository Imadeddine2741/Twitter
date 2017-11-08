package servlets.user;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.user.LikeService;
import service.user.LogoutService;
import servicetools.Functions;
import servicetools.Tools;

/**
 * Servlet implementation class LikeMessageServlet
 */
public class LikeMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		@SuppressWarnings("unchecked")
		Map<String, String> parametres =request.getParameterMap();	
		if(parametres.containsKey("key") && parametres.containsKey("id_message")){
			String key = request.getParameter("key");
			String id_message = request.getParameter("id_message");
			if (Functions.moreThan30Min(Tools.getDate(key)))
				response.getWriter().println(LogoutService.logout(key).toString());
			else 
				response.getWriter().println(LikeService.like(key,id_message).toString());
				//response.getWriter().println(servicetools.Tools.likeMessage(id_message, key).toString());
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

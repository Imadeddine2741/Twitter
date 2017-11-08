package servlets.user;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import service.user.LogoutService;
import service.user.UpdateProfile;
import servicetools.Functions;
import servicetools.Tools;

/**
 * Servlet implementation class UpdateProfileServlet
 */
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<String, String> parametres =request.getParameterMap();	
		if(parametres.containsKey("key") && parametres.containsKey("id") && parametres.containsKey("email") && parametres.containsKey("login")
				&& parametres.containsKey("lastname") && parametres.containsKey("firstname") ){
			String key = request.getParameter("key");
			String login = request.getParameter("login");
			String id = request.getParameter("id");
			String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");
			String firstname = request.getParameter("firstname");
			if (Functions.moreThan30Min(Tools.getDate(key)))
				response.getWriter().println(LogoutService.logout(key));
			else
				try {
					response.getWriter().println(UpdateProfile.updateProfile(id,key,lastname,firstname,email,login).toString());
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
		doGet(request, response);
	}

}

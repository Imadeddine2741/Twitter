package servlets.user;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.user.CreateUserService;
import servicetools.Tools;


/**
 * CreateUserServlet est la servlet qui sert a creer un utilisateur
 
 */
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<String, String> parametres = request.getParameterMap();
		if(parametres.containsKey("login") && parametres.containsKey("mdp") 
		&& parametres.containsKey("nom") && parametres.containsKey("prenom")
		&& parametres.containsKey("email")){		
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String mot_de_passe = request.getParameter("mdp");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			response.getWriter().println(CreateUserService.createUser(login, mot_de_passe, nom, prenom, email));			
		}
		else{
			response.getWriter().println(Tools.servicerefuse("erreur de parametre", 100));
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

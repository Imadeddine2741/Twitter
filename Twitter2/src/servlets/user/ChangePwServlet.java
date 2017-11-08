package servlets.user;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.user.ChangePwService;
import servicetools.Tools;


public class ChangePwServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<String, String> params = req.getParameterMap();
		String key, prec_pw, new_pw;
		
		if (params.containsKey("prec_pw") && params.containsKey("new_pw") && params.containsKey("key")) {
			prec_pw = req.getParameter("prec_pw");
			new_pw = req.getParameter("new_pw");
			key = req.getParameter("key");
			resp.getWriter().write(ChangePwService.changePw(key, prec_pw, new_pw));
		} else {
			resp.getWriter().write(Tools.erreurParam);
		}
	}
	
}

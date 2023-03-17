package movie.servlet;

import movie.dao.MembershipDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/userNotLogin")
public class UserNotLoggedIn extends HttpServlet {
	protected MembershipDao membershipDao;

	@Override
	public void init() throws ServletException {
		membershipDao = MembershipDao.getInstance();
	}

	// Get the user's membership info
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Map message = new HashMap<String, String> ();
		System.out.println("hihihihi");
		if (req.getSession().getAttribute("user") == null ) {
			String message = "No user logged in yet!";
			req.setAttribute("message", message);
			req.getRequestDispatcher("/UserNotLoggedIn.jsp").forward(req, resp);
		}
	}
}
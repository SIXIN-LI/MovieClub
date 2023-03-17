package movie.servlet;

import movie.dao.MembershipDao;
import movie.model.MemberShip;
import movie.model.Movies;
import movie.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/displayMembership")
public class DisplayMembership extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected MembershipDao membershipDao;

	@Override
	public void init() throws ServletException {
		membershipDao = MembershipDao.getInstance();
	}

	// Display user's membership information
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
//		Map<String, String> messages = new HashMap<String, String>();
//		req.setAttribute("messages", messages);
		MemberShip membership;

		Users user = (Users) req.getSession().getAttribute("user");
		if ( user != null) {
			// Retrieve Movies, and store as a message.
			try {
				membership = membershipDao.getMemberFromUserId(user.getUserId());
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
//			String successMessage = String.format("Displaying information for '%s'", membership.getMembershipId());
//			messages.put("success",successMessage);
		}

//		req.setAttribute("membership", membership);
		req.getRequestDispatcher("/DisplayMembership.jsp").forward(req, resp);
	}
}

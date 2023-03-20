package movie.servlet;

import movie.dao.MembershipDao;
import movie.dao.UsersDao;
import movie.model.MemberShip;
import movie.model.Users;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/updateMembership")
public class UpdateMembership extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected MembershipDao membershipDao;
    protected UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        membershipDao = MembershipDao.getInstance();
        usersDao = UsersDao.getInstance();
    }

    // By default, the method will be doGet, whenever we call the servlet unless we specify the action
    // to be post in the jsp file.
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        MemberShip membership = null;
        Users user = (Users) req.getSession().getAttribute("user");
        if (user == null) {
            messages.put("userNotLoggedIn", "true"); // case1: current user not logged in
        } else {
            Integer userId = user.getUserId();
            try {
                membership = membershipDao.getMemberFromUserId(userId);
                if (membership == null) {
                    messages.put("ismembership", "false"); // case2: current user logged in, but no membership
                } else {
                    messages.put("ismembership", "true"); // case3: current user logged in, and with membership
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        req.setAttribute("user", user);
        req.setAttribute("membership", membership);
        req.getRequestDispatcher("/MembershipInfo.jsp").forward(req, resp);
    }

    // Register user as a membership
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        Users user;
        MemberShip membership = null;

        try {
            user = (Users) req.getSession().getAttribute("user");
            if (user == null) {
                messages.put("ismembership", "false");
            } else {
                Timestamp created = new Timestamp(System.currentTimeMillis());
                membership = new MemberShip(user, created);
                membershipDao.create(membership);
                messages.put("ismembership", "true");
                messages.put("success", "true");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        req.setAttribute("membership", membership); // save the info in the request so that we could retrieve it in the jsp
        req.getRequestDispatcher("/MembershipInfo.jsp").forward(req, resp);
    }
}
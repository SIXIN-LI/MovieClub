package movie.servlet.users;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import movie.dao.UsersDao;
import movie.model.Users;

@WebServlet("/login")
public class GetUserLogin extends HttpServlet {

    public static Logger log = Logger.getLogger(GetUserInfo.class.toString());

    protected UsersDao usersDao;

    public GetUserLogin() {
        this.usersDao = UsersDao.getInstance();
    }

    /**
     * Display a login page/component
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    /**
     * Find a matched user with userid and password
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Users user = null;
        // Retrieve and validate username and password.
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (!isValid(username) || !isValid(password)) {
            messages.put("success", "Please enter a valid user id or password.");
        } else {
            try {
                user = usersDao.getUserByUserNameAndPassword(username, password);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        if (user != null) {
            messages.put("success", "Successfully log in " + username);

            req.getSession().setAttribute("user", user);

            // TODO: redirect to home page
            resp.sendRedirect("test.jsp");
        } else {
            messages.put("failure", "Your username or password is incorrect");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }

    private boolean isValid(String str) {
        if (str == null || str.trim().isEmpty()) return false;
        return true;
    }
}

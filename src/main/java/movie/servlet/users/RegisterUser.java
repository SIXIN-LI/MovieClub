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

@WebServlet("/registerUser")
public class RegisterUser extends HttpServlet {
    public static Logger log = Logger.getLogger(GetUserInfo.class.toString());

    protected UsersDao usersDao;

    public RegisterUser() {
        this.usersDao = UsersDao.getInstance();
    }

    /**
     * Display the empty input user info page
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        req.setAttribute("action", "Create");
        req.getRequestDispatcher("/UserInfo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        req.setAttribute("action", "Create");

        Users user = null;
        // Get the updated user info
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        Users.genderType gender = Users.genderType.valueOf(req.getParameter("gender"));

        try {
            // Construct user info
            user = new Users(userName, password, firstName, lastName, gender);
            user = usersDao.createUser(user);
        } catch (SQLException e) {
            log.severe(e.getMessage());
            messages.put("failure", "Sign up failed " + userName);
        }

        // Check if the user has been created
        if (user.getUserId() != 0) {
            messages.put("success", "Successfully created " + userName);

            // redirect to login page
            resp.sendRedirect("login");
        } else {
            messages.put("failure", "Sign up failed " + userName);
            req.getRequestDispatcher("/UserInfo.jsp").forward(req, resp);
        }

    }
}

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

@WebServlet("/updateUser")
public class UpdateUser extends HttpServlet {

    public static Logger log = Logger.getLogger(UpdateUser.class.toString());

    protected UsersDao usersDao;

    public UpdateUser() {
        this.usersDao = UsersDao.getInstance();
    }

    /**
     * Display user's all info when the user is logged in
     * Otherwise, forward the request to register user page
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Users user = (Users) req.getSession().getAttribute("user");
        req.setAttribute("user", user);

        req.getRequestDispatcher("/UserPage/UpdateUser.jsp").forward(req, resp);
    }

    /**
     * Update user information if the user is logged in
     * Otherwise, forward the request to register user operation
     *
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Users user = (Users) req.getSession().getAttribute("user");
        // Retrieve user id.
        int userId = user.getUserId();

        // Get the updated user info
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        Users.genderType gender = Users.genderType.valueOf(req.getParameter("gender"));
        log.info(gender.toString());

        try {
            // Update user info
            user = new Users(userName, password, firstName, lastName, gender);
            user.setUserId(userId);
            boolean updated = usersDao.updateUser(user);

            if (updated) {
                messages.put("success", "Successfully updated " + userName);

                req.getSession().setAttribute("user", user);
            } else {
                messages.put("failure", "Update failed " + userName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        req.setAttribute("user", user);

        req.getRequestDispatcher("/UserPage/UpdateUser.jsp").forward(req, resp);

    }

}

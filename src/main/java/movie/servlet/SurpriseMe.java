package movie.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import java.util.Random;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.dao.FanFavoritesDao;
import movie.dao.SurpriseMeDao;
import movie.dao.UsersDao;
import movie.model.Movies;
import movie.model.Users;


@WebServlet("/surpriseme")
public class SurpriseMe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected SurpriseMeDao surpriseMeDao;
	protected UsersDao usersDao;
	@Override
	public void init() throws ServletException {
		surpriseMeDao = SurpriseMeDao.getInstance();
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        Users user = (Users) req.getSession().getAttribute("user");
				Movies surpriseMe = null;
				if(user == null){
					try {
						surpriseMe = FanFavoritesDao.getInstance().getTopFanFavorites().get(1);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					try {
						surpriseMe = surpriseMeDao.getSurpriseMeByUserId(user.getUserId());
						if(surpriseMe == null) surpriseMe = FanFavoritesDao.getInstance().getTopFanFavorites().get(2);
						else surpriseMe = surpriseMeDao.getSurpriseMeByUserId(user.getUserId());
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

//        req.setAttribute("user", user);
        req.setAttribute("surpriseMe", surpriseMe);
        req.getRequestDispatcher("/SurpriseMe.jsp").forward(req, resp);
	}
}

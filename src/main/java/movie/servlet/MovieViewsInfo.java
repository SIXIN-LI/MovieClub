package movie.servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie.dao.CustomizedSearchDao;
import movie.dao.MovieViewsDao;
import movie.dao.MoviesDao;
import movie.dao.UsersDao;
import movie.model.MovieViews;
import movie.model.Movies;
import movie.model.Users;

/**
 * Servlet implementation class FindMovies
 */
@WebServlet("/movieViewsInfo")
public class MovieViewsInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected  MovieViewsDao  movieViewsDao;
	protected  UsersDao  usersDao;

	@Override
	public void init() throws ServletException {
		movieViewsDao = MovieViewsDao.getInstance();
		usersDao = UsersDao.getInstance();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Map for storing messages.
		 Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);
	        List<MovieViews> movieViews = new ArrayList<MovieViews>();
	        
	        
	        Users user = (Users) req.getSession().getAttribute("user");
	        if (user == null) {
	            messages.put("success", "User is null.");
	        } else {

	            try {
	                movieViews = movieViewsDao.getMovieViewsByUserId(user);
	            } catch (SQLException e) {
	                e.printStackTrace();
	                throw new IOException(e);
	            }

	            String successMessage = String.format("Displaying results for user '%s'", user.getUserName());
	            messages.put("success", successMessage);
	        }
	        req.setAttribute("user", user);
	        req.setAttribute("movieViews", movieViews);
	        req.getRequestDispatcher("/MovieViewsInfo.jsp").forward(req, resp);

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);
	        List<MovieViews> movieViews = new ArrayList<MovieViews>();
	        
	        
	        Users user = (Users) req.getSession().getAttribute("user");
	        if (user == null) {
	            messages.put("success", "User is null.");
	        } else {

	            try {
	                movieViews = movieViewsDao.getMovieViewsByUserId(user);
	            } catch (SQLException e) {
	                e.printStackTrace();
	                throw new IOException(e);
	            }

	            String successMessage = String.format("Displaying results for user '%s'", user.getUserName());
	            messages.put("success", successMessage);
	        }
	        req.setAttribute("user", user);
	        req.setAttribute("movieViews", movieViews);
	        req.getRequestDispatcher("/MovieViewsInfo.jsp").forward(req, resp);

     
	     // Delete all MovieViews records for the specified user ID
	        if (user != null) {
		        try {
		            movieViewsDao.delete(user);
		        } catch (SQLException e) {
		            e.printStackTrace();
		            throw new IOException(e);
		        }
		        // Set success message to indicate deletion completed
		        messages.put("success", String.format("Deleted all MovieViews for user ID %d", user.getUserId()));
		        req.setAttribute("movieViews", movieViews);
		        req.getRequestDispatcher("/MovieViewsInfo.jsp").forward(req, resp);

    }

	}
}

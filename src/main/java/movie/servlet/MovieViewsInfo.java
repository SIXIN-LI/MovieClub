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
       
     
        String userIdStr = req.getParameter("userId");
        Users user;
        if (userIdStr == null || userIdStr.isEmpty()) {
        	user = null;
        }else {
        	int userId = Integer.parseInt(userIdStr);
        	try {
                user = usersDao.getUserByUserId(userId);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        
   
        if (userIdStr == null || userIdStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid userId.");
        } else {
        	// Retrieve MovieViews, and store as a message.
        	try {
        		movieViews = movieViewsDao.getMovieViewsByUserId(user);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	String successMessage = String.format("Displaying results for '%s'", userIdStr);
        	messages.put("success", successMessage);
        
        }
        req.setAttribute("movieViews", movieViews);

        req.getRequestDispatcher("/MovieViewsInfo.jsp").forward(req, resp);

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		Map<String, String> messages = new HashMap<String, String>();
//	    req.setAttribute("messages", messages);
//	    
//	    // Check if the request is for deleting a movie view.
//	    if (req.getParameter("_method") != null && req.getParameter("_method").equalsIgnoreCase("DELETE")) {
//	        int viewId = Integer.parseInt(req.getParameter("viewId"));
//	        try {
//	            MovieViewsDao movieViewsDao = new MovieViewsDao();
//	            MovieViews movieView = movieViewsDao.getMovieViewsByViewId(viewId);
//	            movieViewsDao.delete(movieView);
//	            resp.sendRedirect(req.getContextPath() + "/movieViewsInfo");
//	            messages.put("success", "Movie view deleted successfully.");
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	            messages.put("error", "Failed to delete movie view. Please try again later.");
//	        }
//	    } else { // The request is for listing movie views.
//	        List<MovieViews> movieViews = new ArrayList<MovieViews>();
//	        String userIdStr = req.getParameter("userId");
//	        Users user;
//	        if (userIdStr == null || userIdStr.isEmpty()) {
//	            user = null;
//	        } else {
//	            int userId = Integer.parseInt(userIdStr);
//	            try {
//	                user = usersDao.getUserByUserId(userId);
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	                throw new IOException(e);
//	            }
//	        }
//	        
//	        if (userIdStr == null || userIdStr.trim().isEmpty()) {
//	            messages.put("error", "Please enter a valid userId.");
//	        } else {
//	            // Retrieve MovieViews, and store as a message.
//	            try {
//	                movieViews = movieViewsDao.getMovieViewsByUserId(user);
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	                throw new IOException(e);
//	            }
//	            String successMessage = String.format("Displaying results for userId '%d'", user.getUserId());
//	            messages.put("success", successMessage);
//	        }
//	        
//	        req.setAttribute("movieViews", movieViews);
//	    }
//	    
//	    req.getRequestDispatcher("/MovieViewsInfo.jsp").forward(req, resp);

        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<MovieViews> movieViews = new ArrayList<MovieViews>();
        for (MovieViews movieView : movieViews) {
        	  int viewId = movieView.getViewId();
        	  req.removeAttribute("view_" + viewId); 

        	  try {
        		  MovieViewsDao movieViewsDao = new MovieViewsDao();
	  	          MovieViews gotMovieView = movieViewsDao.getMovieViewsByViewId(viewId);
	  	          movieViewsDao.delete(gotMovieView);
        	 
        	  } catch (SQLException e) {
        	    e.printStackTrace();
        	    throw new IOException(e);
        	  }
        	}

        
        String userIdStr = req.getParameter("userId");
        Users user;
        if (userIdStr == null || userIdStr.isEmpty()) {
        	user = null;
        }else {
        	int userId = Integer.parseInt(userIdStr);
        	try {
                user = usersDao.getUserByUserId(userId);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        
   
        if (userIdStr == null || userIdStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid userId.");
        } else {
        	// Retrieve MovieViews, and store as a message.
        	try {
        		movieViews = movieViewsDao.getMovieViewsByUserId(user);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	String successMessage = String.format("Displaying results for userId'%d'", user.getUserId());
        	messages.put("success", successMessage);
        
        }
     
        req.setAttribute("movieViews", movieViews);

        req.getRequestDispatcher("/MovieViewsInfo.jsp").forward(req, resp);
    }
//	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	    int viewId = Integer.parseInt(req.getParameter("viewId"));
//	  
//	    try {
//	    	MovieViewsDao movieViewsDao = new MovieViewsDao();
//	    	 MovieViews movieView = movieViewsDao.getMovieViewsByViewId(viewId); 
//	        movieViewsDao.delete(movieView);
////	        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//	    }
//	}
//	

}

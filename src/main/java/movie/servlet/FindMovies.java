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
import movie.dao.MoviesDao;
import movie.model.Movies;

/**
 * Servlet implementation class FindMovies
 */
@WebServlet("/findmovies")
public class FindMovies extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected MoviesDao moviesDao;

	@Override
	public void init() throws ServletException {
		moviesDao = MoviesDao.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Movies> movies = new ArrayList<Movies>();
        
        // Retrieve and validate name.
        // moviename is retrieved from the URL query string.
        String movieName = req.getParameter("moviename");
        if (movieName == null || movieName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve Movies, and store as a message.
        	try {
            	movies = moviesDao.getMoviesByName(movieName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	String successMessage = String.format("Displaying results for '%s'", movieName);
        	messages.put("success",successMessage);
        }
        req.setAttribute("movies", movies);
        
        req.getRequestDispatcher("/FindMovies.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Movies> movies = new ArrayList<Movies>();
        
        // Retrieve and validate moviename.
        // moviename is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindMovies.jsp).
        String movieName = req.getParameter("moviename");
        if (movieName == null || movieName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve Movies, and store as a message.
        	try {
        		movies = moviesDao.getMoviesByName(movieName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	String successMessage = String.format("Displaying results for '%s'", movieName);
        	messages.put("success", successMessage);
        }
        req.setAttribute("movies", movies);
        
        req.getRequestDispatcher("/FindMovies.jsp").forward(req, resp);
	}

}

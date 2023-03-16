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
@WebServlet("/findmovieinfo")
public class FindMovieInfo extends HttpServlet {
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
        Movies movie;
        
        // Retrieve and validate name.
        // movieId is retrieved from the URL query string.
        String movieId = req.getParameter("movieid");
        if (movieId == null || movieId.trim().isEmpty()) {
        	throw new ServletException("404 Error: Not found");
        } else {
        	// Retrieve Movies, and store as a message.
        	try {
            	movie = moviesDao.getMovieByMovieId(movieId);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	String successMessage = String.format("Displaying information for '%s'", movie.getTitle());
        	messages.put("success",successMessage);
        }
        req.setAttribute("movie", movie);
        
        req.getRequestDispatcher("/FindMovieInfo.jsp").forward(req, resp);
	}

	

}

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
import movie.dao.MoviesDao;
import movie.model.Movies;

/**
 * Servlet implementation class FindMovies
 */
@WebServlet("/customizedSearchMovies")
public class CustomizedSearchMovies extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected  CustomizedSearchDao  customizedSearchDao;

	@Override
	public void init() throws ServletException {
		customizedSearchDao = CustomizedSearchDao.getInstance();
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
        // genre is retrieved from the URL query string.
        String genre = req.getParameter("genre");
        String ratingStr = req.getParameter("rating");
        double rating;
        if (ratingStr == null || ratingStr.isEmpty()) {
        	rating = 0.0;
        }else {
        	rating = Double.parseDouble(ratingStr);
        }
        
   
        if ((genre == null || genre.trim().isEmpty()) && (rating == 0.0 || ratingStr.trim().isEmpty())) {
            messages.put("success", "Please enter a valid genre or rating.");
        } else {
            // Retrieve Movies based on user-selected criteria, and store as a message.
            try {
                if (genre != null && !genre.trim().isEmpty() && rating != 0.0 && !ratingStr.trim().isEmpty()) {
                    movies = customizedSearchDao.getMoviesByRatingAndGenre(genre, rating);
                } else if (genre != null && !genre.trim().isEmpty()) {
                    movies = customizedSearchDao.getMoviesByGenre(genre);
                } else if (rating != 0.0 && !ratingStr.trim().isEmpty()) {
                    movies = customizedSearchDao.getMoviesByRate(rating);
                }
                String successMessage = String.format("Displaying results for '%s' genre and '%d' rating", genre, rating);
                messages.put("success", successMessage);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.setAttribute("movies", movies);

        req.getRequestDispatcher("/CustomizedSearchMovies.jsp").forward(req, resp);

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Movies> movies = new ArrayList<Movies>();
        
        
//        String genre = req.getParameter("genre");
//        String ratingStr = req.getParameter("rating");
//        Double rating = null;
        String genre = req.getParameter("genre");
        String ratingStr = req.getParameter("rating");
        double rating;
        if (ratingStr == null || ratingStr.isEmpty()) {
        	rating = 0.0;
        }else {
        	rating = Double.parseDouble(ratingStr);
        }
        if ((genre == null || genre.trim().isEmpty()) && (rating == 0.0 || ratingStr.trim().isEmpty())) {
            messages.put("success", "Please enter a valid genre or rating.");
        } else {
            // Retrieve Movies based on user-selected criteria, and store as a message.
            try {
                if (genre != null && !genre.trim().isEmpty() && rating != 0.0 && !ratingStr.trim().isEmpty()) {
                    movies = customizedSearchDao.getMoviesByRatingAndGenre(genre, rating);
                    String successMessage = String.format("Displaying results for '%s' genre and rating is greater than '%s' ", genre, rating);
                    messages.put("success", successMessage);
                } else if (genre != null && !genre.trim().isEmpty()) {
                    movies = customizedSearchDao.getMoviesByGenre(genre);
                    String successMessage = String.format("Displaying results for '%s' genre ", genre);
                    messages.put("success", successMessage);
                } else if (rating != 0.0 && !ratingStr.trim().isEmpty()) {
                    movies = customizedSearchDao.getMoviesByRate(rating);
                    String successMessage = String.format("Displaying results for rating is greater than '%s' ",  rating);
                    messages.put("success", successMessage);
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
     
        req.setAttribute("movies", movies);

        req.getRequestDispatcher("/CustomizedSearchMovies.jsp").forward(req, resp);
    }
	


}

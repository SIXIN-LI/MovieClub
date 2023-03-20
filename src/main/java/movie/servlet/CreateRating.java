package movie.servlet;

import movie.dao.MoviesDao;
import movie.dao.RatingDao;
import movie.model.Movies;
import movie.model.Rating;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/createRating")
public class CreateRating extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected MoviesDao moviesDao;
    protected RatingDao ratingDao;
    protected String movieId;
    protected Double score;

    @Override
    public void init() throws ServletException {
        moviesDao = MoviesDao.getInstance();
        ratingDao = RatingDao.getInstance();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hihihi100000-get");
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        Movies movie;
        Rating rating;

        // Retrieve and validate name.
        // movieId is retrieved from the URL query string.
        movieId = req.getParameter("movieid");
        System.out.println("movie id is: " + movieId);
        System.out.println("in get method: socre is: " + score);
        if (movieId == null || movieId.trim().isEmpty()) {
            System.out.println("movie id is: " + movieId);
            throw new ServletException("404 Error: Not found");
        } else {
            // Retrieve Movies, and store as a message.
            try {
                movie = moviesDao.getMovieByMovieId(movieId);
                rating = ratingDao.getRatingFromMovieId(movieId);
                if (rating != null) {
//                    ratingDao.updateRating(rating, score);
                    messages.put("withRatingOrNot", "true");
                } else {
//                    Rating newRating = new Rating(movie, score, 1);
//                    ratingDao.create(newRating);
                    messages.put("withoutRating", "false");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            String successMessage = String.format("Displaying information for '%s'", movie.getTitle());
            messages.put("success",successMessage);
        }
        req.setAttribute("movie", movie);
        req.setAttribute("rating", rating); // save the info in the request so that we could retrieve it in the jsp
        req.getRequestDispatcher("/CreateRating.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        Movies movie;
        Rating rating;

        // Retrieve and validate name.
        // movieId is retrieved from the URL query string.
        System.out.println("hihihi-post: ");
        System.out.println("hihihi-movid_id: " + movieId);

        score = Double.valueOf(req.getParameter("score"));
        System.out.println("post_score: " + score);

        if (movieId == null || movieId.trim().isEmpty()) {
            System.out.println("movie id is: " + movieId);
            throw new ServletException("404 Error: Not found");
        } else {
            // Retrieve Movies, and store as a message.
            try {
                movie = moviesDao.getMovieByMovieId(movieId);
                rating = ratingDao.getRatingFromMovieId(movieId);
                if (rating != null) {
                    ratingDao.updateRating(rating, score);
                    messages.put("withRatingOrNot", "true");
                } else {
                    Rating newRating = new Rating(movie, score, 1);
                    ratingDao.create(newRating);
                    messages.put("withoutRating", "false");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            String successMessage = String.format("Displaying information for '%s'", movie.getTitle());
            messages.put("success",successMessage);
        }
        req.setAttribute("movie", movie);
        req.setAttribute("rating", rating); // save the info in the request so that we could retrieve it in the jsp
        req.getRequestDispatcher("/CreateRating.jsp").forward(req, resp);
    }
}
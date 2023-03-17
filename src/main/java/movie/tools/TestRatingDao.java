package movie.tools;

import movie.dao.MoviesDao;
import movie.dao.RatingDao;
import movie.model.Movies;
import movie.model.Rating;
import java.sql.SQLException;

public class TestRatingDao {

    public static void main(String[] args) throws SQLException {
        // Need to get movie firstly since movies is the foreign key of Rating
        MoviesDao moviesDao = MoviesDao.getInstance();
        Movies movie = moviesDao.getMovieByMovieId("tt0000009");
        System.out.println("Movie get successfully!");

        // initialize a RatingDao instance
        RatingDao ratingDao = RatingDao.getInstance();
        Rating rating = new Rating(movie, 5.0, 12);

        // Test create
        ratingDao.create(rating);
        System.out.println("Rating created successfully in the database!");

        // Test getRatingFromMovieId
        Rating gotRating = ratingDao.getRatingFromMovieId(movie.getMovieId());
        if (gotRating.equals(rating)) {
            System.out.println("Rating successfully got from the database: " + gotRating);
        }
    }
}
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

        // Initilize a RatingDao instance
        RatingDao ratingDao = RatingDao.getInstance();
        Integer originalNumOfVotes = 12;
        Double originalAvgRating = 5.0;
        Rating rating = new Rating(1, movie, 5.0, originalNumOfVotes);

        // Test create
        ratingDao.create(rating);
        System.out.println("Rating created successfully in the database!");

        // Test getRatingFromMovieId
        Rating gotRating = ratingDao.getRatingFromMovieId(movie.getMovieId());
        if (gotRating.equals(rating)) {
            System.out.println("Rating successfully got from the database: " + gotRating);
        }

        // Test updateRating
        Double newScore = 10.0;
        Rating updatedRating = ratingDao.updateRating(rating, newScore);
        if (updatedRating.getNumOfVotes() == originalNumOfVotes + 1 &&
                updatedRating.getAverageRating().compareTo(originalAvgRating + newScore)/updatedRating.getNumOfVotes() == 0) {
            System.out.println("Successfully updated the rating: " + updatedRating);
        }

    }
}
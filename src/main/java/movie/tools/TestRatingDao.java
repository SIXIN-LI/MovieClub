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
        Movies movieWithoutRating = moviesDao.getMovieByMovieId("tt0000147");
        System.out.println("Movie get successfully!");

        // initialize a RatingDao instance
        RatingDao ratingDao = RatingDao.getInstance();
        Double originalAvgRating = 5.0;
        Integer originalNumOfVotes = 12;
        Rating rating = new Rating(movie, originalAvgRating, originalNumOfVotes);

        // Test create
        ratingDao.create(rating);
        System.out.println("Rating created successfully in the database!");

        // Test getRatingFromMovieId
        Rating gotRating1 = ratingDao.getRatingFromMovieId(movieWithoutRating.getMovieId());
        if (gotRating1 == null) {
            System.out.println("The movie has no corresponding rating: " + gotRating1);
        }
        Rating gotRating2 = ratingDao.getRatingFromMovieId(movie.getMovieId());
        if (gotRating2.equals(rating)) {
            System.out.println("Rating successfully got from the database: " + gotRating2);
        }

        // Test updateRating
        Double newScore = 10.0;
        Rating updatedRating = ratingDao.updateRating(rating, newScore);
        if (updatedRating.getNumOfVotes() == originalNumOfVotes + 1 &&
                updatedRating.getAverageRating().compareTo((originalAvgRating * originalNumOfVotes + newScore)/updatedRating.getNumOfVotes()) == 0) {
            System.out.println("Successfully updated the rating: " + updatedRating);
        }
    }
}
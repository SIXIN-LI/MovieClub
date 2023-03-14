package movie.dao;

import movie.model.Movies;
import movie.model.Rating;
import java.sql.*;


public class RatingDao {
    private static RatingDao instance = null;
    private ConnectionManager connectionManager;

    protected RatingDao() {
        connectionManager = new ConnectionManager();
    }

    public static RatingDao getInstance() {
        if (instance == null) {
            instance = new RatingDao();
        }
        return instance;
    }

    public Rating create(Rating rating) throws SQLException {
        // naming conventions should be the same as we create the table in sql
        // even though the user_id is User object in the data model
        String insertRating = "INSERT INTO Rating(rating_id, movie_id, average_rating, num_votes) VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRating);
            // the insert data type shall also be consistent within the sql query
            insertStmt.setInt(1, rating.getRatingId());
            insertStmt.setString(2, rating.getMovie().getMovieId());
            insertStmt.setDouble(3, rating.getAverageRating());
            insertStmt.setInt(4, rating.getNumOfVotes());
            insertStmt.executeUpdate();
            return rating;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

<<<<<<< HEAD
    public Rating getRatingFromMovieId(String movieId) throws SQLException {
=======
    public Rating getRatingFromMovieId(Integer movieId) throws SQLException {
>>>>>>> 99ec68a8957b340ce5bcbca72a0b97d5a50182d2
        String selectRating = "SELECT rating_id, movie_id, average_rating, num_votes FROM Rating WHERE movie_id =?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRating);
<<<<<<< HEAD
            selectStmt.setString(1, movieId);
=======
            selectStmt.setInt(1, movieId);
>>>>>>> 99ec68a8957b340ce5bcbca72a0b97d5a50182d2
            results = selectStmt.executeQuery();
            if (results.next()) {
                Integer ratingId = results.getInt("rating_id");
                Double avgRating = results.getDouble("average_rating");
                Integer numOfVotes = results.getInt("num_votes");

<<<<<<< HEAD
                MoviesDao moviesDao = MoviesDao.getInstance();
=======
                // Todo: depend on MoviesDao
                Movies moviesDao = MoviesDao.getInstance();
>>>>>>> 99ec68a8957b340ce5bcbca72a0b97d5a50182d2
                Movies movie = moviesDao.getMovieByMovieId(movieId);
                Rating rating = new Rating(ratingId, movie, avgRating, numOfVotes);
                return rating;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }

    public Rating updateRating(Rating rating, Double score) throws SQLException {
        String updateRatingScore = "UPDATE Rating SET average_rating =? WHERE rating_id=?;";
        String updateRatingVotes = "UPDATE Rating SET num_votes =? WHERE rating_id=?;";
        Connection connection = null;
        PreparedStatement updateStmt1 = null;
        PreparedStatement updateStmt2 = null;
        try {
            connection = connectionManager.getConnection();
            // Update average score
            Double newAverageRating = (score + rating.getAverageRating() * rating.getNumOfVotes()) / rating.getNumOfVotes();
            updateStmt1 = connection.prepareStatement(updateRatingScore);
            updateStmt1.setDouble(1, newAverageRating);
            updateStmt1.setInt(2, rating.getRatingId());
            updateStmt1.executeUpdate();

            // Update total number of votes
            Integer totalVotes = rating.getNumOfVotes() + 1;
            updateStmt2 = connection.prepareStatement(updateRatingVotes);
            updateStmt2.setDouble(1, totalVotes);
            updateStmt2.setInt(2, rating.getRatingId());
            updateStmt2.executeUpdate();

            // Update the rating object
            rating.setAverageRating(newAverageRating);
            rating.setNumOfVotes(totalVotes);
            return rating;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt1 != null) {
                updateStmt1.close();
            }
            if (updateStmt2 != null) {
                updateStmt2.close();
            }
        }
    }
}
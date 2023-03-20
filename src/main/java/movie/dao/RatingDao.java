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
        String insertRating = "INSERT INTO Rating(movie_id, average_rating, num_votes) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            // if in the insert statement contains the rating_id, then the pass in parameter must have the rating id.
            // otherwise, we could ignore the rating_id in the insert statement, and add a Statement.RETURN_GENERATED_KEYS
            // in the prepareStatement below; Meanwhile, also initialize a default value for the id.
            insertStmt = connection.prepareStatement(insertRating, Statement.RETURN_GENERATED_KEYS);
            // the insert data type shall also be consistent within the sql query
            insertStmt.setString(1, rating.getMovie().getMovieId());
            insertStmt.setDouble(2, rating.getAverageRating());
            insertStmt.setInt(3, rating.getNumOfVotes());
            insertStmt.executeUpdate();

            ResultSet resultKey = insertStmt.getGeneratedKeys();
            Integer id = -1;
            if(resultKey.next()) {
                id = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            rating.setRatingId(id);
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

    public Rating getRatingFromMovieId(String movieId) throws SQLException {
        String selectRating = "SELECT rating_id, movie_id, average_rating, num_votes FROM Rating WHERE movie_id =?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRating);
            selectStmt.setString(1, movieId);
            results = selectStmt.executeQuery();
            if (results == null) {
                return null;
            }
            if (results.next()) {
                Integer ratingId = results.getInt("rating_id");
                Double avgRating = results.getDouble("average_rating");
                Integer numOfVotes = results.getInt("num_votes");

                MoviesDao moviesDao = MoviesDao.getInstance();
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

            // Update total number of votes
            Integer totalVotes = rating.getNumOfVotes() + 1;
            updateStmt2 = connection.prepareStatement(updateRatingVotes);
            updateStmt2.setDouble(1, totalVotes);
            updateStmt2.setInt(2, rating.getRatingId());
            updateStmt2.executeUpdate();

            // Update average score
            Double newAverageRating = (score + rating.getAverageRating() * rating.getNumOfVotes()) / totalVotes;
            updateStmt1 = connection.prepareStatement(updateRatingScore);
            updateStmt1.setDouble(1, newAverageRating);
            updateStmt1.setInt(2, rating.getRatingId());
            updateStmt1.executeUpdate();

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
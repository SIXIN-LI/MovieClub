package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import movie.model.Movies;

public class SurpriseMeDao {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static SurpriseMeDao instance = null;
  protected SurpriseMeDao() {
    connectionManager = new ConnectionManager();
  }
  public static SurpriseMeDao getInstance() {
    if (instance == null) {
      instance = new SurpriseMeDao();
    }
    return instance;
  }

  public Movies getSurpriseMeByUserId(int userId) throws SQLException {
    Random r = new Random();
    int low = 1894;
    int high = 2029;
    int result = r.nextInt(high-low) + low;
    String generateYear = String.valueOf(result);
    String selectFavorite = "SELECT \n"
    		+ "  Movies.*, Intentions.action, cnt\n"
    		+ " FROM\n"
    		+ " (\n"
    		+ "  SELECT genre, cnt\n"
    		+ "	FROM \n"
    		+ "	(SELECT movie_id, COUNT(movie_id) AS cnt\n"
    		+ "	FROM MovieViews\n"
    		+ "	WHERE user_id = ?\n"
    		+ "	GROUP BY movie_id\n"
    		+ "    ORDER BY cnt Limit 1)mv\n"
    		+ "	JOIN Movies on Movies.movie_id = mv.movie_id\n"
    		+ ") genre_t \n"
    		+ " INNER JOIN \n"
    		+ " Movies \n"
    		+ "  ON genre_t.genre=Movies.genre\n"
    		+ "Join Intentions ON Movies.movie_id = Intentions.movie_id\n"
    		+ "WHERE Movies.year = ? AND Intentions.action='watched'\n"
    		+ "ORDER BY cnt desc\n"
    		+ "LIMIT 1;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectFavorite);
      selectStmt.setInt(1, userId);
      selectStmt.setString(2, generateYear);
      results = selectStmt.executeQuery();
      while(results.next()) {
        String movieId = results.getString("movie_id");
        Movies movie = MoviesDao.getInstance().getMovieByMovieId(movieId);
        return movie;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return null;
  }
}

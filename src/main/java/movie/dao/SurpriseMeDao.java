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
    String selectFavorite =
        "SELECT movie_id, title, is_adult, year, runtime_minutes,genre, action, COUNT(*) as cnt "
            + "FROM (SELECT Movies.*, Intentions.action FROM "
            + "(SELECT COUNT(*) as cnt, genre FROM "
            + "(SELECT * from MovieViews WHERE user_id=?) tmp LEFT JOIN Movies ON tmp.movie_id=Movies.movie_id group by genre order by cnt desc limit 1) genre_t "
            + "INNER JOIN Movies ON genre_t.genre=Movies.genre INNER JOIN Intentions ON Movies.movie_id = Intentions.movie_id WHERE Movies.year = ? AND Intentions.action='watched')tmp "
            + "GROUP BY 1,2,3,4,5,6,7 ORDER BY cnt desc limit 1;";

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

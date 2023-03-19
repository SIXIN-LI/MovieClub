package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import movie.model.Movies;

public class FanFavoritesDao {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static FanFavoritesDao instance = null;
  protected FanFavoritesDao() {
    connectionManager = new ConnectionManager();
  }
  public static FanFavoritesDao getInstance() {
    if (instance == null) {
      instance = new FanFavoritesDao();
    }
    return instance;
  }


  public List<Movies> getTopFanFavorites() throws SQLException {
    List<Movies> favoriteMovies = new ArrayList<Movies>();
    String selectFavoriteMovies = "SELECT t.movie_id, t.title, t.is_adult, t.year, t.runtime_minutes, t.genre FROM (SELECT Movies.movie_id, Movies.title, Movies.is_adult, Movies.year, Movies.runtime_minutes, Movies.genre, COUNT(Favorites.movie_id) AS favor_count FROM Movies JOIN Favorites ON Movies.movie_id = Favorites.movie_id GROUP BY Movies.movie_id ORDER BY favor_count DESC LIMIT 10)t;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectFavoriteMovies);
      results = selectStmt.executeQuery();
      while(results.next()) {
        String movieId = results.getString("movie_id");
        String title = results.getString("title");
        Boolean isAdult = results.getBoolean("is_adult");
        String year = results.getString("year");
        int runTimeMinutes = results.getInt("runtime_minutes");
        Movies.movieGenre genre = Movies.movieGenre.valueOf(results.getString("genre"));
        Movies movie = new Movies(movieId, title, isAdult, year, runTimeMinutes, genre);
        favoriteMovies.add(movie);
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
    return favoriteMovies;
  }
}

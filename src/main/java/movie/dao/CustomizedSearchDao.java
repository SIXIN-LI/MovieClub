package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import movie.model.MovieViews;
import movie.model.Movies;
import movie.model.Users;

public class CustomizedSearchDao {
	protected ConnectionManager connectionManager;

	private static CustomizedSearchDao instance = null;

	protected CustomizedSearchDao() {
		connectionManager = new ConnectionManager();
	}

	public static CustomizedSearchDao getInstance() {
		if (instance == null) {
			instance = new CustomizedSearchDao();
		}
		return instance;
	}
	
//	given the movie_id, we can get the list of movie_view history
	
	
	public List<Movies> getMoviesByGenreAndYear(String genre, String year) throws SQLException {
		List<Movies> yearAndGenreMovies = new ArrayList<Movies>();
		String selectMovies = "SELECT m.* FROM Movies m INNER JOIN Rating r ON m.movie_id = r.movie_id WHERE m.year = ? AND m.genre = ? ORDER BY r.average_rating DESC LIMIT 10;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMovies);
			selectStmt.setString(1, year);
			selectStmt.setString(2, genre);
			results = selectStmt.executeQuery();
			while (results.next())  {
				String movieId = results.getString("movie_id");
				String title = results.getString("title");
				boolean isAdult = results.getBoolean("is_adult");
				String resultyear = results.getString("year");
				int runTimeMinutes = results.getInt("runtime_minutes");
				Movies.movieGenre resultgenre = Movies.movieGenre.valueOf(results.getString("genre"));
				Movies newMovie = new Movies(movieId, title, isAdult, resultyear, runTimeMinutes, resultgenre);
			
			
				yearAndGenreMovies.add(newMovie);
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
		return yearAndGenreMovies;
	}
	
	public List<Movies> getMoviesByRatingAndGenre(String genre, double points) throws SQLException {
		List<Movies> ratingAndGenreMovies = new ArrayList<Movies>();
		String selectMovies = "SELECT m.*, r.average_rating\n"
				+ "FROM Movies m\n"
				+ "INNER JOIN Rating r ON m.movie_id = r.movie_id\n"
				+ "WHERE m.genre = ? AND r.average_rating >?\n"
				+ "ORDER BY r.average_rating DESC;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMovies);
			selectStmt.setString(1, genre);
			selectStmt.setDouble(2, points);
			results = selectStmt.executeQuery();
			while (results.next())  {
				String movieId = results.getString("movie_id");
				String title = results.getString("title");
				boolean isAdult = results.getBoolean("is_adult");
				String resultyear = results.getString("year");
				int runTimeMinutes = results.getInt("runtime_minutes");
				Movies.movieGenre resultgenre = Movies.movieGenre.valueOf(results.getString("genre"));
				Movies newMovie = new Movies(movieId, title, isAdult, resultyear, runTimeMinutes, resultgenre);
			
				ratingAndGenreMovies.add(newMovie);
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
		return ratingAndGenreMovies;
	}
	
	
	
}
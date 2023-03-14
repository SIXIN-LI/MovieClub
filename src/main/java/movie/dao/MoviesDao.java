package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import movie.model.Movies;

public class MoviesDao {
	protected ConnectionManager connectionManager;

	private static MoviesDao instance = null;

	protected MoviesDao() {
		connectionManager = new ConnectionManager();
	}

	public static MoviesDao getInstance() {
		if (instance == null) {
			instance = new MoviesDao();
		}
		return instance;
	}

	public Movies getMovieByMovieId(String movieId) throws SQLException {
		String selectMovie = "SELECT * FROM Movies WHERE movie_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMovie);
			selectStmt.setString(1, movieId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				String title = results.getString("title");
				boolean isAdult = results.getBoolean("is_adult");
				String year = results.getString("year");
				int runTimeMinutes = results.getInt("runtime_minutes");
				Movies.movieGenre genre = Movies.movieGenre.valueOf(results.getString("genre"));
				Movies foundMovie = new Movies(movieId, title, isAdult, year, runTimeMinutes, genre);
				return foundMovie;
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

	// Given a input name string, this method finds all the movies whose title
	// contains this specific name string in a case-insensitive way
	// and return all matched movies
	public List<Movies> getMoviesByName(String name) throws SQLException {
		List<Movies> movies = new ArrayList<Movies>();
		String selectMovies = "SELECT * FROM Movies WHERE title LIKE ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMovies);
			String regex = "%" + name + "%";
			selectStmt.setString(1, regex);
			results = selectStmt.executeQuery();
			while (results.next()) {
				String movieId = results.getString("movie_id");
				String title = results.getString("title");
				boolean isAdult = results.getBoolean("is_adult");
				String year = results.getString("year");
				int runTimeMinutes = results.getInt("runtime_minutes");
				Movies.movieGenre genre = Movies.movieGenre.valueOf(results.getString("genre"));
				Movies foundMovie = new Movies(movieId, title, isAdult, year, runTimeMinutes, genre);
				movies.add(foundMovie);
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
		return movies;
	}
}

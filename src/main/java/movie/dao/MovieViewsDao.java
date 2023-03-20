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

public class MovieViewsDao {
	protected ConnectionManager connectionManager;

	private static MovieViewsDao instance = null;

	public MovieViewsDao() {
		connectionManager = new ConnectionManager();
	}

	public static MovieViewsDao getInstance() {
		if (instance == null) {
			instance = new MovieViewsDao();
		}
		return instance;
	}
	
//	given the movie_id, we can get the list of movie_view history
	
	public List<MovieViews> getMovieViewsByMovieId(Movies movie) throws SQLException {
		List<MovieViews> movieViews = new ArrayList<MovieViews>();
		String selectMovieViews ="SELECT \n"
				+ "	MovieViews.* \n"
				+ "FROM MovieViews\n"
				+ "WHERE MovieViews.movie_id=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMovieViews);
			selectStmt.setString(1, movie.getMovieId());
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			MoviesDao moviesDao = MoviesDao.getInstance();
			if (results.next()) {
				int resultviewId = results.getInt("view_id");
				int  userId = results.getInt("user_id");
				Users user = usersDao.getUserByUserId(userId);
				String movieId = results.getString("movie_id");
				Movies resultmovie = moviesDao.getMovieByMovieId(movieId);
				Date viewTime = new Date(results.getTimestamp("view_time").getTime());
				MovieViews newMovieView = new MovieViews(resultviewId,user,resultmovie,viewTime);
			
				movieViews.add(newMovieView);
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
		return movieViews;
	}
	
	public List<MovieViews> getMovieViewsByUserId(Users user) throws SQLException {
		List<MovieViews> movieViews = new ArrayList<MovieViews>();
		String selectMovieViews ="SELECT \n"
				+ "	MovieViews.* \n"
				+ "FROM MovieViews\n"
				+ "WHERE user_id=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMovieViews);
			selectStmt.setInt(1, user.getUserId());
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			MoviesDao moviesDao = MoviesDao.getInstance();
			while (results.next()) {
				int resultviewId = results.getInt("view_id");
				int  userId = results.getInt("user_id");
				Users resultuser = usersDao.getUserByUserId(userId);
				String movieId = results.getString("movie_id");
				Movies resultmovie = moviesDao.getMovieByMovieId(movieId);
				Date viewTime = new Date(results.getTimestamp("view_time").getTime());
				MovieViews newMovieView = new MovieViews(resultviewId,resultuser,resultmovie,viewTime);
			
				movieViews.add(newMovieView);
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
		return movieViews;
	}
	public MovieViews getMovieViewsByViewId(int viewId) throws SQLException {
		
		String selectMovieViews ="SELECT \n"
				+ "	MovieViews.* \n"
				+ "FROM MovieViews\n"
				+ "WHERE view_id=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMovieViews);
			selectStmt.setInt(1, viewId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			MoviesDao moviesDao = MoviesDao.getInstance();
			while (results.next()) {
				int resultviewId = results.getInt("view_id");
				int  userId = results.getInt("user_id");
				Users resultuser = usersDao.getUserByUserId(userId);
				String movieId = results.getString("movie_id");
				Movies resultmovie = moviesDao.getMovieByMovieId(movieId);
				Date viewTime = new Date(results.getTimestamp("view_time").getTime());
				MovieViews newMovieView = new MovieViews(resultviewId,resultuser,resultmovie,viewTime);
			
				return newMovieView;
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
	
	
// delete movieView 

	public MovieViews delete(MovieViews movieView) throws SQLException {
		String deleteMovieView = "DELETE FROM MovieViews WHERE view_id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteMovieView);
			deleteStmt.setInt(1, movieView.getViewId());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
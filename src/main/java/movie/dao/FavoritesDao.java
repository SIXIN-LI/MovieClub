package movie.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import movie.model.Favorites;
import movie.model.Movies;
import movie.model.Users;

/**
 * Siyuan Chen
 * Data access object (DAO) class to interact with the underlying Favorites table in your MySQL
 * instance. This is used to store {@link Favorites} into your MySQL instance and retrieve
 * {@link Favorites} from MySQL instance.
 */
public class FavoritesDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static FavoritesDao instance = null;
	protected FavoritesDao() {
		connectionManager = new ConnectionManager();
	}
	public static FavoritesDao getInstance() {
		if(instance == null) {
			instance = new FavoritesDao();
		}
		return instance;
	}

	/**
	 * Save the Restaurants instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Favorites create(Favorites favorite) throws SQLException {
		String insertFavorite = "INSERT INTO Favorites(movie_id, user_id) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFavorite, Statement.RETURN_GENERATED_KEYS);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setString(1, favorite.getMovies().getMovieId());
			insertStmt.setInt(2, favorite.getUsers().getUserId());

			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			
			// Note 1: if this was an UPDATE statement, then the person fields should be
			// updated before returning to the caller.
			// Note 2: there are no auto-generated keys, so no update to perform on the
			// input param favorite.
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int favorite_id = -1;
			if(resultKey.next()) {
				favorite_id = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			favorite.setFavoriteId(favorite_id);
			return favorite;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	/**
	 * Get the Favorites record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Favorite instance.
	 */
	public Favorites getFavoriteById(int favoriteId) throws SQLException {
		String selectFavorite = "SELECT favorite_id, movie_id, user_id FROM Favorites WHERE favorite_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFavorite);
			selectStmt.setInt(1, favoriteId);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int resultFavoriteId = results.getInt("favorite_id");
				String movieId = results.getString("movie_id");
				Movies movie = MoviesDao.getInstance().getMovieByMovieId(movieId);
				int userId = results.getInt("user_id");
				Users user = UsersDao.getInstance().getUserByUserId(userId);
				Favorites favorite = new Favorites(resultFavoriteId, movie, user);
				return favorite;
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
	
	public List<Favorites> getFavoritesByUserId(int userId) throws SQLException {
		List<Favorites> favorites = new ArrayList<Favorites>();
		String selectFavorite = "SELECT favorite_id, movie_id, user_id FROM Favorites WHERE user_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFavorite);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int favoriteId = results.getInt("favorite_id");
				String movieId = results.getString("movie_id");
				Movies movie = MoviesDao.getInstance().getMovieByMovieId(movieId);
				int resultUserId = results.getInt("user_id");
				Users user = UsersDao.getInstance().getUserByUserId(resultUserId);
				Favorites favorite = new Favorites(favoriteId, movie, user);
				favorites.add(favorite);
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
		return favorites;
	}
	
	public List<Favorites> getFavoritesByMovieId(String movieId) throws SQLException {
		List<Favorites> favorites = new ArrayList<Favorites>();
		String selectFavorite = "SELECT favorite_id, movie_id, user_id FROM Favorites WHERE movie_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFavorite);
			selectStmt.setString(1, movieId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int favoriteId = results.getInt("favorite_id");
				String resultMovieId = results.getString("movie_id");
				Movies movie = MoviesDao.getInstance().getMovieByMovieId(resultMovieId);
				int userId = results.getInt("user_id");
				Users user = UsersDao.getInstance().getUserByUserId(userId);
				Favorites favorite = new Favorites(favoriteId, movie, user);
				favorites.add(favorite);
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
		return favorites;
	}
	
	/**
	 * Delete the Favorites instance.
	 * This runs a DELETE statement.
	 */
	public Favorites delete(Favorites favorite) throws SQLException {
		String deleteFavorite = "DELETE FROM Favorites WHERE favorite_id =?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteFavorite);
            deleteStmt.setInt(1, favorite.getFavoriteId());
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

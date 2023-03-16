package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import movie.model.Movies;

/**
 * This class will display known movies for each crew
 */
public class KnownMovieDao {

    private static KnownMovieDao instance = null;
    private ConnectionManager connectionManager;

    public KnownMovieDao() {
        this.connectionManager = new ConnectionManager();
    }

    public static KnownMovieDao getInstance() {
        if (instance == null) {
            instance = new KnownMovieDao();
        }
        return instance;
    }

    public List<Movies> getKnownMoviesByCrewId(String crewId) throws SQLException {
        List<Movies> list = new ArrayList<>();
        String selectCompany = "SELECT movie_id FROM KnownMovies WHERE crew_id=?;";
        MoviesDao moviesDao = MoviesDao.getInstance();

        try (Connection connection = connectionManager.getConnection();
            PreparedStatement selectStmt = connection.prepareStatement(selectCompany)) {
            selectStmt.setString(1, crewId);
            // get credit card from database
            ResultSet results = selectStmt.executeQuery();
            while (results.next()) {
                String movieId = results.getString("movie_id");
                Movies movie = moviesDao.getMovieByMovieId(movieId);

                list.add(movie);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}

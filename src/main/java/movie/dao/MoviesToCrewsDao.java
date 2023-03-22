package movie.dao;


import movie.model.Crew;
import movie.model.Movies;
import movie.model.MoviesToCrews;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * As a MovieToCrew table operator, I will need to get/add.
 */
public class MoviesToCrewsDao {

    private static MoviesToCrewsDao instance = null;
    private ConnectionManager connectionManager;

    public MoviesToCrewsDao() {
        this.connectionManager = new ConnectionManager();
    }

    public static MoviesToCrewsDao getInstance() {
        if(instance == null) {
            instance = new MoviesToCrewsDao();
        }
        return instance;
    }


    // Users creates an MoviesToCrews, and then we will return a MovieToCrews Id
    public MoviesToCrews createMoviesToCrew(MoviesToCrews moviesToCrews) throws SQLException {
        String insertMoviesToCrews = "INSERT INTO MovieToCrews(movie_to_crew_id, movie_id, crew_id, job_category) VALUES(?,?,?,?);";
        // try with automatic resource management will close these resources at the end
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertMoviesToCrews,
                     Statement.RETURN_GENERATED_KEYS)) {

            // set parameters
            insertStmt.setInt(1, moviesToCrews.getMovieToCrewId());
            insertStmt.setString(3, moviesToCrews.getCrew().getCrewId());
            insertStmt.setString(2, moviesToCrews.getMovie().getMovieId());
            insertStmt.setString(4, moviesToCrews.getJobCategory().toString());

            // load into database
            insertStmt.executeUpdate();

            ResultSet resultKey = insertStmt.getGeneratedKeys();
            int id = -1;
            if(resultKey.next()) {
                id = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            moviesToCrews.setMovieToCrewId(id);

            return moviesToCrews;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Crew> getCrewsByMovieId(String movieId) throws SQLException {
        String select = "SELECT crew_id FROM MovieToCrews WHERE movie_id=?;";
        List<Crew> res = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(select)) {
            selectStmt.setString(1, movieId);
            ResultSet results = selectStmt.executeQuery();
            while (results.next()) {
                String crewId = results.getString("crew_id");
                Crew crew = CrewDao.getInstance().getCrewByCrewId(crewId);
                res.add(crew);
            }
            return res;


        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
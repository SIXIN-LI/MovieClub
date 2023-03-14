package movie.tools;

import java.sql.SQLException;
import java.util.List;
import movie.dao.KnownMovieDao;
import movie.model.Movies;

public class TestKnownMovieDao {

    public static void main(String[] args) throws SQLException {
        KnownMovieDao knownMovieDao = KnownMovieDao.getInstance();

        List<Movies> list = knownMovieDao.getKnownMoviesByCrewId("nm0000001");
        for (Movies movie: list) {
            System.out.println("Listing : " + movie);
        }
    }
}

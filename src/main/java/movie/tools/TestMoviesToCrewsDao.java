package movie.tools;

import movie.dao.*;
import movie.model.*;

import java.sql.SQLException;
import java.sql.Timestamp;

public class TestMoviesToCrewsDao {
    public static void main(String[] args) throws SQLException {
        CrewDao crewDao = CrewDao.getInstance();
        Crew crew = crewDao.getCrewByCrewId("nm0000001");

        MoviesDao moviesDao = MoviesDao.getInstance();
        Movies movie = moviesDao.getMovieByMovieId("tt0000009");

        MoviesToCrews mtc = new MoviesToCrews(movie, crew, MoviesToCrews.JobCategory.ACTOR);
        MoviesToCrewsDao moviesToCrewsDao = MoviesToCrewsDao.getInstance();
        moviesToCrewsDao.createMoviesToCrew(mtc);

        MoviesToCrews fetchedMtc = moviesToCrewsDao.getMoviesToCrewsById(mtc.getMovieToCrewId());
        assert fetchedMtc.getMovie().getMovieId() == movie.getMovieId();
        assert fetchedMtc.getCrew().getCrewId() == crew.getCrewId();
        assert fetchedMtc.getJobCategory().equals(MoviesToCrews.JobCategory.ACTOR.toString());
    }

}

package movie.tools;

import movie.dao.*;
import movie.model.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class TestMoviesToCrewsDao {
    public static void main(String[] args) throws SQLException {
        CrewDao crewDao = CrewDao.getInstance();
        Crew crew1 = crewDao.getCrewByCrewId("nm0000001");
        Crew crew2 = crewDao.getCrewByCrewId("nm1309758");

        MoviesDao moviesDao = MoviesDao.getInstance();
        Movies movie = moviesDao.getMovieByMovieId("tt0000009");

        MoviesToCrews mtc1 = new MoviesToCrews(movie, crew1, MoviesToCrews.JobCategory.ACTOR);
        MoviesToCrews mtc2 = new MoviesToCrews(movie, crew2, MoviesToCrews.JobCategory.ACTOR);

        MoviesToCrewsDao moviesToCrewsDao = MoviesToCrewsDao.getInstance();
        moviesToCrewsDao.createMoviesToCrew(mtc1);
        moviesToCrewsDao.createMoviesToCrew(mtc2);


        List<Crew> crews = moviesToCrewsDao.getCrewsByMovieId("tt0000009");
        System.out.println("crews size: " + crews.size());
        for (Crew c : crews) {
            System.out.println(c.getCrewId());
        }
    }

}

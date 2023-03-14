package movie.tools;

import java.sql.SQLException;
import java.util.List;

import movie.dao.CrewDao;
import movie.dao.MoviesDao;
import movie.model.Crew;
import movie.model.Movies;

public class TestMoviesCrewDao {
	public static void main(String[] args) throws SQLException {
		// DAO instances.
		MoviesDao moviesDao = MoviesDao.getInstance();
		CrewDao crewDao = CrewDao.getInstance();

		// READ
		// read movies
		Movies foundMovie1 = moviesDao.getMovieByMovieId("tt0000009");
		System.out.println("found movie with movie_id 'tt0000009':" + foundMovie1);
		Movies foundMovie2 = moviesDao.getMovieByMovieId("tt0000147");
		System.out.println("found movie with movie_id 'tt0000147':" + foundMovie2);
		List<Movies> allMovies = moviesDao.getMoviesByName("HARRY POTTER");
		System.out.println("start reading Movies whose name contains 'HARRY POTTER':");
		for (Movies m : allMovies) {
			System.out.println(m);
		}
		// read crew
		Crew foundCrew1 = crewDao.getCrewByCrewId("nm0000001");
		System.out.println("found Crew with crew_id 'nm0000001':" + foundCrew1);
		Crew foundCrew2 = crewDao.getCrewByCrewId("nm0000003");
		System.out.println("found Crew with crew_id 'nm0000003':" + foundCrew2);
	}
}

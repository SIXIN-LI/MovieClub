package movie.tools;

import java.sql.SQLException;
import java.util.List;


import movie.dao.CustomizedSearchDao;

import movie.model.Movies;

public class TestCustomizedSearchDao {
	public static void main(String[] args) throws SQLException {
		CustomizedSearchDao customizedSearchDao = CustomizedSearchDao.getInstance();

		// READ
		// read movies

	
		List<Movies> ratingAndGenreMovies = customizedSearchDao.getMoviesByRatingAndGenre("crime", 9.2);
		for (Movies rgm : ratingAndGenreMovies) {
			System.out.format("looping movies of rating>9.7 and genre is 'crime': movie:%s genre:%s long:%d \n",
					rgm.getTitle(), rgm.getGenre(), rgm.getRuntimeMinutes());
		}
		
	
		List<Movies> genreMovies = customizedSearchDao.getMoviesByGenre("crime");
		for (Movies gm : genreMovies) {
			System.out.format("looping movies whose genre is 'crime': movie:%s genre:%s long:%d \n",
					gm.getTitle(), gm.getGenre(), gm.getRuntimeMinutes());
		}
		
		
		List<Movies> ratingMovies = customizedSearchDao.getMoviesByRate(9.7);
		for (Movies rm : ratingMovies) {
			System.out.format("looping movies whose rating is 9.7': movie:%s genre:%s long:%d \n",
					rm.getTitle(), rm.getGenre(), rm.getRuntimeMinutes());
		}
		
	}
}

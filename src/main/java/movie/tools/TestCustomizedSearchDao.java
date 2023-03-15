package movie.tools;

import java.sql.SQLException;
import java.util.List;


import movie.dao.CustomizedSearchDao;
import movie.dao.MoviesDao;
import movie.dao.RatingDao;
import movie.model.Rating;
import movie.model.Movies;

public class TestCustomizedSearchDao {
	public static void main(String[] args) throws SQLException {
		CustomizedSearchDao customizedSearchDao = CustomizedSearchDao.getInstance();

		// READ
		// read movies

		List<Movies> yearAndGenreMovies = customizedSearchDao.getMoviesByGenreAndYear("crime", "2022");
		for (Movies ygm : yearAndGenreMovies) {
			System.out.format("looping movies of year 2022 and genre is 'crime': movie:%s genre:%s year:%s \n",
					ygm.getTitle(), ygm.getGenre(), ygm.getYear());
		}
//	
//		
		List<Movies> ratingAndGenreMovies = customizedSearchDao.getMoviesByRatingAndGenre("crime", 9.2);
		for (Movies rgm : ratingAndGenreMovies) {
			System.out.format("looping movies of rating>9.7 and genre is 'crime': movie:%s genre:%s long:%d \n",
					rgm.getTitle(), rgm.getGenre(), rgm.getRuntimeMinutes());
		}
//	

	}
}

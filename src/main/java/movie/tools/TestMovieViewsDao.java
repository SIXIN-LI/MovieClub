package movie.tools;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

//import movie.dao.CrewDao;
import movie.dao.MovieViewsDao;
import movie.dao.MoviesDao;
import movie.dao.UsersDao;
//import movie.model.Crew;
import movie.model.MovieViews;
import movie.model.Movies;
import movie.model.Users;

public class TestMovieViewsDao {
	public static void main(String[] args) throws SQLException {
		// DAO instances.
		MoviesDao moviesDao = MoviesDao.getInstance();
		MovieViewsDao movieViewsDao = MovieViewsDao.getInstance();
		UsersDao usersDao  = UsersDao .getInstance();

		// READ
		// read movieviews
		Movies foundMovie1 = moviesDao.getMovieByMovieId("tt0087277");
		System.out.println("found movie with movie_id 'tt0087277':" + foundMovie1);
		
		Users fetchUser = usersDao.getUserByUserId(7124);
		System.out.println("find user by user_id '7124':" + fetchUser);
		
		
		List<MovieViews> fetchMovieViews = movieViewsDao.getMovieViewsByMovieId(foundMovie1);
		for(MovieViews fl : fetchMovieViews) {
			System.out.format("Looping MovieViews view_id:%d time:%s movieId:%s \n",
				fl.getViewId(), fl.getViewTime(), fl.getMovie().getMovieId());
		}
		
		List<MovieViews> fetchMovieViewsByUser = movieViewsDao.getMovieViewsByUserId(fetchUser);
		for(MovieViews ful : fetchMovieViewsByUser) {
			System.out.format("Looping MovieViews user_id:%d time:%s movieId:%s \n",
				ful.getViewId(), ful.getViewTime(), ful.getMovie().getMovieId());
		}
		
//		delete movieView
		for (MovieViews movieView : fetchMovieViews) {
		    movieViewsDao.delete(movieView);
		}
	
		assert Objects.isNull(fetchMovieViews);
		
		
	}
}
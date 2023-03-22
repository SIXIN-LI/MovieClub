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
		
		Users fetchUser = usersDao.getUserByUserId(9033);
		System.out.println("find user by user_id '9033':" + fetchUser);
		
		
		List<MovieViews> fetchMovieViews = movieViewsDao.getMovieViewsByMovieId(foundMovie1);
		for(MovieViews fl : fetchMovieViews) {
			System.out.format("Looping MovieViews view_id:%d time:%s movieId:%s \n",
				fl.getViewId(), fl.getViewTime(), fl.getMovie().getMovieId());
		}
		
		List<MovieViews> fetchMovieViewsByUser = movieViewsDao.getMovieViewsByUserId(fetchUser);
		for(MovieViews ful : fetchMovieViewsByUser) {
			System.out.format("Looping MovieViews by user_id:viewId:%d time:%s movieName:%s \n",
				ful.getViewId(), ful.getViewTime(), ful.getMovie().getTitle());
		}
		
		MovieViews fetchMovieViewsByViewId = movieViewsDao.getMovieViewsByViewId(4);
		System.out.println("find user by view_id '4':" + fetchMovieViewsByViewId.getViewId());
		

		for (MovieViews flv : fetchMovieViewsByUser ) {
		    movieViewsDao.delete(fetchUser);
		}
	
		assert Objects.isNull(fetchMovieViews);
		System.out.println("successfully deleted" );
			
		
	}
}
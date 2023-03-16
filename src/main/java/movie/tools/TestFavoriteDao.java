package movie.tools;

import java.sql.SQLException;
import java.util.List;
import movie.dao.FavoritesDao;
import movie.dao.MoviesDao;
import movie.dao.UsersDao;
import movie.model.Favorites;
import movie.model.Movies;
import movie.model.Users;

public class TestFavoriteDao {

  public static void main(String[] args) throws SQLException {
    FavoritesDao favoritesDao = FavoritesDao.getInstance();
    MoviesDao moviesDao = MoviesDao.getInstance();
    UsersDao usersDao = UsersDao.getInstance();

    Movies movie = moviesDao.getMovieByMovieId("tt0000009");
    Users user = usersDao.getUserByUserId(1);
    Favorites favorite = new Favorites(movie, user);

    favorite = favoritesDao.create(favorite);

    Favorites favorite1 = favoritesDao.getFavoriteById(1);
    System.out.format("Reading Favorites: f:%s m:%s u:%s \n",
        favorite.getFavoriteId(), favorite.getMovies().getMovieId(), favorite.getUsers().getUserId());

    //getFavoritesByUserId
    List<Favorites> favoriteList = favoritesDao.getFavoritesByUserId(1);
    for(Favorites f : favoriteList) {
      System.out.format("Looping Favorites: f:%s m:%s u:%s \n",
          f.getFavoriteId(), f.getMovies().getMovieId(), f.getUsers().getUserId());
    }

    // getFavoritesByMovieId
    List<Favorites> favoriteList1 = favoritesDao.getFavoritesByMovieId("tt0000009");
    for(Favorites f : favoriteList1) {
      System.out.format("getFavoritesByMovieId Method Looping Favorites: f:%s m:%s u:%s \n",
          f.getFavoriteId(), f.getMovies().getMovieId(), f.getUsers().getUserId());
    }

    favoritesDao.delete(favorite1);
  }
}

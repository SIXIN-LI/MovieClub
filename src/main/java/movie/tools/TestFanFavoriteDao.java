package movie.tools;

import java.sql.SQLException;
import java.util.List;
import movie.dao.FanFavoritesDao;
import movie.dao.MoviesDao;
import movie.model.Favorites;
import movie.model.Movies;

public class TestFanFavoriteDao {
  public static void main(String[] args) throws SQLException {
    FanFavoritesDao fanFavoritesDao = FanFavoritesDao.getInstance();

    List<Movies> fanFavoriteList = fanFavoritesDao.getTopFanFavorites();
    for(Movies m : fanFavoriteList) {
      System.out.format("Looping Fan Favorites: m:%s t:%s a:%s y:%s r:%s g:%s\n",
          m.getMovieId(), m.getTitle(), m.getIsAdult(), m.getYear(), m.getRuntimeMinutes(), m.getGenre());
    }
  }
}

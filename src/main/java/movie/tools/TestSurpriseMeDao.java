package movie.tools;

import java.sql.SQLException;
import movie.dao.SurpriseMeDao;
import movie.model.Movies;

public class TestSurpriseMeDao {
  public static void main(String[] args) throws SQLException {
    SurpriseMeDao surpriseMeDao = SurpriseMeDao.getInstance();

    Movies surpriseMeMovie = surpriseMeDao.getSurpriseMeByUserId(7124);
    System.out.format("Read surpriseMeMovie: m:%s t:%s a:%s y:%s r:%s g:%s\n",
        surpriseMeMovie.getMovieId(), surpriseMeMovie.getTitle(), surpriseMeMovie.getIsAdult(), surpriseMeMovie.getYear(), surpriseMeMovie.getRuntimeMinutes(), surpriseMeMovie.getGenre());
  }
}

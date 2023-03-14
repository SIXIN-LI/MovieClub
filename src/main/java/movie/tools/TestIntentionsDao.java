package movie.tools;

import movie.dao.IntentionsDao;
import movie.dao.MoviesDao;
import movie.dao.UsersDao;
import movie.model.Intentions;
import movie.model.Movies;
import movie.model.Users;

import java.sql.SQLException;
import java.sql.Timestamp;

public class TestIntentionsDao {
    public static void main(String[] args) throws SQLException {
        Users user = new Users("112233","888","Sixin", "Li",  Users.genderType.FEMALE);
        UsersDao usersDao = UsersDao.getInstance();
        usersDao.createUser(user);

        MoviesDao moviesDao = MoviesDao.getInstance();
        Movies movie = moviesDao.getMovieByMovieId("tt0000009");

        Timestamp ts = Timestamp.valueOf("2016-03-12 20:45:00");
        Intentions intentions = new Intentions(user, movie, ts, Intentions.Action.WATCHED);
        IntentionsDao intentionDao = IntentionsDao.getInstance();
        intentionDao.createIntentions(intentions);

        Intentions fetchedIntentions = intentionDao.getIntentionById(intentions.getIntentionId());
        assert fetchedIntentions.getMovie().getMovieId() == movie.getMovieId();
        assert fetchedIntentions.getUser().getUserId() == user.getUserId();
        assert fetchedIntentions.getAction() == Intentions.Action.WATCHED;
        assert fetchedIntentions.getTimestamp() == ts;
    }
}

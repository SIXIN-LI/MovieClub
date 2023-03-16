package movie.tools;

import java.sql.SQLException;
import movie.dao.UsersDao;
import movie.model.Users;
import movie.model.Users.genderType;

public class TestUserDao {

    public static void main(String[] args) throws SQLException {
        Users user = new Users("112233","888","Sixin", "Li",  genderType.FEMALE);
        Users user2 = new Users("112234","111","Sixin", "Li",  genderType.FEMALE);
        UsersDao usersDao = UsersDao.getInstance();

        usersDao.createUser(user);
        usersDao.createUser(user2);

        Users gotUser = usersDao.getUserByUserId(user.getUserId());
        if (gotUser.equals(user)) {
            System.out.println("matched got");
        }

        // test data match in database
        Users gotUser3 = usersDao.getUserByUserId(1);
        if (gotUser3.getFirstName().equals("Kennedy")) {
            System.out.println("data match");
        }

        user.setFirstName("Cece");
        usersDao.updateUserByOneParam(user);

        Users gotUser2 = usersDao.getUserByUserId(user.getUserId());
        if (gotUser2.getFirstName().equals("Cece")) {
            System.out.println("update success");
        }

        // test data match in database
        Users gotUser3 = usersDao.getUserByUserId(1);
        if (gotUser3.getFirstName().equals("Kennedy")) {
            System.out.println("data match");
        }

        Users gotLogInUser = usersDao.getUserByUserNameAndPassword("112234", "111");
        if (gotLogInUser!= null && gotLogInUser.equals(user2)) {
            System.out.println("Log in success");
        }
        Users gotNoneUser = usersDao.getUserByUserNameAndPassword("112234", "222");
        if (gotNoneUser == null) {
            System.out.println("Expected non-existent username or password");
        }
    }
}

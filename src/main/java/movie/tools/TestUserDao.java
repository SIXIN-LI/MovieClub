package movie.tools;

import java.sql.SQLException;
import movie.dao.UsersDao;
import movie.model.Users;
import movie.model.Users.genderType;

public class TestUserDao {

    public static void main(String[] args) throws SQLException {
        Users user = new Users("112233","888","Sixin", "Li",  genderType.FEMALE);
        UsersDao usersDao = UsersDao.getInstance();

        usersDao.createUser(user);

        Users gotUser = usersDao.getUserByUserId(user.getUserId());
        if (gotUser.equals(user)) {
            System.out.println("matched got");
        }

        user.setFirstName("Cece");
        usersDao.updateUserByOneParam(user);

        Users gotUser2 = usersDao.getUserByUserId(user.getUserId());
        if (gotUser2.getFirstName().equals("Cece")) {
            System.out.println("update success");
        }
    }
}

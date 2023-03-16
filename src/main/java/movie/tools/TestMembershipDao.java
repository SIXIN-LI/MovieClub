package movie.tools;

import java.sql.SQLException;
import java.sql.Timestamp;
import movie.dao.MembershipDao;
import movie.dao.UsersDao;
import movie.model.MemberShip;
import movie.model.Users;
import movie.model.Users.genderType;

public class TestMembershipDao {

    public static void main(String[] args) throws SQLException {
        // Need to insert users firstly since users is the foreign key of Membership
        UsersDao usersDao = UsersDao.getInstance();
        Users user = new Users("112233","888","Lila", "Li",  genderType.FEMALE);
        usersDao.createUser(user);
        System.out.println("User created successfully!");

        // Initilize a MembershipDao instance
        MembershipDao membershipDao = MembershipDao.getInstance();
        Timestamp timestamp = Timestamp.valueOf("2007-09-23 10:10:10.0");
        MemberShip memberShip = new MemberShip("14519895", user, timestamp);

        // Test create
        membershipDao.create(memberShip);
        System.out.println("Membership created successfully in the database!");

        // Test getMemberFromUserName
        MemberShip gotMember = membershipDao.getMemberFromUserId(user.getUserId());
        if (gotMember.equals(memberShip)) {
            System.out.println("Membership successfully got from the database: " + gotMember);
        }
    }
}


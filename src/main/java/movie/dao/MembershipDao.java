package movie.dao;

import movie.model.MemberShip;
import movie.model.Users;
import java.sql.*;


public class MembershipDao {
    private static MembershipDao instance = null;
    private ConnectionManager connectionManager;

    protected MembershipDao() {
        connectionManager = new ConnectionManager();
    }

    public static MembershipDao getInstance() {
        if(instance == null) {
            instance = new MembershipDao();
        }
        return instance;
    }

    public MemberShip create(MemberShip membership) throws SQLException {
        // naming conventions should be the same as we create the table in sql
        // even though the user_id is User object in the data model
        String insertMember = "INSERT INTO Membership(membership_id, user_id, timestamp) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertMember);
            // the type shall also be consistent within the sql query
            insertStmt.setString(1, membership.getMembershipId());
            insertStmt.setInt(2, membership.getUser().getUserId());
            insertStmt.setTimestamp(3, membership.getTimestamp());
            insertStmt.executeUpdate();
            return membership;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public MemberShip getMemberFromUserName(Integer userId) throws SQLException {
        String selectMember = "SELECT membership_id,user_id,timestamp FROM Membership WHERE user_id =?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectMember);
            selectStmt.setInt(1, userId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                String membershipId = results.getString("membership_id");
                Integer usersId = results.getInt("user_id");
                Timestamp timestamp = results.getTimestamp("timestamp");

                // Todo: depend on usersdao
                Users usersDao = UsersDao.getInstance();
                Users user = usersDao.getUserFromUserId(usersId);
                MemberShip member = new MemberShip(membershipId, user, timestamp);
                return member;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return null;
    }
}
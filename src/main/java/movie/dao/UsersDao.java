package movie.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import movie.model.Users;

/**
 * As a normal user, I will need to access, register, update
 */
public class UsersDao {

    private static UsersDao instance = null;
    private ConnectionManager connectionManager;

    public UsersDao() {
        this.connectionManager = new ConnectionManager();
    }

    public static UsersDao getInstance() {
        if(instance == null) {
            instance = new UsersDao();
        }
        return instance;
    }


    // User create an account, and then we will return a user_id as the login username
    public Users createUser(Users user) throws SQLException {
        String insertUser = "INSERT INTO Users(user_name, password, first_name, last_name, gender) VALUES(?,?,?,?,?);";
        // try with automatic resource management will close these resources at the end
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement insertStmt = connection.prepareStatement(insertUser,
                Statement.RETURN_GENERATED_KEYS)) {
            // set parameters
            insertStmt.setString(1, user.getUserName());
            insertStmt.setString(2, user.getPassword());
            insertStmt.setString(3, user.getFirstName());
            insertStmt.setString(4, user.getLastName());
            insertStmt.setString(5, user.getGender().toString());

            // load into database
            insertStmt.executeUpdate();

            ResultSet resultKey = insertStmt.getGeneratedKeys();
            int id = -1;
            if(resultKey.next()) {
                id = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            user.setUserId(id);

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }


    /**
     * Since all info except userId can be updated, then we will rewrite all of them everytime
     * Only userId cannot be updated
     */
    public boolean updateUserByOneParam(Users user)
        throws SQLException {
        String updateUser = "UPDATE Users SET user_name=?, password=?, first_name=?, last_name=?, gender=? WHERE user_id=?;";
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement updateStmt = connection.prepareStatement(updateUser)) {

            updateStmt.setString(1, user.getUserName());
            updateStmt.setString(2, user.getPassword());
            updateStmt.setString(3, user.getFirstName());
            updateStmt.setString(4, user.getLastName());
            updateStmt.setString(5, user.getGender().toString());
            updateStmt.setInt(6, user.getUserId());
            int affectedRows = updateStmt.executeUpdate();

            if (affectedRows != 0) return true;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Users getUserByUserId(int userId) throws SQLException {
        String selectCompany = "SELECT user_name, password, first_name, last_name, gender FROM Users WHERE user_id=?;";

        try (Connection connection = connectionManager.getConnection();
            PreparedStatement selectStmt = connection.prepareStatement(selectCompany)) {
            selectStmt.setInt(1, userId);
            // get credit card from database
            ResultSet results = selectStmt.executeQuery();
            if (results.next()) {
                String userName = results.getString("user_name");
                String password = results.getString("password");
                String firstName = results.getString("first_name");
                String lastName = results.getString("last_name");

                String genderStr = results.getString("gender");
                Users.genderType gender = Users.genderType.valueOf(genderStr.trim());
                Users user = new Users(userId, userName, password, firstName, lastName, gender);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }
}

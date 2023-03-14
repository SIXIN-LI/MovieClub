package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import movie.model.Intentions;
import movie.model.Users;

/**
 * As an intentions table operator, I will need to access, register, update
 */
public class IntentionsDao {

    private static IntentionsDao instance = null;
    private ConnectionManager connectionManager;

    public IntentionsDao() {
        this.connectionManager = new ConnectionManager();
    }

    public static IntentionsDao getInstance() {
        if(instance == null) {
            instance = new IntentionsDao();
        }
        return instance;
    }


    // Users creates an intention, and then we will return a intention_id
    public Intentions createIntentions(Intentions intentions) throws SQLException {
        String insertIntention = "INSERT INTO Intentions(intention_id, user_id, moive_id, intention_timestamp, action) VALUES(?,?,?,?,?);";
        // try with automatic resource management will close these resources at the end
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertIntention,
                     Statement.RETURN_GENERATED_KEYS)) {

            // set parameters
            insertStmt.setInt(1, intentions.getIntentionId());
            insertStmt.setInt(2, intentions.getUser().getUserId());
            insertStmt.setString(3, intentions.getMovie().getMovieId());
            insertStmt.setTimestamp(4, intentions.getTimestamp());
            insertStmt.setString(5, intentions.getAction().toString());

            // load into database
            insertStmt.executeUpdate();

            ResultSet resultKey = insertStmt.getGeneratedKeys();
            int id = -1;
            if(resultKey.next()) {
                id = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            intentions.setIntentionId(id);

            return intentions;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Intentions getIntentionById(int intentionId) throws SQLException {
        String select = "SELECT user_id, moive_id, intention_timestamp, action FROM Intentions WHERE intention_id=?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(select)) {
            selectStmt.setInt(1, intentionId);
            ResultSet results = selectStmt.executeQuery();
            if (results.next()) {
                int userId = results.getInt("user_id");
                int moiveId = results.getInt("moive_id");
                String intentionTimestamp = results.getString("intention_timestamp");
                String action = results.getString("action");

                Users user = UsersDao.
                Intentions intentions = new Intentions(intentionId, userId, moiveId, intentionTimestamp, action);
                return intentions;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }
}
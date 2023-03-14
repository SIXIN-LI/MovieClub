package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import movie.model.Crew;

public class CrewDao {
	protected ConnectionManager connectionManager;

	private static CrewDao instance = null;

	protected CrewDao() {
		connectionManager = new ConnectionManager();
	}

	public static CrewDao getInstance() {
		if (instance == null) {
			instance = new CrewDao();
		}
		return instance;
	}

	public Crew getCrewByCrewId(String crewId) throws SQLException {
		String selectCrew = "SELECT * FROM Crew WHERE crew_id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCrew);
			selectStmt.setString(1, crewId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				String name = results.getString("name");
				String birthYear = results.getString("birth_year");
				String deathYear = results.getString("death_year");
				String primaryProfession = results.getString("primary_profession");
				Crew foundCrew = new Crew(crewId, name, birthYear, deathYear, primaryProfession);
				return foundCrew;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}
}

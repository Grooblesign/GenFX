package application.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionFactory {
	
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception exception) {
			System.out.println("Failed to load driver!");
		}

		return DriverManager.getConnection("jdbc:postgresql://localhost:5433/FamilyHistory", "postgres", "Tazzle11!");
	}

	public static void closeConnection(Connection connection) {
		if (connection == null)
			return;
		try {
			connection.close();
		} catch (SQLException exception) {
			// logger.info(exception.getMessage());
		}
	}

}

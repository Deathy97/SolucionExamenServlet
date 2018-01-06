package es.Rafa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.Rafa.connection.AbstractConnection;
import es.Rafa.model.Console;

public class ConsoleRepository {

	private AbstractConnection connection = new AbstractConnection() {

		@Override
		public String getDriver() {
			return "org.h2.Driver";
		}

		@Override
		public String getDatabaseUser() {
			return "sa";
		}

		@Override
		public String getDatabasePassword() {
			return "";
		}
	};

	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test;INIT=RUNSCRIPT FROM 'classpath:scripts/Console.sql'";

	public void insert(Console consoleForm) {
		Connection conn = connection.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO CONSOLE (name,codCompany)" + "VALUES (?, ?)");
			preparedStatement.setString(1, consoleForm.getName());
			preparedStatement.setInt(2, consoleForm.getCodCompany());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			connection.close(preparedStatement);
		}

		connection.close(conn);
	}

}

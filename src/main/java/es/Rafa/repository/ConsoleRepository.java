package es.Rafa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

	public Console search(Console consoleForm) {
		Console consoleInDatabase = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Connection conn = null;
		try {
			conn = connection.open(jdbcUrl);
			prepareStatement = conn.prepareStatement("SELECT * FROM CONSOLE WHERE name = ?");
			prepareStatement.setString(1, consoleForm.getName());
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				consoleInDatabase = new Console();
				consoleInDatabase.setName(resultSet.getString(0));
				consoleInDatabase.setCodCompany(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(prepareStatement);
		}
		close(conn);
		return consoleInDatabase;
	}

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
			close(preparedStatement);
			close(conn);
		}
	}

	public void update(Console console) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {
			conn = connection.open(jdbcUrl);
			preparedStatement = conn
					.prepareStatement("UPDATE CONSOLE SET " + "name = ?, codCompany = ? WHERE name = ?");
			preparedStatement.setString(1, console.getName());
			preparedStatement.setInt(2, console.getCodCompany());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			close(conn);
		}
	}

	public List<Console> searchAll() {
		List<Console> listGame = new ArrayList<Console>();
		Connection conn = connection.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM CONSOLE");
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Console consoleInDatabase = new Console();
				consoleInDatabase.setName(resultSet.getString(1));
				consoleInDatabase.setCodCompany(resultSet.getInt(2));
				listGame.add(consoleInDatabase);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(prepareStatement);
		}
		close(conn);
		return listGame;
	}

	public void delete(Console console) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {
			conn = connection.open(jdbcUrl);
			preparedStatement = conn.prepareStatement("DELETE * FROM CONSOLE  WHERE name = ?");
			preparedStatement.setString(1, console.getName());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			close(conn);
		}
	}

	public List<Console> selectByCompany(int id) {
		List<Console> listConsole = new ArrayList<Console>();
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Connection conn = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM CONSOLE WHERE companyId = ?");
			prepareStatement.setString(1, id + "");
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Console consoleInDatabase = new Console();
				consoleInDatabase.setName(resultSet.getString(1));
				consoleInDatabase.setCodCompany(resultSet.getInt(2));
				listConsole.add(consoleInDatabase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(prepareStatement);

		}
		close(conn);
		return listConsole;
	}

	private void close(PreparedStatement prepareStatement) {
		try {
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void close(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}

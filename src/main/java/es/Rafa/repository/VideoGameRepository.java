package es.Rafa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.Rafa.connection.AbstractConnection;
import es.Rafa.model.Console;
import es.Rafa.model.VideoGame;

public class VideoGameRepository {

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

	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test;INIT=RUNSCRIPT FROM 'classpath:scripts/VideoGame.sql'";

	public void insert(VideoGame gameForm) {
		Connection conn = connection.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO GAME (tittle,pegi,releaseDate)" + "VALUES (?, ?,?)");
			preparedStatement.setString(1, gameForm.getTittle());
			preparedStatement.setInt(2, gameForm.getPegi());
			preparedStatement.setDate(3, gameForm.getReleaseDate());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			connection.close(preparedStatement);
		}

		connection.close(conn);
	}
	
	public void update(VideoGame videoGame) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {
			conn = connection.open(jdbcUrl);
			preparedStatement = conn
					.prepareStatement("UPDATE GAME SET " + "tittle = ?, pegi = ?, releaseDate = ? WHERE tittle = ?");
			preparedStatement.setString(1, videoGame.getTittle());
			preparedStatement.setInt(2, videoGame.getPegi());
			preparedStatement.setDate(3, videoGame.getReleaseDate());
			preparedStatement.executeUpdate();

			// System.out.println("UPDATE CONSOLE SET " + "nombre = ?, apellido = ? WHERE
			// dni = ?");

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			connection.close(preparedStatement);
			connection.close(conn);
		}
	}
	
	public VideoGame search(VideoGame gameForm) {
		VideoGame videoGameInDatabase = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Connection conn = connection.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM GAME WHERE tittle = ?");
			prepareStatement.setString(1, gameForm.getTittle());
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				videoGameInDatabase = new VideoGame();
				videoGameInDatabase.setTittle(resultSet.getString(0));
				videoGameInDatabase.setPegi(resultSet.getInt(2));
				videoGameInDatabase.setReleaseDate(resultSet.getDate(3));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(prepareStatement);

		}
		connection.close(conn);
		return videoGameInDatabase;
	}
	
	private void close(PreparedStatement prepareStatement) {
		try {
			prepareStatement.close();
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

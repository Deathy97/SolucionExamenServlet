package es.Rafa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import es.Rafa.connection.AbstractConnection;
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
			preparedStatement = conn.prepareStatement("INSERT INTO GAME (title,pegi,releaseDate)" + "VALUES (?, ?, ?)");
			preparedStatement.setString(1, gameForm.getTitle());
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
					.prepareStatement("UPDATE GAME SET " + "title = ?, pegi = ?, releaseDate = ? WHERE title = ?");
			preparedStatement.setString(1, videoGame.getTitle());
			preparedStatement.setInt(2, videoGame.getPegi());
			preparedStatement.setDate(3, videoGame.getReleaseDate());
			preparedStatement.executeUpdate();

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
			prepareStatement = conn.prepareStatement("SELECT * FROM GAME WHERE title = ?");
			prepareStatement.setString(1, gameForm.getTitle());
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				videoGameInDatabase = new VideoGame();
				videoGameInDatabase.setTitle(resultSet.getString(0));
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

	public List<VideoGame> searchAll() {
		List<VideoGame> listGames = new ArrayList<VideoGame>();
		Connection conn = connection.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM GAME");
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				VideoGame videoGameInDatabase = new VideoGame();
				videoGameInDatabase.setTitle(resultSet.getString(1));
				videoGameInDatabase.setPegi(resultSet.getInt(2));
				videoGameInDatabase.setReleaseDate(resultSet.getDate(3));

				listGames.add(videoGameInDatabase);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(prepareStatement);
		}

		connection.close(conn);
		return listGames;
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

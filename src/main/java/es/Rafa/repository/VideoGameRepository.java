package es.Rafa.repository;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import es.Rafa.model.VideoGame;

@Repository
public class VideoGameRepository {

	private static final String SELECT = "SELECT * FROM GAME";
	private static final String INSERT = "INSERT INTO GAME (title,recommendedAge,releaseDate,consoleName) VALUES (:title,:recommendedAge,:releaseDate, :consoleName)";
	private static final String DELETE = "DELETE * FROM GAME WHERE title = :title";
	private static final String SELECTBYTITLE = SELECT + " WHERE title = :title";
	private static final String SELECTBYCONSOLE = SELECT + " WHERE consoleName = :consoleName";
	private static final String SELECTBYRECOMMENDEDAGE = SELECT + " WHERE recommendedAge = :recommendedAge";

	private static Logger log = LogManager.getLogger(VideoGameRepository.class);

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public List<VideoGame> search(VideoGame videogame) {
		log.debug("Ejecutando la consulta: " + SELECTBYTITLE);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("title", videogame.getTitle());
		namedJdbcTemplate.update(SELECTBYTITLE, params);
		List<VideoGame> listVideogame = template.query(SELECTBYTITLE, new BeanPropertyRowMapper(VideoGame.class));
		return listVideogame;
	}

	public List<VideoGame> searchAll() {
		log.debug("Ejecutando la consulta: " + SELECT);
		List<VideoGame> listVideogame = template.query(SELECT, new BeanPropertyRowMapper(Videogame.class));
		return listVideogame;
	}

	public List<VideoGame> searchByConsole(String consoleName) {
		log.debug("Ejecutando la consulta: " + SELECTBYCONSOLE);
		List<VideoGame> listVideogame = namedJdbcTemplate.query(SELECTBYCONSOLE,
				new MapSqlParameterSource("consoleName", consoleName), (resultSet, i) -> {
					return toVideogame(resultSet);
				});
		return listVideogame;
	}

	public List<VideoGame> searchByRecommendedAge(String recommendedAge) {
		log.debug("Ejecutando la consulta: " + SELECTBYRECOMMENDEDAGE);
		List<VideoGame> listVideogame = namedJdbcTemplate.query(SELECTBYRECOMMENDEDAGE,
				new MapSqlParameterSource("recommendedAge", recommendedAge), (resultSet, i) -> {
					return toVideogame(resultSet);
				});
		return listVideogame;
	}

	private VideoGame toVideogame(ResultSet resultSet) throws SQLException {
		VideoGame videogameInDatabase = new VideoGame();
		videogameInDatabase.setTitle((resultSet.getString("title")));
		videogameInDatabase.setPegi(resultSet.getInt("recommendedAge"));
		videogameInDatabase.setReleaseDate((resultSet.getDate("releaseDate")));
		videogameInDatabase.setCompanyId(resultSet.getInt("consoleName"));
		return videogameInDatabase;
	}

	public String selectOrder(String order) {
		if (order.equals("orderByTitle")) {
			return " ORDER BY title ASC";
		} else {
			return " ORDER BY releaseDate ASC";
		}
	}

	public List<VideoGame> orderBy(String recommendedAge, String order) {
		log.debug("Ejecutando la consulta: " + SELECTBYRECOMMENDEDAGE + selectOrder(order));
		List<VideoGame> listVideogame = namedJdbcTemplate.query(SELECTBYRECOMMENDEDAGE + selectOrder(order),
				new MapSqlParameterSource("recommendedAge", recommendedAge), (resultSet, i) -> {
					return toVideogame(resultSet);
				});
		return listVideogame;
	}

	public void insert(VideoGame videogame) {
		log.debug("Ejecutando la consulta: " + INSERT);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("title", videogame.getTitle());
		params.addValue("recommendedAge", videogame.getPegi());
		params.addValue("releaseDate", videogame.getReleaseDate());
		params.addValue("consoleName", videogame.getCompanyId());
		namedJdbcTemplate.update(INSERT, params);
	}

	public void delete(VideoGame videogame) {
		log.debug("Ejecutando la consulta: " + DELETE);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("title", videogame.getTitle());
		namedJdbcTemplate.update(DELETE, params);
	}

}
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

import es.Rafa.model.Company;
import es.Rafa.model.Console;

@Repository
public class ConsoleRepository {

	private static final String SELECT = "SELECT * FROM CONSOLE";
	private static final String INSERT = "INSERT INTO CONSOLE (name,creationDate) VALUES (:name, :creationDate)";
	private static final String DELETE = "DELETE * FROM CONSOLE WHERE name = :name";
	private static final String SELECTBYNAME = "SELECT * FROM CONSOLE WHERE name = :name";
	private static final String SELECTBYCOMPANY = "SELECT * FROM CONSOLE WHERE codCompany = :codCompany";

	private static Logger log = LogManager.getLogger(ConsoleRepository.class);

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public List<Console> search(Console console) {
		log.debug("Ejecutando la consulta: " + SELECTBYNAME);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", console.getName());
		namedJdbcTemplate.update(SELECTBYNAME, params);
		List<Console> listConsole = template.query(SELECTBYNAME, new BeanPropertyRowMapper<Console>(Console.class));
		return listConsole;
	}

	public List<Console> searchAll() {
		log.debug("Ejecutando la consulta: " + SELECT);
		List<Console> listConsole = template.query(SELECT, new BeanPropertyRowMapper(Console.class));
		return listConsole;
	}

	public List<Console> searchByCompany(String companyName) {
		log.debug("Ejecutando la consulta: " + SELECTBYCOMPANY);
		List<Console> listConsole = namedJdbcTemplate.query(SELECTBYCOMPANY,
				new MapSqlParameterSource("companyName", companyName), (resultSet, i) -> {
					return toConsole(resultSet);
				});
		return listConsole;
	}

	private Console toConsole(ResultSet resultSet) throws SQLException {
		Console console = new Console();
		console.setName((resultSet.getString("name")));
		console.setCodCompany(resultSet.getInt("companyName"));
		return console;
	}

	public void insert(Console console) {
		log.debug("Ejecutando la consulta: " + INSERT);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", console.getName());
		params.addValue("companyName", console.getCodCompany());
		namedJdbcTemplate.update(INSERT, params);
	}

	public void delete(Console console) {
		log.debug("Ejecutando la consulta: " + DELETE);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", console.getName());
		namedJdbcTemplate.update(DELETE, params);
	}

}
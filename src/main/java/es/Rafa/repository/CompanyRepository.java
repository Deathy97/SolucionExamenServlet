package es.Rafa.repository;

import java.util.List;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import es.Rafa.model.Company;

public class CompanyRepository {

	private static final String SELECT = "SELECT * FROM COMPANY";
	private static final String INSERT = "INSERT INTO COMPANY (name,creationDate) VALUES (:name, :creationDate)";
	private static final String DELETE = "DELETE * FROM COMPANY WHERE name = :name";
	private static final String SELECTBYNAME = "SELECT * FROM CONSOLE WHERE name = :name";

	private static Logger log = LogManager.getLogger(CompanyRepository.class);

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public List<Company> search(Company company) {
		log.debug("Consulta ejecutada" + SELECTBYNAME);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", company.getName());
		namedJdbcTemplate.update(SELECTBYNAME, params);
		List<Company> listCompany = template.query(SELECTBYNAME, new BeanPropertyRowMapper(Company.class));
		return listCompany;
	}

	public List<Company> searchAll() {
		log.debug("Ejecutando la consulta: " + SELECT);
		List<Company> listCompany = template.query(SELECT, new BeanPropertyRowMapper(Company.class));
		return listCompany;
	}

	public void insert(Company company) {
		log.debug("Ejecutando la consulta: " + INSERT);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", company.getName());
		params.addValue("creationDate", company.getCreationDate());
		namedJdbcTemplate.update(INSERT, params);
	}

	public void delete(Company company) {
		log.debug("Ejecutando la consulta: " + DELETE);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", company.getName());
		namedJdbcTemplate.update(DELETE, params);
	}

}

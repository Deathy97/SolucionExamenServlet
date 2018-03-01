package es.Rafa.connection;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class H2Connection {

	@Bean
	public DriverManagerDataSource h2DataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:file:./src/main/resources/test;INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}
}

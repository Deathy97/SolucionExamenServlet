package es.Rafa.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.Rafa.model.Console;
import es.Rafa.repository.ConsoleRepository;

@Service
public class ConsoleService {

	private static Logger log = LogManager.getLogger(ConsoleService.class);

	@Autowired
	private ConsoleRepository repository;

	public void insert(Console console) {
		log.debug("Insertando la consola " + console.getName());
		repository.insert(console);
	}

	public void delete(Console console) {
		log.debug("Borrando la consola " + console.getName());
		repository.delete(console);
	}

	public List<Console> listAll() {
		log.debug("Listando todas las consolas...");
		return repository.searchAll();
	}

	public List<Console> listByCompany(String companyName) {
		log.debug("Listando todas las consolas de la empresa " + companyName);
		return repository.searchByCompany(companyName);
	}

}

package es.Rafa.service;

import java.util.List;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.Rafa.model.Company;
import es.Rafa.repository.CompanyRepository;

@Service
public class CompanyService {

	private static Logger log = LogManager.getLogger(CompanyService.class);

	@Autowired
	private CompanyRepository repository;

	public void insert(Company company) {
		log.debug("Insertando la empresa " + company.getName());
		repository.insert(company);
	}

	public void delete(Company company) {
		log.debug("Borrando la empresa " + company.getName());
		repository.delete(company);
		;
	}

	public List<Company> listAll() {
		log.debug("Listando todas las empresas...");
		return repository.searchAll();
	}

}

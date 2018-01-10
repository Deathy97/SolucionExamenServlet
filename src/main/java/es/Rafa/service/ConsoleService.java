package es.Rafa.service;

import javax.servlet.http.HttpServletRequest;

import es.Rafa.assembler.ConsoleAssembler;
import es.Rafa.model.Console;
import es.Rafa.repository.ConsoleRepository;

public class ConsoleService {

	private ConsoleRepository repository = new ConsoleRepository();

	public void createNewConsoleFromRequest(HttpServletRequest req) {
		Console console = ConsoleAssembler.assembleConsoleFrom(req);
		insertOrUpdate(console);
	}

	public void insertOrUpdate(Console consoleFrom) {
		Console consoleInDatabase = repository.search(consoleFrom);
		if (null == consoleInDatabase) {
			repository.insert(consoleFrom);
		} else {
			repository.update(consoleFrom);
		}
	}

	public ConsoleRepository getRepository() {
		return repository;
	}

	public void setRepository(ConsoleRepository repository) {
		this.repository = repository;
	}
}

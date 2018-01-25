package es.Rafa.service;

import java.util.List;

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

	public List<Console> listAllConsoles(){
		return repository.searchAll();
		
	}
	
	public Console deleteConsole(HttpServletRequest req) {
		repository.delete(req);
	}
	
	public ConsoleRepository getRepository() {
		return repository;
	}

	public void setRepository(ConsoleRepository repository) {
		this.repository = repository;
	}
}

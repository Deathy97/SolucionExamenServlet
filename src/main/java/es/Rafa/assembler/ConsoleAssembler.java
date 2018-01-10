package es.Rafa.assembler;

import javax.servlet.http.HttpServletRequest;

import es.Rafa.model.Console;

public class ConsoleAssembler {

	
	public static Console assembleConsoleFrom(HttpServletRequest request) {
		
		Console console = new Console();
		
		String cod = request.getParameter("codCompany");
		Integer x = Integer.parseInt(cod);
		
		console.setName(request.getParameter("name")); 
		console.setCodCompany(x);
		return console;
	}
}

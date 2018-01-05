package es.Rafa.assembler;

import javax.servlet.http.HttpServletRequest;

import es.Rafa.model.Console;

public class ConsoleAssembler {

	
	public static Console assembleUserFrom(HttpServletRequest request) {
		
		Console console = new Console();
		console.setName(request.getParameter("name")); 
		console.setCodCompany(Integer.parseInt(request.getParameter("codCompany")));
		return console;
	}
}

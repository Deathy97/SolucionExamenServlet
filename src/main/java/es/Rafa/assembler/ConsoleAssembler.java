package es.Rafa.assembler;

import javax.servlet.http.HttpServletRequest;
import es.Rafa.model.Console;

public class ConsoleAssembler {

	public static Console assembleConsoleFrom(HttpServletRequest request) {
		Console console = new Console();
		int cod = Integer.parseInt(request.getParameter("codCompany"));
		console.setName(request.getParameter("name"));
		console.setCodCompany(cod);
		return console;
	}
}

package es.Rafa.servlet;

public class ConsoleServlet {

private ConsoleService service = new ConsoleService();
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Console console = service.assembleUserFromRequest(req);
		service.insertOrUpdate(user);
		service.calculateAgeAndAddIntoRequest(req, user.getDateOfBirth());
		redirect(req,resp);
	}

	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/fin.jsp");
		dispatcher.forward(req,resp);
	}

	public ConsoleService getService() {
		return service;
	}

	public void setService(ConsoleService service) {
		this.service = service;
}
}

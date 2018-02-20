package es.Rafa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.Rafa.model.Company;
import es.Rafa.model.Console;
import es.Rafa.service.CompanyService;

public class ListConsoleByCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CompanyService service = new CompanyService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("selectCompany"));
		List<Console> listConsoleByCompany = service.listAllByCompany(id);
		req.setAttribute("listAllConsoleByCompany", listConsoleByCompany);
		redirect(req, resp);
	}

	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ListConsoleByCompany.jsp");
		dispatcher.forward(req, resp);
	}
}

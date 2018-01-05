package es.Rafa.assembler;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import es.Rafa.model.Company;

public class CompanyAssembler {

	public static Company assembleUserFrom(HttpServletRequest request) {

		Company company = new Company();
		company.setName(request.getParameter("name"));
		company.setCreationDate(Date.valueOf(request.getParameter("creationDate")));
		return company;
	}
}

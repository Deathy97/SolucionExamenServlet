package es.Rafa.controller;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import es.Rafa.model.Company;
import es.Rafa.model.Console;
import es.Rafa.service.CompanyService;

@Controller
public class CompanyController {

	private static Logger log = LogManager.getLogger(CompanyController.class);

	@Autowired
	private CompanyService service;

	@GetMapping("/RegisterCompany")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("RegisterCompany", "command", new Company());
		return modelAndView;
	}

	@PostMapping("registercompany")
	public ModelAndView companyInsert(@ModelAttribute Company company) {
		log.debug("Insertando la empresa:" + company.getName());
		service.insert(company);
		ModelAndView modelAndView = new ModelAndView("RegisterCompany", "command", new Company());
		return modelAndView;
	}

	@GetMapping("ListCompanies")
	public ModelAndView listCompanies() {
		log.debug("Listando las empresas...");
		ModelAndView modelAndView = new ModelAndView("ListCompanies", "command", new Company());
		modelAndView.addObject("listAllCompanies", service.listAll());
		return modelAndView;
	}

	@GetMapping("/RegisterConsole")
	public ModelAndView registerConsole() {
		ModelAndView modelAndView = new ModelAndView("RegisterConsole", "command", new Console());
		modelAndView.addObject("listAllCompanies", service.listAll());
		return modelAndView;
	}

	@GetMapping("/ListConsolesCompanies")
	public ModelAndView listConsolesCompanies() {
		log.debug("Listando todas las empresas");
		ModelAndView modelAndView = new ModelAndView("ListConsolesCompanies", "command", new Console());
		modelAndView.addObject("listAllCompanies", service.listAll());
		return modelAndView;
	}

}
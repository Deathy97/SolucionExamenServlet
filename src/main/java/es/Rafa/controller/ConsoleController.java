package es.Rafa.controller;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import es.Rafa.model.Console;
import es.Rafa.model.VideoGame;
import es.Rafa.service.ConsoleService;

@Controller
public class ConsoleController {

	private static Logger log = LogManager.getLogger(ConsoleController.class);

	@Autowired
	private ConsoleService service;

	@PostMapping("registerconsole")
	public ModelAndView consoleInsert(@ModelAttribute Console console) {
		log.debug("Insertando la consola:" + console.getName());
		service.insert(console);
		ModelAndView modelAndView = new ModelAndView("RegisterConsole", "command", new Console());
		return modelAndView;
	}

	@GetMapping("/ListConsoles")
	public ModelAndView listConsoles() {
		log.debug("Listando todas las consolas");
		ModelAndView modelAndView = new ModelAndView("ListConsoles", "command", new Console());
		modelAndView.addObject("listAllConsoles", service.listAll());
		return modelAndView;
	}

	@GetMapping("/RegisterVideogame")
	public ModelAndView registerVideogame() {
		log.debug("Listando todas las consolas");
		ModelAndView modelAndView = new ModelAndView("RegisterVideogame", "command", new VideoGame());
		modelAndView.addObject("listAllConsoles", service.listAll());
		return modelAndView;
	}

	@GetMapping("/ListVideogamesConsoles")
	public ModelAndView listVideogamesConsoles() {
		log.debug("Listando las consolas...");
		ModelAndView modelAndView = new ModelAndView("ListVideogamesConsoles", "command", new VideoGame());
		modelAndView.addObject("listAllConsoles", service.listAll());
		return modelAndView;
	}

	@PostMapping("/listConsolesByCompany")
	public ModelAndView listConsolesByCompanies(@ModelAttribute("companyName") String companyName) {
		log.debug("Listando las consolas...");
		ModelAndView modelAndView = new ModelAndView("ListConsolesCompanies", "command", new Console());
		modelAndView.addObject("listAllConsoles", service.listByCompany(companyName));
		return modelAndView;
	}

}
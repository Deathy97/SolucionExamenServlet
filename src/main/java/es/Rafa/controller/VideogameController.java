package es.Rafa.controller;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import es.Rafa.model.VideoGame;
import es.Rafa.service.VideoGameService;

@Controller
public class VideogameController {

	private static Logger log = LogManager.getLogger(VideogameController.class);

	@Autowired
	private VideoGameService service;

	@PostMapping("registervideogame")
	public ModelAndView videogameInsert(@ModelAttribute VideoGame videogame) {
		log.debug("Insertando el videojuego:" + videogame.getTitle());
		service.insert(videogame);
		ModelAndView modelAndView = new ModelAndView("RegisterVideogame", "command", new VideoGame());
		return modelAndView;
	}

	@GetMapping("/ListVideogames")
	public ModelAndView listVideogames() {
		log.debug("Listando todos los videojuegos");
		ModelAndView modelAndView = new ModelAndView("ListVideogames", "command", new VideoGame());
		modelAndView.addObject("listAllVideogames", service.listAll());
		return modelAndView;
	}

	@PostMapping("listVideogamesByConsole")
	public ModelAndView listVideogamesConsoles(@ModelAttribute("consoleName") String consoleName) {
		log.debug("Listando los videojuegos de la consola " + consoleName);
		ModelAndView modelAndView = new ModelAndView("ListVideogamesConsoles", "command", new VideoGame());
		modelAndView.addObject("listAllVideogames", service.listByConsole(consoleName));
		return modelAndView;
	}

	@GetMapping("/ListRecommendedAge")
	public ModelAndView recommendedAge() {
		log.debug("Cargando pagina");
		ModelAndView modelAndView = new ModelAndView("ListRecommendedAge");
		modelAndView.addObject("recommendedAge");
		return modelAndView;
	}

	@PostMapping("listByRecommendedAge")
	public ModelAndView listRecommendedAge(@ModelAttribute("recommendedAge") String recommendedAge) {
		log.debug("Preparando listado segun la edad " + recommendedAge);
		ModelAndView modelAndView = new ModelAndView("ListRecommendedAge");
		modelAndView.addObject("listAllVideogames", service.listByRecommendedAge(recommendedAge));
		modelAndView.addObject("recommendedAge", recommendedAge);
		modelAndView.addObject("order");
		return modelAndView;
	}

	@PostMapping("orderVideogames")
	public ModelAndView order(@ModelAttribute("consoleName") String recommendedAge,
			@ModelAttribute("order") String order) {
		log.debug("Ordenando videojuegos");
		ModelAndView modelAndView = new ModelAndView("ListRecommendedAge", "command", new VideoGame());
		modelAndView.addObject("listAllVideogames", service.order(recommendedAge, order));
		return modelAndView;
	}

}
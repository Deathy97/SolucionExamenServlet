package es.Rafa.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.Rafa.model.VideoGame;
import es.Rafa.repository.VideoGameRepository;

@Service
public class VideoGameService {

	private static Logger log = LogManager.getLogger(VideoGameService.class);

	@Autowired
	private VideoGameRepository repository;

	public void insert(VideoGame videogame) {
		log.debug("Insertando el videojuego " + videogame.getTitle());
		repository.insert(videogame);
	}

	public void delete(VideoGame videogame) {
		log.debug("Borrando el videojuego " + videogame.getTitle());
		repository.delete(videogame);
	}

	public List<VideoGame> listAll() {
		log.debug("Listando todos los videojuegos...");
		return repository.searchAll();
	}

	public List<VideoGame> listByRecommendedAge(String recommendedAge) {
		log.debug("Listando videojuegos de edad recomendada " + recommendedAge);
		return repository.searchByRecommendedAge(recommendedAge);
	}

	public List<VideoGame> listByConsole(String consoleName) {
		log.debug("Listando videojuegos para la consola " + consoleName);
		return repository.searchByConsole(consoleName);
	}

	public List<VideoGame> order(String recommendedAge, String order) {
		log.debug("Ordenando videojuegos de edad recomendada " + recommendedAge);
		return repository.orderBy(recommendedAge, order);
	}

}
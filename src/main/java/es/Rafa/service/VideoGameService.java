package es.Rafa.service;

import javax.servlet.http.HttpServletRequest;

import es.Rafa.assembler.VideoGameAssembler;
import es.Rafa.model.VideoGame;
import es.Rafa.repository.VideoGameRepository;

public class VideoGameService {

	private VideoGameRepository repository = new VideoGameRepository();

	public void createNewVideoGameFromRequest(HttpServletRequest req) {
		VideoGame videoGame = VideoGameAssembler.assembleVideoGameForm(req);
		insertOrUpdate(videoGame);
	}

	public void insertOrUpdate(VideoGame videoGameForm) {
		VideoGame videoGameInDatabase = repository.search(videoGameForm);
		if (null == videoGameInDatabase) {
			repository.insert(videoGameForm);
		} else {
			repository.update(videoGameForm);
		}
	}

	public VideoGameRepository getRepository() {
		return repository;
	}

	public void setRepository(VideoGameRepository repository) {
		this.repository = repository;
	}
}


package es.Rafa.assembler;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import es.Rafa.model.VideoGame;

public class VideoGameAssembler {

	public static VideoGame assembleVideoGameForm(HttpServletRequest request) {

		VideoGame videoGame = new VideoGame();
		videoGame.setTitle(request.getParameter("title"));
		videoGame.setPegi(Integer.parseInt(request.getParameter("pegi")));
		videoGame.setReleaseDate(Date.valueOf(request.getParameter("releaseDate")));
		return videoGame;
	}
}

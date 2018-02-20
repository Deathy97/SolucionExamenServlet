package es.Rafa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.Rafa.model.VideoGame;
import es.Rafa.service.VideoGameService;

public class VideoGameDataListServlet extends HttpServlet {

	private VideoGameService service = new VideoGameService();
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<VideoGame> listAllVideoGames = service.listAllVideoGames();
		req.setAttribute("listAllVideoGames", listAllVideoGames);
		redirect(req, resp);
	}

	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/VideoGameList.jsp");
		dispatcher.forward(req, resp);
	}

}

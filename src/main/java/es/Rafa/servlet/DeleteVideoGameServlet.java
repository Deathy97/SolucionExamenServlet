package es.Rafa.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import es.Rafa.assembler.VideoGameAssembler;
import es.Rafa.model.VideoGame;
import es.Rafa.service.VideoGameService;

public class DeleteVideoGameServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private VideoGameService service = new VideoGameService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VideoGame videoGameToDelete = VideoGameAssembler.assembleVideoGameForm(req);
		service.deleteVideoGame(videoGameToDelete);
		listRedirect(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("title", req.getParameter("title"));
		confirmationRedirect(req, resp);
	}

	private void listRedirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/VideoGameList.jsp");
		dispatcher.forward(req, resp);
	}

	private void confirmationRedirect(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Confirmation.jsp");
		dispatcher.forward(req, resp);
	}
}

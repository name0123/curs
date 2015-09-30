package edu.upc.escert.curs;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.upc.escert.curs.repositori.*;

@WebServlet("/comentaris")
@SuppressWarnings("serial")
public class LlistaComentarisServlet extends HttpServlet {
	private IRepositoriComentaris repositoriComentaris=RepositoriFactory.getRepositoriComentaris();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Comentari> comentaris=null;
		String autor=request.getParameter("autor");
		if (autor!=null) {
			comentaris=repositoriComentaris.getComentarisPerAutor(autor);
		} else {
			comentaris=repositoriComentaris.getComentaris();
		}
		request.setAttribute("username",request.getRemoteUser());
		request.setAttribute("comentaris",comentaris);
		request.getRequestDispatcher("/comentaris.jsp").forward(request,response);
	}
}

package edu.upc.escert.curs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.upc.escert.curs.repositori.IRepositoriComentaris;
import edu.upc.escert.curs.repositori.RepositoriFactory;

@WebServlet("/esborrar")
public class EsborrarComentariServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IRepositoriComentaris repositoriComentaris=RepositoriFactory.getRepositoriComentaris();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=(String)(request.getSession().getAttribute("username"));
		int id=Integer.parseInt(request.getParameter("id"));
		Comentari c=repositoriComentaris.getComentariPerId(id);
		if (c==null || (!c.getAutor().equals(username))) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		repositoriComentaris.esborrarComentari(id);
		response.sendRedirect("comentaris");
	}

}

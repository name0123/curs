package edu.upc.escert.curs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.upc.escert.curs.repositori.RepositoriFactory;
import edu.upc.escert.curs.repositori.IRepositoriComentaris;

@WebServlet("/comentar")
public class ComentarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IRepositoriComentaris repositoriComentaris=RepositoriFactory.getRepositoriComentaris();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("username")==null) {
			response.sendRedirect("login");
			return;
		}
		request.setAttribute("username",request.getSession().getAttribute("username"));
		request.getRequestDispatcher("/comentar.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String comentari=request.getParameter("comentari");
		String autor=request.getParameter("autor");
		String titol=request.getParameter("titol");
		repositoriComentaris.afegirComentari(new Comentari(autor,titol,comentari));
		response.sendRedirect("comentaris");
	}

}

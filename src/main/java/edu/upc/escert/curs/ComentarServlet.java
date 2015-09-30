package edu.upc.escert.curs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import edu.upc.escert.curs.repositori.RepositoriFactory;
import edu.upc.escert.curs.repositori.IRepositoriComentaris;


@WebServlet("/comentar")
@SuppressWarnings("serial")
public class ComentarServlet extends HttpServlet {

	private IRepositoriComentaris repositoriComentaris=RepositoriFactory.getRepositoriComentaris();
	public static PolicyFactory policyHTML = Sanitizers.FORMATTING.and(Sanitizers.LINKS);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("username")==null) {
			response.sendRedirect("login");
			return;
		}
		request.setAttribute("username",request.getSession().getAttribute("username"));
		request.getRequestDispatcher("/comentar.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String comentari=policyHTML.sanitize(request.getParameter("comentari"));
		String autor=request.getParameter("autor");
		String titol=request.getParameter("titol");
		repositoriComentaris.afegirComentari(new Comentari(autor,titol,comentari));
		response.sendRedirect("comentaris");
	}

}

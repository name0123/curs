package edu.upc.escert.curs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableInterceptor.PolicyFactory;

import edu.upc.escert.curs.repositori.IRepositoriComentaris;
import edu.upc.escert.curs.repositori.RepositoriFactory;

@WebServlet("/comentar")
public class ComentarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IRepositoriComentaris repositoriComentaris=RepositoriFactory.getRepositoriComentaris();	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("username",request.getRemoteUser());
		request.getRequestDispatcher("/comentar.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String comentari=(String) request.getAttribute("comentari");
		String autor=request.getRemoteUser();
		String titol=request.getParameter("titol");
		repositoriComentaris.afegirComentari(new Comentari(autor,titol,comentari));
		response.sendRedirect("comentaris");
	}

}

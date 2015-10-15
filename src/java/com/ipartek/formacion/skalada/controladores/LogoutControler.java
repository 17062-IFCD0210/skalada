package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutControler
 * 
 * @author Curso
 */
public class LogoutControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * private Cookie cookieLastVisit = null; private final static String COOKIE_NAME = "ultima_visita";
	 */

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutControler() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Se guarda la cookie cuando te logeas
		/*
		 * long milisegundos = System.currentTimeMillis(); SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm"); Date resulDate = new
		 * Date(milisegundos);
		 * 
		 * Cookie cookie = new Cookie(COOKIE_NAME, sdf.format(resulDate)); response.addCookie(cookie);
		 */

		request.getSession().invalidate();
		request.getRequestDispatcher("home").forward(request, response);
	}

}

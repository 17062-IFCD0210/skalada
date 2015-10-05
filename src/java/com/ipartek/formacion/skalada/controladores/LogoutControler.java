package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutControler
 * 
 * @author ander
 */
public class LogoutControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutControler() {
		super();
	}

	/**
	 * @param request
	 *            a
	 * @param response
	 *            a
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @throws ServletException
	 *             a
	 * @throws IOException
	 *             a
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @param request
	 *            a
	 * @param response
	 *            a
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @throws ServletException
	 *             a
	 * @throws IOException
	 *             a
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//

		request.getSession().invalidate();
		request.getRequestDispatcher("home").forward(request, response);

	}

}

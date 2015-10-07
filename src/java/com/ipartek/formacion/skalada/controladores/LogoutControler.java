package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutControler.
 */
public class LogoutControler extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutControler() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request,
	 *  HttpServletResponse response)
	 */
	@Override
	protected final void doGet(final HttpServletRequest request,
					final HttpServletResponse response) 
					throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 *  HttpServletResponse response)
	 */
	@Override
	protected final void doPost(final HttpServletRequest request,
					final HttpServletResponse response) 
					throws ServletException, IOException {
				
		request.getSession().invalidate();
		request.getRequestDispatcher("home").forward(request, response);
		
	}

}

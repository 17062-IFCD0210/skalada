package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.Constantes;

/**
 * Servlet implementation class Controller.
 */
public class Controller extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
       /**
        * 
        */
	private RequestDispatcher dispatcher = null;

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
		
		String path = request.getRequestURL().toString();
		System.out.println(path);
				
		this.dispatcher = request.getRequestDispatcher(
				Constantes.VIEW_BACK_LOGIN);
		
		this.dispatcher.forward(request, response);
	}

}

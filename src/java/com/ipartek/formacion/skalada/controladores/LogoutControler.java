package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
	
	private Cookie cookieLastVisit = null;
	private final static String COOKIE_NAME= "last_visit"; 

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long milliseconds = System.currentTimeMillis();
    	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy - HH:mm");
    	Date date = new Date(milliseconds);
    	this.cookieLastVisit = new Cookie(COOKIE_NAME, sdf.format(date));
		
    	response.addCookie(cookieLastVisit);
		
		request.getSession().invalidate();
		request.getRequestDispatcher("home").forward(request, response);

	}

}

package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.skalada.bean.Usuario;

/**
 * Servlet implementation class LogoutControler
 * @author Curso
 */
public class LogoutControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Logs
	private final static Logger LOG = Logger.getLogger(LogoutControler.class);
	
	public static final String KEY_SESSION_USER = "ss_user";
    
	private HttpSession session = null;
	
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);			
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		Usuario usuario = (Usuario)session.getAttribute(KEY_SESSION_USER);
		
		LOG.info("Usuario: " + usuario.getNombre() + "[id:" + usuario.getId() + "] Cerrar sesion.");
		
		request.getSession().invalidate();
		request.getRequestDispatcher("home").forward(request, response);
		
	}

}

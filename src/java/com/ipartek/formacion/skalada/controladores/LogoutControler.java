package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class LogoutControler
 * 
 * @author Javi
 */
public class LogoutControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(LoginController.class);

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			// Fichero configuracion de Log4j
			Properties props = new Properties();
			props.load(this.getClass().getResourceAsStream("/log4j.properties"));
			PropertyConfigurator.configure(props);

		} catch (IOException e) {
			e.printStackTrace();
		}
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

		// System.out.println("Logout.... ");
		LOG.info("Saliendo del bakoffice");

		request.getSession().invalidate();
		request.getRequestDispatcher("home").forward(request, response);

	}

}

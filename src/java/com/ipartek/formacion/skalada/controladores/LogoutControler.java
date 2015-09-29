package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Usuario;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;

/**
 * Servlet implementation class LogoutControler
 */
public class LogoutControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Logs
	private final static Logger log = Logger.getLogger(LogoutControler.class);
	
	public static final String KEY_SESSION_USER = "ss_user";
    
	private RequestDispatcher dispatcher = null;
	private HttpSession session = null;
	
	private ModeloUsuario modeloUsuario = null;
	private Usuario usuario = null;
	
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		modeloUsuario = new ModeloUsuario();
		try {
			//Fichero de configuracion de Log4jv 
			Properties props = new Properties();
			props.load( getClass().getResourceAsStream("/log4j.properties"));
			PropertyConfigurator.configure(props);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		Usuario usuario = (Usuario)session.getAttribute(KEY_SESSION_USER);
		
		log.info("Usuario: " + usuario.getNombre() + "[id:" + usuario.getId() + "] Cerrar sesion.");
		
		request.getSession().invalidate();
		request.getRequestDispatcher("home").forward(request, response);
		
	}

}

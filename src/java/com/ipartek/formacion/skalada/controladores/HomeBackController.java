package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.modelo.ModeloSector;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;

/**
 * Servlet implementation class HomeBackController
 * @author Curso
 */
public class HomeBackController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	//Key oara guardar el usuario en la session
	public static final String KEY_SESSION_USER = "ss_user";
       
	private RequestDispatcher dispatcher = null;
	private ModeloUsuario modeloUsuario = null;
	
	private ModeloSector modeloSector = null;
	
    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);	
		modeloUsuario = new ModeloUsuario();
		modeloSector = new ModeloSector();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		Usuario sessionUser = (Usuario)request.getSession().getAttribute(KEY_SESSION_USER);
		
		request.setAttribute("num_user_invalid", modeloUsuario.getUserInvalid());
		
		request.setAttribute("num_sectores", modeloSector.getSectorCant());
		
		dispatcher = request.getRequestDispatcher("pages/index_back.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}

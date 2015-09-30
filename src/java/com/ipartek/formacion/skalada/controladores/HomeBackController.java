package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Usuario;
import com.ipartek.formacion.skalada.modelo.ModeloSector;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;

/**
 * Servlet implementation class HomeBackController
 */
public class HomeBackController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	//Key oara guardar el usuario en la session
	public static final String KEY_SESSION_USER = "ss_user";
       
	private RequestDispatcher dispatcher = null;
	private HttpSession session = null;
	
	private ModeloUsuario modeloUsuario = null;
	private Usuario usuario = null;
	
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
		
		Usuario sessionUser = (Usuario)request.getSession().getAttribute(KEY_SESSION_USER);
		
		ArrayList<Object> usuarios = modeloUsuario.getAll();
		int num_user_not_valid = 0;
		for( int i = 0 ; i < usuarios.size() ; i++ ){
			usuario = (Usuario) usuarios.get(i);
			if(usuario.getValidado() == Constantes.USER_NO_VALIDATE){
				num_user_not_valid++;
			}
		}
		request.setAttribute("num_user_invalid", num_user_not_valid);
		
		ArrayList<Object> sectores = modeloSector.getAll();
		request.setAttribute("num_sectores", sectores.size());
		
		dispatcher = request.getRequestDispatcher("pages/index_back.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

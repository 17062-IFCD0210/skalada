package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.modelo.ModeloSector;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;

/**
 * Servlet implementation class BackIndexController
 */
public class BackIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;
	
	
	private ModeloSector modeloSector=null;
	private ModeloUsuario modeloUsuario=null;
       
	public void init(ServletConfig config) throws ServletException {
		modeloSector = new ModeloSector();
		modeloUsuario = new ModeloUsuario();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("sectoresPublicados", Integer.toString(modeloSector.sectoresPublicados()));
		request.setAttribute("usuariosSinValidar", Integer.toString(modeloUsuario.usuariosSinValidar()));
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_INDEX);
		dispatcher.forward(request, response);	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
}

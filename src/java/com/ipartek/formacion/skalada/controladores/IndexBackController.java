package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.modelo.ModeloSector;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;
import com.ipartek.formacion.skalada.modelo.ModeloVia;
import com.ipartek.formacion.skalada.modelo.ModeloZona;

/**
 * Servlet implementation class IndexBackController
 */
public class IndexBackController extends HttpServlet {
	private static final long serialVersionUID1 = 1L;
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;

	private ModeloSector modeloSector = null;
	private ModeloUsuario modeloUsuario = null;
	private ModeloZona modeloZona = null;
	private ModeloVia modeloVia = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexBackController() {
		super();
		this.modeloSector = new ModeloSector();
		this.modeloUsuario = new ModeloUsuario();
		this.modeloZona = new ModeloZona();
		this.modeloVia = new ModeloVia();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("sectoresPublicados",
				Integer.toString(this.modeloSector.sectoresPublicados()));
		request.setAttribute("usuariosSinValidar",
				Integer.toString(this.modeloUsuario.usuariosNoValidados()));
		request.setAttribute("zonasPublicados",
				Integer.toString(this.modeloZona.zonasPublicados()));
		request.setAttribute("viasPublicados",
				Integer.toString(this.modeloVia.viasPublicados()));
		this.dispatcher = request
				.getRequestDispatcher(Constantes.VIEW_BACK_INDEX);
		this.dispatcher.forward(request, response);
	}

}

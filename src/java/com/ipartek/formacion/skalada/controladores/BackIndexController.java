package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.listener.SessionListener;
import com.ipartek.formacion.skalada.modelo.ModeloSector;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;

/**
 * Servlet implementation class BackIndexController
 * 
 * @author Javi
 */
public class BackIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;

	private ModeloSector modeloSector = null;
	private ModeloUsuario modeloUsuario = null;

	/**
	 * Inicia el servlet
	 * 
	 * @param config
	 *            {@code ServletConfig}
	 * @throws ServletException
	 * @author Javi
	 */
	@Override()
	public void init(ServletConfig config) throws ServletException {
		this.modeloSector = new ModeloSector();
		this.modeloUsuario = new ModeloUsuario();
	}

	/**
	 * doPost
	 * 
	 * @param request
	 *            {@code HttpServletRequest}
	 * @param response
	 *            {@code HttpServletResponse}
	 * @throws ServletException
	 * @throws IOException
	 * @author Javi
	 */
	@Override()
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("sectoresPublicados",
				Integer.toString(this.modeloSector.sectoresPublicados()));
		request.setAttribute("usuariosSinValidar",
				Integer.toString(this.modeloUsuario.usuariosSinValidar()));
		request.setAttribute("usuariosConectados",
				Integer.toString(SessionListener.sessionCount));
		this.dispatcher = request
				.getRequestDispatcher(Constantes.VIEW_BACK_INDEX);
		this.dispatcher.forward(request, response);
	}

	/**
	 * doGet
	 * 
	 * @param request
	 *            {@code HttpServletRequest}
	 * @param response
	 *            {@code HttpServletResponse}
	 * @throws ServletException
	 * @throws IOException
	 * @author Javi
	 */
	@Override()
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
}

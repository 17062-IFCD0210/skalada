package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Usuario;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;

/**
 * Servlet implementation class LoginController
 *
 * @author ander
 */
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(LoginController.class);

	// Key oara guardar el usuario en la session
	public static final String KEY_SESSION_USER = "ss_user";

	private RequestDispatcher dispatcher = null;
	private HttpSession session = null;
	/*
	 * private final String EMAIL = "admin@admin.com"; private final String PASS
	 * = "admin";
	 */
	private String pEmail;
	private String pPassword;

	private ModeloUsuario modeloUsuario = null;
	private Usuario usuario = null;

	@Override()
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		this.modeloUsuario = new ModeloUsuario();
		/*
		 * try { // Fichero configuracion de Log4j Properties props = new
		 * Properties();
		 * props.load(this.getClass().getResourceAsStream("/log4j.properties"));
		 * PropertyConfigurator.configure(props);
		 *
		 * } catch (IOException e) { e.printStackTrace(); }
		 */

	}

	/**
	 * @param request
	 *            a
	 * @param response
	 *            a
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @throws ServletException
	 *             a
	 * @throws IOException
	 *             a
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @param request
	 *            a
	 * @param response
	 *            a
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @throws ServletException
	 *             a
	 * @throws IOException
	 *             a
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		LOG.info("Entrando....");
		// recoger la sesion
		this.session = request.getSession();
		this.usuario = (Usuario) this.session.getAttribute(KEY_SESSION_USER);
		// Usuario logeado
		if (this.usuario != null) {

			// Ir a => index_back.jsp
			this.dispatcher = request
					.getRequestDispatcher(Constantes.VIEW_BACK_INDEX);

			// Usuario No logeado o caducada session
		} else {

			// recoger parametros del formulario
			this.getParameters(request);
			// obtener usuario por email
			Usuario user = this.modeloUsuario.getByEmail(this.pEmail);
			// validar los datos
			// comprobamos con la BBDD
			if (this.pPassword.equals(user.getPassword())) {
				// salvar session
				this.session.setAttribute(KEY_SESSION_USER, user);
				// Ir a => index_back.jsp
				// tenemos que pasar por un nuevo controlador para cargar el
				// numero de sectores
				// y etc controladro que controla el INDEX_BACK
				// desde ese controlador deberiamos ir a
				// Constantes.VIEW_BACK_INDEX
				this.dispatcher = request
						.getRequestDispatcher(Constantes.CONTROLLER_INDEXBACK);
			} else {
				// Ir a => login.jsp
				/*
				 * request.setAttribute("msg",
				 * "El email y/o contrase&ntilde;a incorrecta");
				 */
				this.dispatcher = request
						.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
			}
		}
		LOG.info("Saliendo....");
		this.dispatcher.forward(request, response);

	}

	/**
	 * Recoger los parametros enviados
	 *
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	private void getParameters(HttpServletRequest request)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		this.pEmail = request.getParameter("email");
		this.pPassword = request.getParameter("password");

	}

}

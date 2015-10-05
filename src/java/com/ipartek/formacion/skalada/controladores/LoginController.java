package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ipartek.formacion.skalada.Constantes;

/**
 * Servlet implementation class LoginController
 *
 * @author
 */
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(LoginController.class);

	// Key oara guardar el usuario en la session
	public static final String KEY_SESSION_USER = "ss_user";

	private RequestDispatcher dispatcher = null;
	private HttpSession session = null;

	private final String EMAIL = "admin@admin.com";
	private final String PASS = "admin";

	private String pEmail;
	private String pPassword;

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
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

		LOG.info("Entrando....");

		// recoger la sesion
		this.session = request.getSession();
		String usuario = (String) this.session.getAttribute(KEY_SESSION_USER);

		// Usuario logeado
		if (usuario != null && "".equals(usuario)) {

			//
			LOG.info("    Usuario YA logueado");

			// Ir a => index_back.jsp
			this.dispatcher = request
					.getRequestDispatcher(Constantes.VIEW_BACK_INDEX);

			// Usuario No logeado o caducada session
		} else {

			//
			LOG.info("    Usuario NO logueado");

			// recoger parametros del formulario
			this.getParameters(request);

			// validar los datos

			// comprobamos con la BBDD
			if (this.EMAIL.equals(this.pEmail)
					&& this.PASS.equals(this.pPassword)) {

				// salvar session
				this.session.setAttribute(KEY_SESSION_USER, this.pEmail);

				// Ir a => index_back.jsp
				this.dispatcher = request
						.getRequestDispatcher(Constantes.VIEW_BACK_INDEX);
			} else {
				// Ir a => login.jsp
				request.setAttribute("msg",
						"El email y/o contrase&ntilde;a incorrecta");
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

package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.skalada.Constantes;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String USER = "admin@admin.com";
	private final String PASS = "admin";

	// Key para guardar el usuario en session
	public final static String KEY_SESSION_USER = "ss_user";

	private RequestDispatcher dispatcher = null;
	private String email;
	private String pass;
	private HttpSession session;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Login entrando...");

		session = request.getSession();
		String usuario = (String) session.getAttribute(KEY_SESSION_USER);

		// Usuario ya logeado
		if (usuario != null && !"".equals(usuario)) {
			System.out.println("    Usuario YA logeado");
			dispatcher = request
					.getRequestDispatcher(Constantes.VIEW_BACK_INDEX);
		} else { // Usuario no logeado o sesion caducada
			System.out.println("    Usuario NO logeado");

			// email y pass
			email = request.getParameter("email");
			pass = request.getParameter("password");

			if (email.equalsIgnoreCase(USER) && pass.equalsIgnoreCase(PASS)) {
				// Salvar sesion
				session.setAttribute(KEY_SESSION_USER, email);
				dispatcher = request
						.getRequestDispatcher(Constantes.VIEW_BACK_INDEX);
			} else {
				request.setAttribute("msg", "Email y/o contraseña no validos");
				dispatcher = request
						.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
			}
		}
		System.out.println("Login forward o saliendo");
		dispatcher.forward(request, response);

	}

}

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

	private RequestDispatcher dispatcher = null;
	String email;
	String pass;
	HttpSession session;

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

		session = request.getSession();
		String usuario = (String) session.getAttribute("user");

		// Usuario ya logeado
		if (usuario == null && !"".equals(usuario)) {
			dispatcher = request
					.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
		} else { // Usuario no logeado o sesion caducada
			// email y pass
			email = request.getParameter("email");
			pass = request.getParameter("password");

			if (!email.equalsIgnoreCase(USER)) {
				request.setAttribute("msg", "Email no valido");
				dispatcher = request
						.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
			} else if (!pass.equalsIgnoreCase(PASS)) {
				request.setAttribute("msg", "Password no valido");
				dispatcher = request
						.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
			} else {
				saveSession(request);
				dispatcher = request
						.getRequestDispatcher("backoffice/indexb.jsp");
			}
		}

		dispatcher.forward(request, response);

	}

	private void saveSession(HttpServletRequest request) {
		session.setAttribute("user", email);
	}

}

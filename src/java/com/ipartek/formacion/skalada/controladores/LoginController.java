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
import com.ipartek.formacion.skalada.bean.Mensaje;
import com.ipartek.formacion.skalada.bean.Usuario;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;
import com.ipartek.formacion.utilidades.EnviarEmails;

/**
 * Servlet implementation class LoginController
 * 
 * @author Javi
 */
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// necesario para usar logs
	private final static Logger LOG = Logger.getLogger(LoginController.class);

	private RequestDispatcher dispatcher = null;
	private HttpSession session = null;

	// Parametros
	private String pEmail;
	private String pPassword;

	// Variables
	ModeloUsuario modeloUsuario = null;
	Usuario usuario = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.modeloUsuario = new ModeloUsuario();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Mensaje msg = new Mensaje(Mensaje.MSG_WARNING, "Error al loguearte");
		LOG.info("Entrando a login...");
		try {
			this.getParameters(request);
			if (this.modeloUsuario.checkUser("", this.pEmail)) {
				this.usuario = (Usuario) this.modeloUsuario
						.getByEmail(this.pEmail);
				if (this.usuario.getPassword().equals(this.pPassword)) {
					if (this.usuario.getValidado() == Constantes.USER_VALIDATE) {
						// El usuario puede acceder

						this.session = request.getSession();
						// session.setAttribute(KEY_SESSION_USER,
						// usuario.getNombre());
						this.session.setAttribute(Constantes.KEY_SESSION_USER,
								this.usuario);

						msg.setTipo(Mensaje.MSG_SUCCESS);
						msg.setTexto("Acceso correcto");
						LOG.info(msg.getTexto());
						this.dispatcher = request
								.getRequestDispatcher(Constantes.CONTROLLER_BACK_INDEX);
					} else {
						// el usuario no esta validado
						if (this.enviarEmail()) {
							msg.setTipo(Mensaje.MSG_SUCCESS);
							msg.setTexto("No est�s validado. Revisa tu email para activar tu cuenta");
							LOG.warn("Usuario no validado - se envia email");
						} else {
							msg.setTexto("No est�s validado. Se ha producido alg�n error al enviar email de validaci�n");
							LOG.error("Usuario no validado - ERROR al enviar email");
						}

						this.dispatcher = request
								.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
					}
				} else {
					// error al teclear el password
					msg.setTexto("El password no coincide con el que tenemos registrado. Por favor intentalo de nuevo.");
					LOG.warn("Usuario validado, password erroneo");
					this.dispatcher = request
							.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
				}
			} else {
				// no existe ese email en la BD
				msg.setTexto("El email introducido no existe en la base de datos. Por favor reg�strate.");
				LOG.warn("Usuario no existe");
				this.dispatcher = request.getRequestDispatcher("backoffice/"
						+ Constantes.VIEW_BACK_SIGNUP);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setTexto("ERROR: " + e.getMessage());
			LOG.error(msg.getTexto());
		} finally {
			request.setAttribute("msg", msg);
			LOG.info("Saliendo de login...");
			this.dispatcher.forward(request, response);
		}

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

	private boolean enviarEmail() {
		boolean resul = false;

		String url = Constantes.SERVER + Constantes.CONTROLLER_REGISTRO
				+ "?email=" + this.usuario.getEmail();

		EnviarEmails correo = new EnviarEmails();
		correo.setDireccionFrom("skalada.ipartek@gmail.com");
		correo.setDireccionDestino(this.usuario.getEmail());
		correo.setMessageSubject("Confirmaci�n de registro de usuario en Skalada App");

		correo.setPlantillaHTML(Constantes.EMAIL_TEMPLATE_REGISTRO);
		correo.setReemplazos("{usuario}", this.usuario.getNombre());
		correo.setReemplazos("{url}", url);

		resul = correo.enviar();
		return resul;
	}
}

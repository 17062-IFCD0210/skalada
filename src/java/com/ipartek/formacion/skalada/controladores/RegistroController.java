package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Mensaje;
import com.ipartek.formacion.skalada.bean.Rol;
import com.ipartek.formacion.skalada.bean.Usuario;
import com.ipartek.formacion.skalada.modelo.ModeloRol;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;
import com.ipartek.formacion.utilidades.EnviarEmails;

/**
 * Servlet implementation class RegistroController
 * 
 * @author Javi
 */
public class RegistroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RequestDispatcher dispatcher = null;
	private ModeloUsuario modeloUsuario = null;
	private ModeloRol modeloRol = null;
	private Usuario usuario = null;
	private Rol rol = null;

	// parametros
	private String pNombre = "";
	private String pEmail = "";
	private String pPassword = "";

	/**
	 * Este metodo se ejecuta solo la primera vez que se llama al servlet Se usa
	 * para crear el modelo
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.modeloUsuario = new ModeloUsuario();
		this.modeloRol = new ModeloRol();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Mensaje msg = new Mensaje(Mensaje.MSG_DANGER,
				"Error activando cuenta de usuario");
		// recoger parametro
		this.pEmail = (request.getParameter("email"));
		if (!this.modeloUsuario.checkUser("", this.pEmail)) {
			// NO existe el email
			msg.setTexto("La direcciï¿½n de email '" + this.pEmail
					+ "' no existe");
			this.dispatcher = request
					.getRequestDispatcher(Constantes.VIEW_BACK_SIGNUP);
		} else {
			// SI existe el email

			// ver si esta validado
			if (!this.modeloUsuario.estaValidado(this.pEmail)) {
				// NO esta validado
				this.usuario = (Usuario) this.modeloUsuario
						.getByEmail(this.pEmail);
				this.usuario.setValidado(Constantes.USER_VALIDATE);
				this.modeloUsuario.update(this.usuario);

				msg.setTipo(Mensaje.MSG_SUCCESS);
				msg.setTexto("El usuario ha sido validado");

				this.dispatcher = request
						.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
			} else {
				// SI esta validado

				msg.setTexto("El usuario ya estaba validado");
				this.dispatcher = request
						.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
			}

		}
		request.setAttribute("msg", msg);
		this.dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Mensaje msg = new Mensaje(Mensaje.MSG_DANGER,
				"Error dando de alta al usuario");
		try {

			this.getParameters(request, response);
			if (this.modeloUsuario.checkUser(this.pNombre, this.pEmail)) {
				msg.setTexto("El usuario ya existe");
				this.dispatcher = request.getRequestDispatcher("backoffice/"
						+ Constantes.VIEW_BACK_SIGNUP);
			} else {
				this.crearObjeto();
				if (this.modeloUsuario.save(this.usuario) != -1) {
					if (this.enviarEmail()) {
						msg.setTipo(Mensaje.MSG_SUCCESS);
						msg.setTexto("Revisa tu email para confirmar el alta");
					} else {
						msg.setTexto("Error al enviar el email Por favor ponte en contacto con nosotros "
								+ EnviarEmails.DIRECCIONORIGEN);
					}
				} else {
					msg = new Mensaje(Mensaje.MSG_DANGER,
							"Error al registrar usuario");
				}
				this.dispatcher = request
						.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = new Mensaje(Mensaje.MSG_DANGER, "Error al registrar usuario");
			request.setAttribute("msg", msg);
		} finally {
			request.setAttribute("msg", msg);
			this.dispatcher.forward(request, response);
		}
	}

	private void getParameters(HttpServletRequest request,
			HttpServletResponse response) {
		this.pNombre = (request.getParameter("nombre"));
		this.pEmail = (request.getParameter("email"));
		this.pPassword = (request.getParameter("password"));
	}

	/**
	 * Crea un Objeto {@code Usuario} Con los parametros recibidos
	 */
	private void crearObjeto() {
		this.rol = this.modeloRol.getById(Constantes.ROLE_USER_ID);
		this.usuario = new Usuario(this.pNombre, this.pEmail, this.pPassword,
				this.rol);
		this.usuario.setValidado(Constantes.USER_NO_VALIDATE);
	}

	private boolean enviarEmail() {
		boolean resul = false;

		String url = Constantes.SERVER + Constantes.CONTROLLER_REGISTRO
				+ "?email=" + this.usuario.getEmail();

		EnviarEmails correo = new EnviarEmails();
		correo.setDireccionFrom("skalada.ipartek@gmail.com");
		correo.setDireccionDestino(this.usuario.getEmail());
		correo.setMessageSubject("Confirmación de registro de usuario en Skalada App");

		// TODO cambiar la ruta
		correo.setPlantillaHTML(Constantes.EMAIL_TEMPLATE_REGISTRO);
		correo.setReemplazos("{usuario}", this.usuario.getNombre());
		correo.setReemplazos("{url}", url);

		resul = correo.enviar();
		return resul;
	}

}

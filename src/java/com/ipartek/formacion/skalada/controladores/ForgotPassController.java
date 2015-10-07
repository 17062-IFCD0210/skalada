package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Mensaje;
import com.ipartek.formacion.skalada.bean.Usuario;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;
import com.ipartek.formacion.skalada.util.EnviarEmails;
import com.ipartek.formacion.skalada.util.Utilidades;

/**
 * Servlet implementation class forgotPassController.
 */
public class ForgotPassController extends HttpServlet {
	//parametros
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private RequestDispatcher dispatcher = null;
	/**
	 * 
	 */
	private String pEmail;
	/**
	 * 
	 */
	private Usuario usuario = null;
	/**
	 * 
	 */
	private ModeloUsuario modeloUsuario = null;
	/**
	 * 
	 */
	private Mensaje msg = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassController() {
        super();
        this.modeloUsuario = new ModeloUsuario();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request,
	 *  HttpServletResponse response)
	 */
	@Override
	protected final void doGet(final HttpServletRequest request,
			final HttpServletResponse response) 
			throws ServletException, IOException {

		this.msg = new Mensaje(Mensaje.MSG_DANGER, "Error sin definir");
		try {
			
			this.pEmail = request.getParameter("email");
			//recuperar usuario por su email
			this.usuario = this.modeloUsuario.getByEmail(this.pEmail);
			//usuario no existe
			if (this.usuario == null) { 
				this.msg.setTexto("Email no registrado: " + this.pEmail);
				this.dispatcher = request
						.getRequestDispatcher(Constantes.VIEW_BACK_SIGNUP);
			//usuario encontrado
			} else {
				
				// Generar TOKEN para el usuario
				String token = Utilidades.getCadenaAlfanumAleatoria(250);
				this.usuario.setToken(token);
				this.modeloUsuario.update(this.usuario);			
				
				//Enviar email de validacion
				if (this.enviarEmail()) {
					this.msg = new Mensaje(
							Mensaje.MSG_SUCCESS ,
							"Por favor revisa tu Email"
							+ "para reestablecer "
							+ "las contraseñas");						
				} else {
					this.msg = new Mensaje(
							Mensaje.MSG_DANGER , "Error al enviar email, "
									+ "por favor ponte en contacto "
									+ "con nosotros "
					+ EnviarEmails.DIRECION_ORIGEN);						
				}	
			} 
			
			this.dispatcher = request
					.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			request.setAttribute("msg", this.msg);
			this.dispatcher.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 *  HttpServletResponse response)
	 */
	@Override
	protected final void doPost(final HttpServletRequest request,
					final HttpServletResponse response) 
					throws ServletException, IOException {
		this.msg = new Mensaje(Mensaje.MSG_DANGER, "Error sin definir");
		try {
			
			//recoger parametros
			this.pEmail = request.getParameter("email");
			String ptoken = request.getParameter(
					"token"); // recibo el token desde la JSP
			String pass = request.getParameter("password");
			
			//buscar usuario en BBDD
			this.usuario = this.modeloUsuario.getByEmail(this.pEmail);
			
			//usuario existe
			if (this.usuario != null) {
				
				// Comprobar que no se haya cambiado el email
				// para enviar el nuevo password
				if (ptoken.equals(this.usuario.getToken())) {
					
					this.usuario.setPassword(pass);
					if (this.modeloUsuario.update(this.usuario)) {
						this.msg.setTexto("Contraseñas"
								+ " modificadas correctamente");
						this.msg.setTipo(Mensaje.MSG_SUCCESS);
					}
				}
				
			} else {
				//usuario no existe
				this.msg.setTexto("usuario no existe");
				this.msg.setTipo(Mensaje.MSG_WARNING);
				
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.setAttribute("msg", this.msg);
			this.dispatcher = request.getRequestDispatcher(
					Constantes.VIEW_BACK_LOGIN);			
			this.dispatcher.forward(request, response);
		}
	}
	/**
	 * 
	 * @return resul
	 */
	private boolean enviarEmail() {
		
		boolean resul = false;
		
		try {
			EnviarEmails correo = new EnviarEmails();
				
			//url para validar el registro del usuario, 
			//llamara a este mismo controlador por Get pasando 
			//el email del usuario mas una accion para ahora
			String url = Constantes.SERVER 
					+ Constantes.ROOT_BACK + Constantes.VIEW_BACK_NEWPASS 
					+ "?email=" + this.usuario.getEmail() + "&token=" 
					+ this.usuario.getToken();
			
			//parametros para la plantilla			
			HashMap<String, String> parametros = new HashMap<String, String>();
			parametros.put("{usuario}", this.usuario.getNombre());
			parametros.put("{url}", url);
			parametros.put("{contenido}", "Has solicitado "
					+ "cambiar la contraseña, si no ha sido usted por "
					+ "favor pongase en bla BLA BLA....");
			parametros.put("{btn_submit_text}", "Solicitar nuevo Password");
			
			//configurar correo electronico
			correo.setDireccionFrom("skalada.ipartek@gmail.com");
			correo.setDireccionDestino(this.usuario.getEmail());
			correo.setMessageSubject("Recuperar Password");
			
			//generamos la plantilla			
			correo.setMessageContent(correo.generarPlantilla(
										Constantes.EMAIL_TEMPLATE_REGISTRO,
					 						parametros 
					 						)
					 				);
			//enviar
			resul = correo.enviar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

}
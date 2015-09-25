package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.util.HashMap;

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
 */
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RequestDispatcher dispatcher = null;

	private String pNombre;
	private String pEmail;
	private String pPass;

	// Valor por defecto de un nuevo usuario (Rol = Usuario)
	private int pIdRol = 2;

	private Usuario usuario = null;
	private Rol rol = null;
	private ModeloUsuario modeloUsuario = null;
	private ModeloRol modeloRol = null;
	private Mensaje msg = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		modeloUsuario = new ModeloUsuario();
		modeloRol = new ModeloRol();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			msg.setTexto("Error sin definir");
			msg.setTipo(Mensaje.MSG_DANGER);
			pEmail = request.getParameter("email");

			usuario = (Usuario) modeloUsuario.getByEmail(pEmail);
			//usuario  no existe
			if (usuario == null){ 
				msg.setTexto("Email no registrado: "+ pEmail);
				dispatcher = request
						.getRequestDispatcher(Constantes.VIEW_BACK_SIGNUP);
			//usuario encpontrado
			} else {	
				if( usuario.getValidado() == Constantes.USER_VALIDATE ){
					msg.setTexto("Ya estabas registrado");
					msg.setTipo(Mensaje.MSG_WARNING);
					
				}else{
					
					usuario.setValidado( Constantes.USER_VALIDATE );
					if( modeloUsuario.update(usuario) ){
						msg.setTexto("Eskerrik asko por registrarte");
						msg.setTipo(Mensaje.MSG_SUCCESS);
					}
				}
				dispatcher = request
						.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}finally{
			request.setAttribute("msg", msg);
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			// recoger parametros
			pNombre = (String) request.getParameter("nombre");
			pEmail = (String) request.getParameter("email");
			pPass = (String) request.getParameter("password");

			// Comprobamos si existe el usuario
			if (!modeloUsuario.checkUser(pNombre, pEmail)) {

				// Creamos el objeto Usuario
				rol = (Rol) modeloRol.getById(pIdRol);
				usuario = new Usuario(pNombre, pEmail, pPass, rol);

				// Guardar en Base Datos
				if (modeloUsuario.save(usuario) == -1) {
					msg = new Mensaje(Mensaje.MSG_DANGER,
							"Ha habido un error al guardar el usuario");
					dispatcher = request
							.getRequestDispatcher(Constantes.VIEW_BACK_SIGNUP);
				} else {
					// Enviar email de validación
					if (enviarEmail()) {
						msg = new Mensaje(Mensaje.MSG_SUCCESS,
								"Por favor revisa tu Email para validar tu registro");
					} else {
						msg = new Mensaje(Mensaje.MSG_DANGER,
								"Error al enviar email, por favor ponte en contacto con nosotros "
										+ EnviarEmails.direccionOrigen);
					}
					dispatcher = request
							.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
				}
			} else {
				msg = new Mensaje(Mensaje.MSG_DANGER,
						"El usuario o email ya existe");
				dispatcher = request
						.getRequestDispatcher(Constantes.VIEW_BACK_SIGNUP);
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = new Mensaje(Mensaje.MSG_WARNING,
					"Ha habido un error al guardar el usuario."
							+ e.getMessage());
			request.setAttribute("msg", msg);
		} finally {
			request.setAttribute("msg", msg);
			dispatcher.forward(request, response);
		}
	}

	private boolean enviarEmail(){
		boolean resul = false;
		try{
			EnviarEmails correo = new EnviarEmails();
			
			//url para validar el registro del usuario
			//llamara a este mismo controlador por GET pasando el email del usuario
			String url = Constantes.SERVER + Constantes.CONTROLLER_SIGNUP + "?email=" + usuario.getEmail();
			
			//parametros para la plantilla
			HashMap<String, String> parametros = new HashMap<String, String>();
			parametros.put("{usuario}", usuario.getNombre());
			parametros.put("{url}", url);
			parametros.put("{contenido}", "Gracias por registrarte. Para activar el usuario y verificar el email, clica en el enlace de debajo.");
			parametros.put("{btn_submit_text}", "Activa tu cuenta" );
			
			//configurar correo electronico
			correo.setDireccionFrom("skalada.ipartek@gmail.com");
			correo.setDireccionDestino(usuario.getEmail());			
			correo.setMessageContent( 
						correo.generarPlantilla( 
								Constantes.EMAIL_TEMPLATE_REGISTRO ,	
								parametros	
							)					
					);
			
			//enviar correo electronico
			resul = correo.enviar();
						
		}catch(Exception e){
			e.printStackTrace();
		}	
		
		return resul;
	}

}
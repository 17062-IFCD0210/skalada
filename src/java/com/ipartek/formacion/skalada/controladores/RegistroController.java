package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
import com.ipartek.formacion.skalada.util.SendMail;

/**
 * Servlet implementation class RegistroController
 */
public class RegistroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RequestDispatcher dispatcher = null;
	private ModeloUsuario modeloUsuario = null;
	private ModeloRol modeloRol = null;
	private Usuario usuario = null;
	private Rol rol = null;
	
	//parametros
	private int pAccion = Constantes.ACCION_VALIDAR;
	private int pID	= -1;		//ID no valido	
	private String pNombre;
	private String pEmail;
	private String pPassword;
	//private int pValidado;
	private int pIDRol = 2;	//Rol Usuario predefinido
	
	private SendMail mail;

	private Mensaje msg;
	
	 /**
     * Este metodo se ejecuta solo la primera vez que se llama al servlet
     * Se usa para crear el modelo
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	modeloUsuario = new ModeloUsuario();   
    	modeloRol = new ModeloRol();
    	mail = new SendMail();
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recoger parametros
		getParameters(request,response);
		
		//realizar accion solicitada
		switch (pAccion) {
		case Constantes.ACCION_RECUPERAR:
			recuperar(request,response);
			break;
		default:
			validar(request,response);
			break;
		}
		
		request.setAttribute("msg", msg);
		dispatcher.forward(request, response);
	}
	
	private void getParameters(HttpServletRequest request, HttpServletResponse response) {		
		try {
			request.setCharacterEncoding("UTF-8");
			if(request.getParameter("accion") != null && !"".equalsIgnoreCase(request.getParameter("accion"))){
				pAccion = Integer.parseInt(request.getParameter("accion"));	
			}
			if(request.getParameter("email") != null && !"".equalsIgnoreCase(request.getParameter("email"))){
				pEmail = request.getParameter("email");
			}
		} catch(Exception e){
			e.printStackTrace();
		}		
	}
		
	private void validar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pID = modeloUsuario.getIdByEmail(pEmail);
		if(pID != 0){
			usuario = (Usuario)modeloUsuario.getById(pID);
			if(usuario.getValidado() == Constantes.USER_VALIDATE){
				msg = new Mensaje(Mensaje.MSG_INFO, "Usuario ya activado, utiliza el nombre y la contraseña para loguearte.");
			} else {
				usuario.setValidado(Constantes.USER_VALIDATE);
				if(modeloUsuario.update(usuario)){
					msg = new Mensaje(Mensaje.MSG_SUCCESS, "Usuario activado, ya puedes loguearte.");
				} else {
					msg = new Mensaje(Mensaje.MSG_DANGER, "Error al activar, intentelo de nuevo");
				}
			}			
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);				
		} else {
			msg = new Mensaje(Mensaje.MSG_DANGER, "Usuario no registrado");
			dispatcher = request.getRequestDispatcher("backoffice/"+Constantes.VIEW_BACK_SIGNUP);
		}	
	}

	private void recuperar(HttpServletRequest request, HttpServletResponse response) {
		pID = modeloUsuario.getIdByEmail(pEmail);
		if(pID != 0){
			usuario = (Usuario)modeloUsuario.getById(pID);
			String asunto = "Recuperacion de contraseña";
			String mensaje = "Hola "+usuario.getNombre()+"."
							+ "\n Contraseña: " + usuario.getPassword()
							+ " \n\n Esperamos que disfrutes de nuestra web." + "\n\n Staf de Skalada App";
			
			if(mail.enviar(usuario.getEmail(), asunto, mensaje)){
				//Mensaje enviado correctamente
				msg = new Mensaje(Mensaje.MSG_INFO, "Contraseña enviada al email, compruebalo");					
			} else {
				//Error en el envio del mensaje
				msg = new Mensaje(Mensaje.MSG_DANGER, "Error al enviar el email, por favor ponte en contacto con nosotros (admin@admin.com)");
			}
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
		} else {
			msg = new Mensaje(Mensaje.MSG_DANGER, "Usuario no registrado");
			dispatcher = request.getRequestDispatcher("backoffice/"+Constantes.VIEW_BACK_SIGNUP);
		}	
	}

	
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recoger parametros del formulario
		getParametersForm(request);
		
		//Comprobar si estan libre el nombre y email del usuario
		if(!modeloUsuario.checkUser(pNombre, pEmail)){	
		
		//Esta libre			
			//Crear Objeto Usuario
			crearObjeto();
			
			//Guardar Objeto Usuario			
				if( modeloUsuario.save(usuario) != -1){	
					//enviar email
					String asunto = "Activacion nuevo Usuario";
					String mensaje = "<h1>Validar cuenta de usuario</h1>"
									+ "<p>Bienvenid@ " + usuario.getNombre() + ". Sólo nos falta un paso más.</p>"
									+ "<p>Para confirmar tu registro pincha en el siguiente enlace "
									+ Constantes.SERVER + Constantes.CONTROLLER_REGISTRO + "?accion="+Constantes.ACCION_VALIDAR+"&email=" + usuario.getEmail() + "</p>"
									+ "<p> Esperamos que disfrutes de nuestra web.</p>" 
									+ "<p>Staf de Skalada App</p>";
					
					if(mail.enviar(usuario.getEmail(), asunto, mensaje)){
						//Mensaje enviado correctamente
						msg = new Mensaje(Mensaje.MSG_INFO, "Mira tu cuenta de correo, y valida el registro");					
					} else {
						//Error en el envio del mensaje
						msg = new Mensaje(Mensaje.MSG_DANGER, "Error al enviar el email de activacion, por favor ponte en contacto con nosotros (admin@admin.com)");
					}
				} else {
					msg = new Mensaje(Mensaje.MSG_DANGER, "Error al registrar usuario");
				}

			//dispatcher login.jsp
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
				
		//No esta libre
		} else {
			msg = new Mensaje(Mensaje.MSG_DANGER, "Nombre o email del usuario no disponibles");
			dispatcher = request.getRequestDispatcher("backoffice/"+Constantes.VIEW_BACK_SIGNUP);	
		}
				
		request.setAttribute("msg", msg);
		dispatcher.forward(request, response);
		
	}
	
	/**
	 * Crea un Objeto {@code Usuario} Con los parametros recibidos
	 */
	private void crearObjeto() {
		rol = (Rol)modeloRol.getById(pIDRol);		
		usuario = new Usuario(pNombre, pEmail, pPassword, rol);
	}


	/**
	* Recoger los parametros enviados desde el formulario
	* @see backoffice\pages\\usuarios\form.jsp
	* @param request
	 * @throws UnsupportedEncodingException 
	*/
	private void getParametersForm(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		pNombre = request.getParameter("nombre");
		pEmail = request.getParameter("email");
		pPassword = request.getParameter("password");

	}
}
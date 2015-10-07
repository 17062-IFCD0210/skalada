package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
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

/**
 * Servlet implementation class LoginController
 * @author Curso
 */
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	//Logs
	private final static Logger LOG = Logger.getLogger(LoginController.class);
	
	//Key oara guardar el usuario en la session
	public static final String KEY_SESSION_USER = "ss_user";
       
	private RequestDispatcher dispatcher = null;
	private HttpSession session = null;
	
	private ModeloUsuario modeloUsuario = null;
	private Usuario usuario = null;

	private String pUser;
	private String pPassword;
	
	private Mensaje msg;
	
	
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		modeloUsuario = new ModeloUsuario();	
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//recoger la sesion
		this.session = request.getSession();
		this.usuario = (Usuario)session.getAttribute(KEY_SESSION_USER);
		
//Usuario logeado
		if ( this.usuario != null || "".equals(usuario) ){	
//			if(sessionKey.getRol().getNombre().equalsIgnoreCase(Constantes.ROLE_ADMIN)){
				dispatcher = request.getRequestDispatcher(Constantes.CONTROLLER_BACK_HOME);
//			} else {
//				msg = new Mensaje(Mensaje.MSG_INFO, "Solo el administrador puede entrar. Sentimos las molestias");
//				dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_INDEX);
//			}

//Usuario No logeado o caducada session
		} else {		
			//recoger parametros del formulario
			getParameters(request);
			
			if (pUser != null && pPassword != null){
				
				//Obtener Usuario de BBDD
				if(modeloUsuario.checkUser(pUser, pUser)){
					
					//Existe el usuario
					usuario = modeloUsuario.getUserLogin(pUser);
					
					if(usuario.getValidado() == 0 ){
						
						//Usuario no validado
						msg = new Mensaje(Mensaje.MSG_WARNING, "Usuario no validado, por favor comprueba su bandeja de entrada del email.");
						request.setAttribute("msg", msg);
						dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
						
						LOG.warn("Usuario: " + usuario.getNombre() + "[id:" + usuario.getId() + "] Usuario no validado.");
						
					} else {
						
						//Usuario Validado
						if(pPassword.equals(usuario.getPassword())){
							
							//Password correcto
							//salvar session
							session.setAttribute(KEY_SESSION_USER, usuario);						
							dispatcher = request.getRequestDispatcher(Constantes.CONTROLLER_BACK_HOME);
							
							LOG.info("Usuario: " + usuario.getNombre() + "[id:" + usuario.getId() + "] Inicio sesion.");
							
						}else{
							
							//Password incorrecto
							msg = new Mensaje(Mensaje.MSG_DANGER, "La contraseña es incorrecta");
							request.setAttribute("msg", msg);
							dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
							
							LOG.warn("Usuario: " + usuario.getNombre() + "[id:" + usuario.getId() + "] Contraseña incorrecta.");
						}
					}			
				} else {
					
					//Nombre de usuario incorrecto
					msg = new Mensaje(Mensaje.MSG_DANGER, "Nombre o email no registrado");
					request.setAttribute("msg", msg);
					dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);	
					
					LOG.warn("Inicio de sesion usuario no registrado.");
				}	
			} else {
				dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
			}
		}	
		
		dispatcher.forward(request, response);
		
	}
	
	/**
	* Recoger los parametros enviados
	* @param request
	*/
	private void getParameters(HttpServletRequest request) {
	
		pUser = request.getParameter("user");
		pPassword = request.getParameter("password");
		
	}
	
}




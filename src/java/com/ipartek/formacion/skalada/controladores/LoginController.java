package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Mensaje;
import com.ipartek.formacion.skalada.bean.Rol;
import com.ipartek.formacion.skalada.bean.Usuario;
import com.ipartek.formacion.skalada.modelo.ModeloRol;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
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
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        modeloUsuario = new ModeloUsuario();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//recoger la sesion
		session = request.getSession();
		String sessionKey = (String)session.getAttribute(KEY_SESSION_USER);
		
//Usuario logeado
		if ( sessionKey != null || "".equals(sessionKey) ){	
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_INDEX);
			
//Usuario No logeado o caducada session
		} else {		
			//recoger parametros del formulario
			getParameters(request);
				
			//Obtener Usuario de BBDD
			if(modeloUsuario.checkUser(pUser, pUser)){
				
				//Existe el usuario
				usuario = (Usuario)modeloUsuario.getUserLogin(pUser);
				
				if(usuario.getValidado() == 0 ){
					
					//Usuario no validado
					msg = new Mensaje(Mensaje.MSG_WARNING, "Usuario no validado, por favor comprueba su bandeja de entrada del email.");
					request.setAttribute("msg", msg);
					dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
				} else {
					
					//Usuario Validado
					if(pPassword.equals(usuario.getPassword())){
						
						//Password correcto
						//salvar session
						session.setAttribute(KEY_SESSION_USER, usuario.getEmail());						
						dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_INDEX);
						
					}else{
						
						//Password incorrecto
						msg = new Mensaje(Mensaje.MSG_DANGER, "La contraseña es incorrecta");
						request.setAttribute("msg", msg);
						dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
					}
				}			
			} else {
				
				//Nombre de usuario incorrecto
				msg = new Mensaje(Mensaje.MSG_DANGER, "Nombre o email no registrado");
				request.setAttribute("msg", msg);
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




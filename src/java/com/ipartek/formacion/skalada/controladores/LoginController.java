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

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//Key oara guardar el usuario en la session
	public static final String KEY_SESSION_USER = "ss_user";
       
	private RequestDispatcher dispatcher = null;
	private HttpSession session = null;
	
	private final String EMAIL = "admin@admin.com";
	private final String PASS = "admin";
	
	private String pEmail;
	private String pPassword;
	
	private Mensaje msg;

		
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
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
		String usuario = (String)session.getAttribute(KEY_SESSION_USER);
		
//Usuario logeado
		if ( usuario != null && "".equals(usuario) ){
			//Ir a => index_back.jsp		
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_INDEX);
			
//Usuario No logeado o caducada session
		} else {		
			//recoger parametros del formulario
			getParameters(request);
					
			//validar los datos

			//comprobamos con la BBDD			
			if(EMAIL.equals(pEmail)&&PASS.equals(pPassword)){
				
				//salvar session
				session.setAttribute(KEY_SESSION_USER, pEmail);
				
				//Ir a => index_back.jsp		
				dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_INDEX);
			} else {
				msg = new Mensaje(Mensaje.MSG_DANGER, "El email y/o contraseña incorrecta");				
				request.setAttribute("msg", msg);
				//Ir a => login.jsp
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
	
		pEmail = request.getParameter("email");
		pPassword = request.getParameter("password");
		
	}
	
}




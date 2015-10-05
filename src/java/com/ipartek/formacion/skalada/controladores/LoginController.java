package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Usuario;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(LoginController.class);
	
	//Key oara guardar el usuario en la session
	public static final String KEY_SESSION_USER = "ss_user";
       
	private RequestDispatcher dispatcher = null;
	private HttpSession session = null;
	private ModeloUsuario modelUsuario = null;
	
	private final String EMAIL = "admin@admin.com";
	private final String PASS = "admin";
	
	private String pEmail;
	private String pPassword;

		
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		try {
			//Fichero configuracion de Log4j
			Properties props = new Properties();		
			props.load( getClass().getResourceAsStream("/log4j.properties"));
			PropertyConfigurator.configure(props);
			
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		
		modelUsuario = new ModeloUsuario();
		
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

		log.info("Entrando....");
		
		//recoger la sesion
		session = request.getSession();
		String usuario = (String)session.getAttribute(KEY_SESSION_USER);
		
		//Usuario logeado
		if ( usuario != null  ){
			
			//
			System.out.println("    Usuario YA logueado");
			
			//Ir a => index_back.jsp		
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_INDEX);
			
//Usuario No logeado o caducada session
		} else {
			
			//
			System.out.println("    Usuario NO logueado");
			
			//recoger parametros del formulario
			getParameters(request);
			
			//obtener usuario por email
			Usuario user  = (Usuario)modelUsuario.getByEmail(pEmail);
			
			//validar los datos

			//comprobamos con la BBDD			
			if(EMAIL.equals(pEmail)&&PASS.equals(pPassword)){
				
				//salvar session
				session.setAttribute(KEY_SESSION_USER, user );
				
				//Ir a => index_back.jsp		
				dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_INDEX);
			} else {
				//Ir a => login.jsp
				request.setAttribute("msg", "El email y/o contrase&ntilde;a incorrecta");			
				dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
			}
			
		}
		
		log.info("Saliendo....");
				
		dispatcher.forward(request, response);
		
	}
	
	/**
	* Recoger los parametros enviados
	* @param request
	 * @throws UnsupportedEncodingException 
	*/
	private void getParameters(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		pEmail = request.getParameter("email");
		pPassword = request.getParameter("password");
		
	}
	
}




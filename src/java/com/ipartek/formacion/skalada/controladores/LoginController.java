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
import com.ipartek.formacion.skalada.bean.Mensaje;
import com.ipartek.formacion.skalada.bean.Usuario;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;
import com.ipartek.formacion.utilidades.EnviarEmails;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	//necesario para usar logs
	private final static Logger log = Logger.getLogger(LoginController.class);

	
	//Key para guardar el usuario en la session
	public static final String KEY_SESSION_USER = "ss_user";
       
	private RequestDispatcher dispatcher = null;
	private HttpSession session = null;
	
//	private final String EMAIL = "admin@admin.com";
//	private final String PASS = "admin";
	
	//Parametros
	private String pEmail;
	private String pPassword;
	
	//Variables
	ModeloUsuario modeloUsuario=null;
	Usuario usuario=null;
	
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	modeloUsuario = new ModeloUsuario();
    	try {
			//Fichero configuracion de Log4j
			Properties props = new Properties();		
			props.load( getClass().getResourceAsStream("/log4j.properties"));
			PropertyConfigurator.configure(props);
			
		} catch (IOException e) {			
			e.printStackTrace();
		}		
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mensaje msg = new Mensaje(Mensaje.MSG_WARNING,"Error al loguearte");
		log.info("Entrando a login...");
		try{
			getParameters(request);
			if(modeloUsuario.checkUser("", pEmail)){
				usuario=(Usuario)modeloUsuario.getByEmail(pEmail);
				if(usuario.getPassword().equals(pPassword)){
					if(usuario.getValidado()==Constantes.USER_VALIDATE){
						//El usuario puede acceder

						session = request.getSession();
						session.setAttribute(KEY_SESSION_USER, usuario.getNombre());
						
						msg.setTipo(Mensaje.MSG_SUCCESS);
						msg.setTexto("Acceso correcto");
						log.info(msg.getTexto());
						dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_INDEX);
					}else{
						//el usuario no esta validado
						if(enviarEmail()){
							msg.setTipo(Mensaje.MSG_SUCCESS);
							msg.setTexto("No est�s validado. Revisa tu email para activar tu cuenta");
							log.warn("Usuario no validado - se envia email");
						}else{
							msg.setTexto("No est�s validado. Se ha producido alg�n error al enviar email de validaci�n");
							log.error("Usuario no validado - ERROR al enviar email");
						}

						dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
					}
				}else{
					//error al teclear el password
					msg.setTexto("El password no coincide con el que tenemos registrado. Por favor intentalo de nuevo.");
					log.warn("Usuario validado, password erroneo");
					dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);				
				}
			}else{
				//no existe ese email en la BD
				msg.setTexto("El email introducido no existe en la base de datos. Por favor reg�strate.");
				log.warn("Usuario no existe");
				dispatcher = request.getRequestDispatcher("backoffice/"+Constantes.VIEW_BACK_SIGNUP);
			}
		}catch(Exception e){
			e.printStackTrace();
			msg.setTexto("ERROR: "+e.getMessage());
			log.error(msg.getTexto());
		}finally{
			request.setAttribute("msg", msg);
			log.info("Saliendo de login...");
			dispatcher.forward(request, response);			
		}

		
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
	
	private boolean enviarEmail(){
		boolean resul=false;

		String url=Constantes.SERVER+Constantes.CONTROLLER_REGISTRO+"?email="+usuario.getEmail();
		
		EnviarEmails correo = new EnviarEmails();
		correo.setDireccionFrom("skalada.ipartek@gmail.com");
		correo.setDireccionDestino(usuario.getEmail());
		correo.setMessageSubject("Confirmaci�n de registro de usuario en Skalada App");
		
		correo.setPlantillaHTML(Constantes.EMAIL_TEMPLATE_REGISTRO);
		correo.setReemplazos("{usuario}", usuario.getNombre());
		correo.setReemplazos("{url}", url);

		resul=correo.enviar();
		return resul;
	}	
}




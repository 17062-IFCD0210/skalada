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
import com.ipartek.formacion.skalada.utilidades.EnviarEmails;
import com.ipartek.formacion.skalada.utilidades.Utilidades;

/**
 * Servlet implementation class forgotPassController
 */
public class ForgotPassController extends HttpServlet {
	//parametros
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;
	private String pEmail;
	private Usuario usuario = null;
	private ModeloUsuario modeloUsuario = null;
	private Mensaje msg = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassController() {
        super();
        modeloUsuario = new ModeloUsuario();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		msg = new Mensaje(Mensaje.MSG_DANGER,"Error sin definir");
		try{
			
			pEmail = request.getParameter("email");
			//recuperar usuario por su email
			usuario = (Usuario) modeloUsuario.getByEmail(pEmail);
			//usuario no existe
			if (usuario == null){ 
				msg.setTexto("Email no registrado: "+ pEmail);
				dispatcher = request
						.getRequestDispatcher(Constantes.VIEW_BACK_SIGNUP);
			//usuario encontrado
			}else{
				
				// Generar TOKEN para el usuario
				String token = Utilidades.getCadenaAlfanumAleatoria(250);
				usuario.setToken(token);;
				modeloUsuario.update(usuario);			
				
				//Enviar email de validacion
				if ( enviarEmail() ){
					msg = new Mensaje( Mensaje.MSG_SUCCESS , "Por favor revisa tu Email para reestablecer las contraseñas");						
				}else{
					msg = new Mensaje( Mensaje.MSG_DANGER , "Error al enviar email, por favor ponte en contacto con nosotros " + EnviarEmails.direccionOrigen);						
				}	
			} 
			
			dispatcher = request
					.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			request.setAttribute("msg", msg);
			dispatcher.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		msg = new Mensaje(Mensaje.MSG_DANGER,"Error sin definir");
		try{
			
			//recoger parametros
			pEmail= (String) request.getParameter("email");
			String ptoken = (String) request.getParameter("token"); // recibo el token desde la JSP
			String pass =  (String) request.getParameter("password");
			
			//buscar usuario en BBDD
			usuario = (Usuario) modeloUsuario.getByEmail(pEmail);
			
			//usuario existe
			if(usuario!=null){
				
				// Comprobar que no se haya cambiado el email, para enviar el nuevo password
				if ( ptoken.equals(usuario.getToken() )){
					
					usuario.setPassword(pass);
					if( modeloUsuario.update(usuario)) {
						msg.setTexto("Contraseñas modificadas correctamente");
						msg.setTipo(Mensaje.MSG_SUCCESS);
					}
				}
				
			}else{
				//usuario no existe
				msg.setTexto("usuario no existe");
				msg.setTipo(Mensaje.MSG_WARNING);
				
			}
				
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			request.setAttribute("msg", msg);
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN );			
			dispatcher.forward(request, response);
		}
	}
	private boolean enviarEmail()  {
		
		boolean resul = false;
		
		try{
			EnviarEmails correo = new EnviarEmails();
				
			//url para validar el registro del usuario, 
			//llamara a este mismo controlador por Get pasando el email del usuario
			//mas una accion para ahora
			String url = Constantes.SERVER + Constantes.ROOT_BACK + Constantes.VIEW_BACK_NEWPASS +"?email="+usuario.getEmail()+"&token="+usuario.getToken();
			
			//parametros para la plantilla			
			HashMap<String, String> parametros = new HashMap<String, String>();
			parametros.put("{usuario}", usuario.getNombre());
			parametros.put("{url}", url);
			parametros.put("{contenido}", "Has solicitado cambiar la contraseña, si no ha sido usted por favor pongase en bla BLA BLA....");
			parametros.put("{btn_submit_text}", "Solicitar nuevo Password");
			
			//configurar correo electronico
			correo.setDireccionFrom("skalada.ipartek@gmail.com");
			correo.setDireccionDestino( usuario.getEmail() );
			correo.setMessageSubject("Recuperar Password");
			
			//generamos la plantilla			
			correo.setMessageContent( correo.generarPlantilla
										(Constantes.EMAIL_TEMPLATE_REGISTRO,
					 						parametros 
					 						)
					 				);
			//enviar
			resul = correo.enviar();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return resul;
	}

}
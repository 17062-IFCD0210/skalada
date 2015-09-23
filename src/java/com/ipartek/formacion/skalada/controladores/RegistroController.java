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
 */
public class RegistroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RequestDispatcher dispatcher = null;
	private ModeloUsuario modeloUsuario = null;
	private ModeloRol modeloRol = null;
	private Usuario usuario = null;	
	private Rol rol= null;
	
	//parametros
	private String pNombre = "";
	private String pEmail = "";
	private String pPassword = "";
       
	 /**
     * Este metodo se ejecuta solo la primera vez que se llama al servlet
     * Se usa para crear el modelo
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	modeloUsuario = new ModeloUsuario();   
    	modeloRol=new ModeloRol();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mensaje msg = new Mensaje(Mensaje.MSG_DANGER,"Error activando cuenta de usuario");
		//recoger parametro
		pEmail = (request.getParameter("email"));
		if(!modeloUsuario.checkUser("", pEmail)){
			//NO existe el email
			msg.setTexto("La direcci�n de email '"+pEmail+"' no existe");
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_SIGNUP);
		}else{
			//SI existe el email
			
			//ver si esta validado
			if(!modeloUsuario.estaValidado(pEmail)){
				//NO esta validado
				usuario=(Usuario)modeloUsuario.getByEmail(pEmail);
				usuario.setValidado(Constantes.USER_VALIDATE);
				modeloUsuario.update(usuario);
				
				msg.setTipo(Mensaje.MSG_SUCCESS);
				msg.setTexto("El usuario ha sido validado");
				
				dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
			}else{
				//SI esta validado
				
				msg.setTexto("El usuario ya estaba validado");
				dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
			}

		}
		request.setAttribute("msg", msg);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  Mensaje msg = new Mensaje(Mensaje.MSG_DANGER,"Error dando de alta al usuario");
	  try{
		
		getParameters(request,response);
		if(modeloUsuario.checkUser(pNombre, pEmail)){
			msg.setTexto("El usuario ya existe");
			dispatcher = request.getRequestDispatcher("backoffice/"+Constantes.VIEW_BACK_SIGNUP);
		}else{
			crearObjeto();
			if(modeloUsuario.save(usuario)!=-1){
				if(enviarEmail()){
					msg.setTipo(Mensaje.MSG_SUCCESS);
					msg.setTexto("Revisa tu email para confirmar el alta");
				}else{
					msg.setTexto("Error al enviar el email Por favor ponte en contacto con nosotros " + EnviarEmails.direccionOrigen);
				}
			}else{
				msg = new Mensaje(Mensaje.MSG_DANGER, "Error al registrar usuario");
			}
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
		}
	  }catch(Exception e){
		  e.printStackTrace();
		  msg = new Mensaje(Mensaje.MSG_DANGER, "Error al registrar usuario");
		  request.setAttribute("msg", msg);
	  }	finally{
		request.setAttribute("msg", msg);
		dispatcher.forward(request, response);		  
	  }
	}

	private void getParameters(HttpServletRequest request, HttpServletResponse response) {
		pNombre = (request.getParameter("nombre"));
		pEmail = (request.getParameter("email"));
		pPassword = (request.getParameter("password"));
	}
	
	/**
	 * Crea un Objeto {@code Usuario} Con los parametros recibidos
	 */
	private void crearObjeto() {
		rol = (Rol)modeloRol.getById(Constantes.ROLE_USER_ID);	
		usuario = new Usuario(pNombre, pEmail, pPassword, rol);
		usuario.setValidado(Constantes.USER_NO_VALIDATE);
	}
	
	
	private boolean enviarEmail(){
		boolean resul=false;
		EnviarEmails correo = new EnviarEmails();
		correo.setDireccionDestino(usuario.getEmail());
		correo.setMessageSubject("Confirmación de registro de usuario en Skalada App");
		String cuerpo="<h1>Validar cuenta de usuario</h1>";
		cuerpo+="<p>Bienvenid@ "+usuario.getNombre()+". Sólo nos falta un paso más.";
		cuerpo+="<br>Para confirmar tu registro pincha en el siguiente enlace ";
		//cuerpo+="<a href='http://localhost:8080/skalada/registro?email="+usuario.getEmail();
		cuerpo+="<a href='";
		cuerpo+=Constantes.SERVER+Constantes.CONTROLLER_REGISTRO+"?email="+usuario.getEmail();
		cuerpo+="'>ACTIVAR</a>";
		cuerpo+="<br><br> Esperamos que disfrutes de nuestra web." +"<br><br> Staf de Skalada App";
		//correo.setMessageText(cuerpo);
		correo.setMensajeHTML(cuerpo);
		correo.setDireccionFrom("skalada.ipartek@gmail.com");
		resul=correo.enviarHTML();
		return resul;
	}
		
}

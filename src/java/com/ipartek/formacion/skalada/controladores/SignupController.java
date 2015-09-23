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
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RequestDispatcher dispatcher = null;
	
	private String pNombre;
	private String pEmail;
	private String pPass;
	
	//Valor por defecto de un nuevo usuario (Rol = Usuario)
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			request.setCharacterEncoding("UTF-8");
			
			//recoger parametros
			pNombre = (String)request.getParameter("nombre");
			pEmail = (String)request.getParameter("email");
			pPass = (String)request.getParameter("password");
			
			//Comprobamos si existe el usuario
			if (!modeloUsuario.checkUser(pNombre, pEmail)){ 
			
				//Creamos el objeto Usuario
				rol = (Rol)modeloRol.getById(pIdRol);
				usuario = new Usuario(pNombre, pEmail, pPass, rol);
				
				//Guardar en Base Datos
				if (modeloUsuario.save(usuario) == -1){
					msg = new Mensaje( Mensaje.MSG_DANGER , "Ha habido un error al guardar el usuario");					
					dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_SIGNUP);
				}else{
					//Enviar email de validación
					if ( enviarEmail() ){
						msg = new Mensaje( Mensaje.MSG_SUCCESS , "Por favor revisa tu Email para validar tu registro");						
					}else{
						msg = new Mensaje( Mensaje.MSG_DANGER , "Error al enviar email, por favor ponte en contacto con nosotros " + EnviarEmails.direccionOrigen);						
					}	
					dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
				}
			}else{
				msg = new Mensaje( Mensaje.MSG_DANGER ,"El usuario o email ya existe");
				dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_SIGNUP);				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			msg = new Mensaje( Mensaje.MSG_WARNING , "Ha habido un error al guardar el usuario." + e.getMessage());
			request.setAttribute("msg", msg);
		}finally{
			request.setAttribute("msg", msg);
			dispatcher.forward(request, response);
		}
	}

	private boolean enviarEmail() {
		boolean resul = false;
		
		EnviarEmails correo = new EnviarEmails();
		
		correo.setDireccionFrom("skalada.ipartek@gmail.com");
		correo.setDireccionDestino( usuario.getEmail() );
		correo.setMessageSubject("Por favor valida tu email");
		String cuerpo = "<h1>Validar cuenta de usuario</h1>";
		cuerpo += "<p>Pulsa este enlace para validar:</p>";
		cuerpo += Constantes.SERVER + Constantes.CONTROLLER_SIGNUP+"?accion="+Constantes.ACCION_VALIDAR+"&email="+usuario.getEmail();
		
		correo.setMessageText(cuerpo);
		
		resul = correo.enviar();
		return resul;
	}

	
	
	
	
	
	
	
}
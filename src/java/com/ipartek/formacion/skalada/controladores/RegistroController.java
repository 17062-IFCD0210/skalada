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
	private int pID	= -1;		//ID no valido	
	private String pNombre;
	private String pEmail;
	private String pPassword;
	private int pValidado;
	private int pIDRol = 2;	//Rol Usuario predefinido
	
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
					
					//mensaje para que valide cuenta
					msg = new Mensaje(Mensaje.MSG_INFO, "Mira tu cuenta de correo, y valida el registro");

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
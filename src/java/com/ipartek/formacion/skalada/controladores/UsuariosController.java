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
import com.ipartek.formacion.skalada.bean.Grado;
import com.ipartek.formacion.skalada.bean.Mensaje;
import com.ipartek.formacion.skalada.bean.Rol;
import com.ipartek.formacion.skalada.bean.Usuario;
import com.ipartek.formacion.skalada.modelo.ModeloRol;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;

/**
 * Servlet implementation class UsuarioController
 */
public class UsuariosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private RequestDispatcher dispatcher = null;
	private ModeloUsuario modeloUsuario = null;
	private ModeloRol modeloRol = null;
	private Usuario usuario = null;
	private Rol rol = null;
	
	//parametros
	private int pAccion = Constantes.ACCION_LISTAR;		//Accion por defecto
	private int pID	= -1;		//ID no valido	
	private String pNombre;
	private String pEmail;
	private String pPassword;
	private int pValidado;
	private int pIDRol;
	
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recoger parametros
		getParameters(request,response);
		
		//realizar accion solicitada
		switch (pAccion) {
		case Constantes.ACCION_NUEVO:
			nuevo(request,response);
			break;
		case Constantes.ACCION_DETALLE:
			detalle(request,response);
			break;
		case Constantes.ACCION_ELIMINAR:
			eliminar(request,response);
			break;
		default:
			listar(request,response);
			break;
		}
			
		dispatcher.forward(request, response);
	}

	private void getParameters(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			pAccion = Integer.parseInt(request.getParameter("accion"));		
			if(request.getParameter("id") != null && !"".equalsIgnoreCase(request.getParameter("id"))){
				pID = Integer.parseInt(request.getParameter("id"));
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * Obtiene todas los usuarios del modelo y carga dispatch
	 * @see 
	 * @param request
	 * @param response
	 */
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("usuarios", modeloUsuario.getAll());
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_USUARIOS_INDEX);		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		if(modeloUsuario.delete(pID)){
			msg = new Mensaje(Mensaje.MSG_DANGER, "Registro eliminado correctamente");			
		} else {
			msg = new Mensaje(Mensaje.MSG_WARNING, "Error al eliminar el registro [id(" + pID + ")]");
		}
		request.setAttribute("msg", msg);
		listar(request, response);
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		rol = new Rol(Constantes.ROLE_USER);
		usuario = new Usuario("", "", "", rol); 
		request.setAttribute("usuario", usuario);
		request.setAttribute("titulo", "Crear nuevo Usuario");
		request.setAttribute("metodo", "Guardar");
		request.setAttribute("roles", modeloRol.getAll());
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_USUARIOS_FORM);
		
	}
	
	private void detalle(HttpServletRequest request, HttpServletResponse response) {
		usuario = (Usuario)modeloUsuario.getById(pID);
		request.setAttribute("usuario", usuario);
		request.setAttribute("titulo", usuario.getNombre().toUpperCase());
		request.setAttribute("metodo", "Modificar");
		request.setAttribute("roles", modeloRol.getAll());
		dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_USUARIOS_FORM);		
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
			//Crear Objeto Grado
			crearObjeto();
			
			//Guardar/Modificar Objeto Usuario
			if (pID == -1){
				if( modeloUsuario.save(usuario) != -1){	
					msg = new Mensaje(Mensaje.MSG_SUCCESS, "Registro creado con exito");
				} else {
					msg = new Mensaje(Mensaje.MSG_DANGER, "Error al guardar el nuevo registro");
				}
			} else {
				if(modeloUsuario.update(usuario)){
					msg = new Mensaje(Mensaje.MSG_SUCCESS, "Modificado correctamente el registro [id(" + pID + ")]");
				} else {
					msg = new Mensaje(Mensaje.MSG_DANGER, "Error al modificar el registro [id(" + pID + ")]");
				}
			}
			
			listar(request,response);
		
		//No esta libre
		} else {
			msg = new Mensaje(Mensaje.MSG_DANGER, "Nombre/email del usuario no disponibles");
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_USUARIOS_FORM);	
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
		usuario.setId(pID);
		usuario.setValidado(pValidado);
	}


	/**
	* Recoger los parametros enviados desde el formulario
	* @see backoffice\pages\\usuarios\form.jsp
	* @param request
	 * @throws UnsupportedEncodingException 
	*/
	private void getParametersForm(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		pID = Integer.parseInt(request.getParameter("id"));
		pNombre = request.getParameter("nombre");
		
		if(request.getParameter("validado") != null ) {
			pValidado = Integer.parseInt(request.getParameter("validado"));
		} else {
			pValidado = Constantes.USER_NO_VALIDATE;
		}
		
		pEmail = request.getParameter("email");
		pPassword = request.getParameter("password");
		
		pIDRol = Integer.parseInt(request.getParameter("rol"));

	}
}
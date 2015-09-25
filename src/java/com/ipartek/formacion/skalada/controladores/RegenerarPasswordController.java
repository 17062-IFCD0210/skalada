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
import com.ipartek.formacion.skalada.bean.Usuario;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;

/**
 * Servlet implementation class RegenerarPasswordController
 */
public class RegenerarPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	ModeloUsuario modeloUsuario=null;
	Usuario usuario = null;
	private RequestDispatcher dispatcher = null;  

	
	//Parametros
	String pEmail = "";
	String pPassword = "";

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		modeloUsuario=new ModeloUsuario();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mensaje msg=null;
		//getParameters
		if(request.getParameter("email")!=null){
			pEmail = request.getParameter("email");
			request.setAttribute("email",pEmail);
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_RESETEAR_PASSWORD);
		}else{
			msg=new Mensaje(Mensaje.MSG_WARNING,"Error al regenerar la contraseña");
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
		}
		request.setAttribute("msg", msg);
		dispatcher.forward(request, response);	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mensaje msg=new Mensaje(Mensaje.MSG_WARNING,"Error al regenerar la contraseña");
		//getParameters
		if(request.getParameter("email")!=null){
			pEmail = request.getParameter("email");
		}
		if(request.getParameter("password")!=null){
			pPassword=request.getParameter("password");
		}
		
		if(modeloUsuario.getByEmail(pEmail)==null){
			//NO existe ese email en la BD
			msg.setTexto("No existe ese email");
			
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_SIGNUP);
		}else{
			//SI existe el usuario
			usuario=(Usuario)modeloUsuario.getByEmail(pEmail);
			usuario.setPassword(pPassword);
			if(modeloUsuario.update(usuario)){
				msg.setTipo(Mensaje.MSG_SUCCESS);
				msg.setTexto("Contraseña reseteada correctamente");
			}else{
				msg.setTexto("Error al actualizar la contraseña");
			}
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
		}
		request.setAttribute("msg", msg);
		dispatcher.forward(request, response);	
	}


}

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
import com.ipartek.formacion.utilidades.EnviarEmails;
import com.ipartek.formacion.utilidades.Utilidades;

/**
 * Servlet implementation class RecordarPasswordController
 */
public class RecordarPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private RequestDispatcher dispatcher = null;
	private ModeloUsuario modeloUsuario = null;
	private Usuario usuario = null;	

	
	//parametros
	private String pEmail = "";
    
	/**
     * Este metodo se ejecuta solo la primera vez que se llama al servlet
     * Se usa para crear el modelo
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	modeloUsuario = new ModeloUsuario();   
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mensaje msg = new Mensaje(Mensaje.MSG_WARNING,"Error recuperando la contraseña del usuario");
		
		pEmail = (request.getParameter("email"));
		
		if(modeloUsuario.getByEmail(pEmail)==null){
			//NO existe ese email en la BD
			msg.setTexto("No tenemos registrado ese email");
			dispatcher = request.getRequestDispatcher("backoffice/"+Constantes.VIEW_BACK_RECORDAR_PASSWORD);
		
		}else{

			//Generar token para el usuario
			String token=Utilidades.getCadenaAlfanumAleatoria(250);
			
			usuario=(Usuario) modeloUsuario.getByEmail(pEmail);
			usuario.setToken(token);
			modeloUsuario.update(usuario);
			//Enviar email para confirmar reseteo de password
			
			if(enviarEmail()){
				msg.setTipo(Mensaje.MSG_SUCCESS);
				msg.setTexto("En breve recibirá un email con instruciones para el cambio de la contraseña");
			}else{
				msg.setTexto("Hemos tenido algún problema al enviarte el email");	
			}
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);							
		}
		request.setAttribute("msg", msg);
		dispatcher.forward(request, response);		
	}

	
	private boolean enviarEmail(){
		boolean resul=false;
		String cuerpo="";
		String url=Constantes.SERVER+Constantes.ROOT_BACK+Constantes.VIEW_BACK_RESETEAR_PASSWORD+"?email="+usuario.getEmail()+"&token="+usuario.getToken();
		
		EnviarEmails correo = new EnviarEmails();
		correo.setDireccionFrom("skalada.ipartek@gmail.com");
		correo.setDireccionDestino(usuario.getEmail());
		correo.setMessageSubject("Petición de cambio de contraseña");

		correo.setPlantillaHTML(Constantes.EMAIL_TEMPLATE_RESETEAR_PASS);
		correo.setReemplazos("{usuario}", usuario.getNombre());
		correo.setReemplazos("{url}", url);
		correo.setReemplazos("{email}", usuario.getEmail());
		
		correo.setMessageContent(cuerpo);

		resul=correo.enviar();
		return resul;
	}
}

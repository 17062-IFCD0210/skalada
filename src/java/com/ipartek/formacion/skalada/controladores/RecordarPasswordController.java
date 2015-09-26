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
	private String pPassword="";
	private String pToken="";
    
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mensaje msg = new Mensaje(Mensaje.MSG_WARNING,"Error recuperando la contrase�a del usuario");
		
		pEmail = (request.getParameter("email"));
		
		if(modeloUsuario.getByEmail(pEmail)==null){
			//NO existe ese email en la BD
			msg.setTexto("No tenemos registrado ese email");
			dispatcher = request.getRequestDispatcher("backoffice/"+Constantes.VIEW_BACK_SIGNUP);
		
		}else{

			//Generar token para el usuario
			String token=Utilidades.getCadenaAlfanumAleatoria(250);
			
			usuario=(Usuario) modeloUsuario.getByEmail(pEmail);
			usuario.setToken(token);
			modeloUsuario.update(usuario);
			//Enviar email para confirmar reseteo de password
			
			if(enviarEmail()){
				msg.setTipo(Mensaje.MSG_SUCCESS);
				msg.setTexto("En breve recibir� un email con instruciones para el cambio de la contrase�a");
			}else{
				msg.setTexto("Hemos tenido alg�n problema al enviarte el email");	
			}
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);							
		}
		request.setAttribute("msg", msg);
		dispatcher.forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mensaje msg=new Mensaje(Mensaje.MSG_WARNING,"Error al regenerar la contrase�a");
		//getParameters
		if(request.getParameter("email")!=null){
			pEmail = request.getParameter("email");
		}
		if(request.getParameter("password")!=null){
			pPassword=request.getParameter("password");
		}
		if(request.getParameter("token")!=null){
			pToken=request.getParameter("token");
		}
		
		if(modeloUsuario.getByEmail(pEmail)==null){
			//NO existe ese email en la BD
			msg.setTexto("No existe ese email");
			
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_SIGNUP);
		}else{
			//SI existe el usuario
			usuario=(Usuario)modeloUsuario.getByEmail(pEmail);
			
			//comprobar que no nos hayan cambiado el email
			if(pToken.equals(usuario.getToken())){
				usuario.setPassword(pPassword);
				if(modeloUsuario.update(usuario)){
					msg.setTipo(Mensaje.MSG_SUCCESS);
					msg.setTexto("Contrase�a reseteada correctamente");
				}else{
					msg.setTexto("Error al actualizar la contrase�a");
				}
			}else{
				msg.setTexto("Has intentado modificar la direcci�n de email");
			}
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);
		}
		request.setAttribute("msg", msg);
		dispatcher.forward(request, response);	
	}
	
	private boolean enviarEmail(){
		boolean resul=false;
		String cuerpo="";
		String url=Constantes.SERVER+Constantes.ROOT_BACK+Constantes.VIEW_BACK_NEW_PASS+"?email="+usuario.getEmail()+"&token="+usuario.getToken();
		String contenido="Hemos recibido una petición de cambio de contraseña de tu dirección de email. Si las has cursado tú confírmalo clicando en el enlace de debajo.";
		
		EnviarEmails correo = new EnviarEmails();
		correo.setDireccionFrom("skalada.ipartek@gmail.com");
		correo.setDireccionDestino(usuario.getEmail());
		correo.setMessageSubject("Petici�n de cambio de contrase�a");

		//correo.setPlantillaHTML(Constantes.EMAIL_TEMPLATE_RESETEAR_PASS);
		correo.setPlantillaHTML(Constantes.EMAIL_TEMPLATE_PLANTILLA);
		correo.setReemplazos("{contenido}", contenido);
		correo.setReemplazos("{usuario}", usuario.getNombre());
		correo.setReemplazos("{url}", url);
		correo.setReemplazos("{email}", usuario.getEmail());
		
		correo.setReemplazos("{btn_submit_text}", "Accede para resetaear tu contraseña");

		
		correo.setMessageContent(cuerpo);

		resul=correo.enviar();
		return resul;
	}
}

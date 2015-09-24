package com.ipartek.formacion.skalada.controladores;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Mensaje;
import com.ipartek.formacion.skalada.bean.Usuario;
import com.ipartek.formacion.skalada.modelo.ModeloUsuario;
import com.ipartek.formacion.utilidades.EnviarEmails;

/**
 * Servlet implementation class RecordarPasswordController
 */
public class CopyOfRecordarPasswordController extends HttpServlet {
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
		getParameters(request,response);
		Mensaje msg = new Mensaje(Mensaje.MSG_WARNING,"Error recuperando la contraseña del usuario");
		if(modeloUsuario.getByEmail(pEmail)==null){
			//NO existe ese email en la BD
			msg.setTexto("No tenemos registrado ese email");
			dispatcher = request.getRequestDispatcher("backoffice/"+Constantes.VIEW_BACK_RECORDAR_PASSWORD);
		
		}else{
			//Cambiar password en BD
			
			usuario=(Usuario)modeloUsuario.getByEmail(pEmail);
			usuario.setPassword(generarNuevaPass());
			modeloUsuario.update(usuario);
			
			//Enviar email con el nuevo password
			if(enviarEmail()){
				msg.setTipo(Mensaje.MSG_SUCCESS);
				msg.setTexto("En breve recibirá un email con su nueva contraseña");
			}else{
				msg.setTexto("Hemos tenido algún problema al enviarte el email");	
			}
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_LOGIN);							
		}
		request.setAttribute("msg", msg);
		dispatcher.forward(request, response);		
	}

	
	private void getParameters(HttpServletRequest request, HttpServletResponse response) {
		pEmail = (request.getParameter("email"));
	}
	
	private String generarNuevaPass(){
		String resul="";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		int i = 0;
		while ( i < 6){
			char c = (char)r.nextInt(255);
			if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
					resul += c;
					i ++;
			}
		}
		System.out.println(resul);
		return resul;
	}
	
	private boolean enviarEmail(){
		boolean resul=false;
		String cuerpo="";
		String url=Constantes.SERVER + Constantes.ROOT_APP+Constantes.VIEW_BACK_LOGIN;
		
		EnviarEmails correo = new EnviarEmails();
		correo.setDireccionFrom("skalada.ipartek@gmail.com");
		correo.setDireccionDestino(usuario.getEmail());
		correo.setMessageSubject("Envio de datos de acceso a Skalada App");

		//TODO cambiar la ruta 		
		correo.setPlantillaHTML(Constantes.TEST_EMAIL_TEMPLATE_RECORDAR_PASS);
		correo.setReemplazos("{usuario}", usuario.getNombre());
		correo.setReemplazos("{password}", usuario.getPassword());
		correo.setReemplazos("{url}", url);
		
		correo.setMessageContent(cuerpo);

		resul=correo.enviar();
		return resul;
	}
}

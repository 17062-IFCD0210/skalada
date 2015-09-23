package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.util.Random;

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
			enviarEmail();
			
			msg.setTipo(Mensaje.MSG_SUCCESS);
			msg.setTexto("En breve recibirá un email con su nueva contraseña");
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
	
	private void enviarEmail(){
		EnviarEmails sendEmail = new EnviarEmails();
		sendEmail.setDireccionDestino(usuario.getEmail());
		sendEmail.setMessageSubject("Envio de datos de acceso a Skalada App");
		String messageText="Bienvenid@ "+usuario.getNombre()+". Hemos tenido que resetar su contraseña."
				+ "\n Para poder acceder de nuevo utilice la siguiente contraseña: "+ usuario.getPassword()
				+ " \n\n Esperamos que disfrutes de nuestra web." +"\n\n Staf de Skalada App";
		sendEmail.setMessageText(messageText);
		sendEmail.setDireccionFrom("skalada.ipartek@gmail.com");
		sendEmail.enviar();
	}
}

package com.ipartek.formacion.skalada.controladores;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
	private int idUsuario;
	
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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Mensaje msg = new Mensaje(Mensaje.MSG_DANGER,"Error dando de alta al usuario");
		getParameters(request,response);
		if(modeloUsuario.checkUser(pNombre, pEmail)){
			msg.setTexto("El usuario ya existe");
			dispatcher = request.getRequestDispatcher(Constantes.VIEW_BACK_SIGNUP);
		}else{
			crearObjeto();
			modeloUsuario.save(usuario);
			enviarEmail();
			msg.setTipo(Mensaje.MSG_SUCCESS);
			msg.setTexto("Revisa tu email para confirmar el alta");
			dispatcher = request.getRequestDispatcher(Constantes.CONTROLLER_LOGIN);
		}
		dispatcher.forward(request, response);
		
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
		Rol rol = new Rol(Constantes.ROLE_USER);		
		usuario = new Usuario(pNombre, pEmail, pPassword, rol);
		usuario.setValidado(Constantes.USER_NO_VALIDATE);
		usuario.setId(idUsuario);
	}
	
	private void enviarEmail(){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("skalada.ipartek@gmail.com","123ABC123");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("skalada.ipartek@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usuario.getEmail()));
			message.setSubject("Confirmación de registro de usuario en Skalada App");
			message.setText("Para confirmar tu registro pincha en el siguiente enlace"
					+ "No es Spam," +"\n\n Enviado email desde Java, dentro de poco el codigo en tu GIT!");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}	
	}
}

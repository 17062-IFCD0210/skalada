package com.ipartek.formacion.skalada.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import com.ipartek.formacion.skalada.Constantes;


public class SendMail{
	
	Properties props;
	Session session;	
	Authenticator authenticator;
	Message message;
	
	/**
	 * Constructor que inicializa las propiedades y crea la sesion.
	 */
	public SendMail() {
		super();		
		//Propiedades de envio
		sendProperties();
			
		//Crear Sesion
		crearSesion();		
	}
	
	/**
	 * Metodo que se encarga de rellenar el email y enviarlo
	 * @param destino {@code String} Email al que se quiere enviar el correo
	 * @param asunto {@code String} Asunto del correo
	 * @param cuerpo {@code String} Cuerpo del email
	 * @return
	 */
	@SuppressWarnings("finally")
	public boolean enviar(String destino, String asunto, String cuerpo){
		boolean resul = false;
		try {
			message = new MimeMessage(session);			
			message.setFrom(new InternetAddress(Constantes.MAIL_USER));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
			message.setSubject(MimeUtility.encodeText(asunto,"UTF-8","B"));
			message.setContent(cuerpo, "text/html; charset=utf-8");
			
			Transport.send(message);
			resul = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return resul;
		}
	}
	


/**
 * Propiedades del envio de email
 *	<code>mail.smtp.host</code>, representa el host que hace de servidor de correo
 *	<code>mail.transport.protocol</code>, es el protocolo de comunicación que queremos utilizar para enviar el correo. En este caso es SMTP, pero podría ser mail.
 * 	<code>mail.smtp.auth</code>, indica si se requiere de autentificación de SMTP.
 * 	<code>mail.user</code>, es el usuario de la conexión.
 * 	<code>mail.password</code>, el el password de la conexión.
 */
	private void sendProperties() {
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "465");		
		props.put("mail.transport.protocol","smtp");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
	}
	
	private void crearSesion() {		
		authenticator = new Authenticator() {
			@Override
            protected PasswordAuthentication getPasswordAuthentication() {
            	return new PasswordAuthentication(Constantes.MAIL_USER, Constantes.MAIL_PASS);
           	}
        };
		
        session = Session.getInstance(props, authenticator);
    }    
	
}

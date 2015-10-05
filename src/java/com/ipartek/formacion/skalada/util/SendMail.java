package com.ipartek.formacion.skalada.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.io.IOUtils;


public class SendMail {
	
	private String MAIL_USER; 
	private String MAIL_PASS;
	
	private Properties props;
	private Session session;	
	private Authenticator authenticator;
	private Message message;
	
	
	/**
	 * Constructor que inicializa las propiedades y crea la sesion.
	 * @param mailUser {@code String} Cuenta de correo del usuario (Origen del mensaje)
	 * @param mailPass {@code String} Password del email anterior
	 */
	public SendMail(String mailUser, String mailPass) {
		super();
		this.MAIL_USER = mailUser;
		this.MAIL_PASS = mailPass;
		
		//Propiedades de envio
		sendProperties();
			
		//Crear Sesion
		crearSesion();		
	}
	
	
	/**
	 * Propiedades del envio de email:
	 * <ul>
	 *	<li><code>'mail.smtp.host'</code>, representa el host que hace de servidor de correo</li>
	 *	<li><code>'mail.transport.protocol'</code>, es el protocolo de comunicación que queremos utilizar para enviar el correo. En este caso es SMTP, pero podría ser mail.</li>
	 * 	<li><code>'mail.smtp.auth'</code>, indica si se requiere de autentificación de SMTP.</li>
	 * 	<li><code>'mail.user'</code>, es el usuario de la conexión.</li>
	 * 	<li><code>'mail.password'</code>, el el password de la conexión.</li>
	 * </ul>
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
	
	
	/**
	 * Crea la sesion y autentificando el email y password
	 */
	private void crearSesion() {
		authenticator = new Authenticator() {
			@Override
            protected PasswordAuthentication getPasswordAuthentication() {
            	return new PasswordAuthentication(MAIL_USER, MAIL_PASS);
           	}
        };		
        session = Session.getInstance(props, authenticator);
    }    
	
	
	/**
	 * Metodo para obtener el cuerpo del email personalizado
	 * @param fileUrl ruta donde se encuentra la plantilla html a enviar "src/resoruces"
	 * @param parametros {@code HashMap } con key-value a reemplazar en el email
	 * @return {@code String} con el cuerpo del email presonalizado
	 */
	@SuppressWarnings("rawtypes")
	public String mailTemplateToString(String fileUrl, HashMap<String, String> parametros ){
		String resul = "";
	    try{
	    	ClassLoader classLoader = getClass().getClassLoader();
	    	resul = IOUtils.toString(classLoader.getResourceAsStream(fileUrl), "UTF-8");
		} catch(IOException e) {
			e.printStackTrace();
		}	    
		Iterator it = parametros.entrySet().iterator();
	    while (it.hasNext()) {
	    	Map.Entry e = (Map.Entry)it.next();
	    	resul = resul.replace(e.getKey().toString(), e.getValue().toString());
	    }	    
	    return resul;		
	}
	
	
	/**
	 * Metodo que se encarga de rellenar el email y enviarlo
	 * @param destino {@code String} Email al que se quiere enviar el correo. 
	 * 				  Para el envio a mas de un destinatario concatenar emails con una ',' sin espacios
	 * @param asunto {@code String} Asunto del correo
	 * @param cuerpo {@code String} Cuerpo del email
	 * @return
	 */
	@SuppressWarnings("finally")
	public boolean enviar(String destino, String asunto, String cuerpo){
		boolean resul = false;
		try {
			message = new MimeMessage(session);			
			message.setFrom(new InternetAddress(MAIL_USER));
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
	
}

package com.ipartek.formacion.skalada.utilidades;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.io.IOUtils;
/**
 * 
 * @author Curso
 *
 */
public class EnviarEmails {
/**
 * 
 */
	public  static final String DIRECIONORIGEN = "skalada.ipartek@gmail.com";
	/**
	 * 
	 */
	private String passwordOrigen   = "123ABC123";
	/**
	 * 
	 */
	private String direccionFrom    = "";
	/**
	 * 
	 */
	private String direccionDestino = "";
	/**
	 * 
	 */
	private String messageSubject   = ""; //Asunto	
	/**
	 * 
	 */
	private String messageText      = ""; //Cuerpo Texto Plano
	/**
	 * 
	 */
	private String messageContent   = ""; //Cuerpo Html
	/**
	 * 
	 */

	private Session session;
	
		/**	Construye el objeto {@code EnviarEmails}. 
		*
		*/
		public EnviarEmails() {
			super();
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", 
					  "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			this.session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						@Override
						protected PasswordAuthentication 
							getPasswordAuthentication() {
							return new PasswordAuthentication(
									DIRECIONORIGEN, 
									EnviarEmails.this.passwordOrigen);
						}
					});			
		}
	
	 	
	
	/*********************** GETTERS Y SETTERS. **********************/
/**
 * 
 * @return direccionorigen
 */
	public final String getDireccionOrigen() {
		return DIRECIONORIGEN;
	}


	/*public void setDireccionOrigen(String direccionOrigen) {
		this.direccionOrigen = direccionOrigen;
	}
	*/

/**
 * 
 * @return passwordorigen
 */
	public final String getPasswordOrigen() {
		return this.passwordOrigen;
	}

	/*
	public void setPasswordOrigen(String passwordOrigen) {
		this.passwordOrigen = passwordOrigen;
	}
	*/

/**
 * 
 * @return direccionfrom
 */
	public final String getDireccionFrom() {
		return this.direccionFrom;
	}
/**
 * 
 * @param sDireccionFrom
 */

	public final void setDireccionFrom(String sDireccionFrom) {
		this.direccionFrom = sDireccionFrom;
	}

/**
 * 
 * @return direcciondestino
 */
	public final String getDireccionDestino() {
		return this.direccionDestino;
	}
/**
 * 
 * @param sDireccionDestino
 */

	public final void setDireccionDestino(String sDireccionDestino) {
		this.direccionDestino = sDireccionDestino;
	}
/**
 * 
 * @return messagecontent
 */

	public final String getMessageContent() {
		return this.messageContent;
	}

/**
 * 
 * @param sMessageContent
 */

	public final void setMessageContent(String sMessageContent) {
		this.messageContent = sMessageContent;
	}

/**
 * 
 * @return message subject
 */

	public final String getMessageSubject() {
		return this.messageSubject;
	}
/**
 * 
 * @param sMessageSubject
 */

	public final void setMessageSubject(final String sMessageSubject) {
		this.messageSubject = sMessageSubject;
	}
/**
 * 
 * @return message text
 */

	public final String getMessageText() {
		return this.messageText;
	}
/**
 * 
 * @param sMessageText
 */

	public final void setMessageText(final String sMessageText) {
		this.messageText = sMessageText;
	}

	
	
	@Override
	public final String toString() {
		return "EnviarEmails [direccionOrigen=" + DIRECIONORIGEN
				+ ", passwordOrigen=" + this.passwordOrigen + ", direccionFrom="
				+ this.direccionFrom + ", direccionDestino=" 
				+ this.direccionDestino
				+ ", messageSubject=" + this.messageSubject + ", messageText="
				+ this.messageText + ", session=" + this.session + "]";
	}

	
	/************************ METODO PUBLICO. ***************************/
	/**
	 * 
	 * @return resul
	 */
	public final boolean enviar() {
	
		boolean resul = false;
		try {
			Message message = new MimeMessage(this.session);
			message.setFrom(new InternetAddress(this.direccionFrom));
			message.setRecipients(
					Message.RecipientType.TO, 
					InternetAddress.parse(this.direccionDestino));
			message.setSubject(
					MimeUtility.encodeText(this.messageSubject, "UTF-8", "B"));
			
			if (!"".equals(this.messageText)) {
				message.setText(this.messageText);
			} else {
				message.setContent(this.messageContent,
						"text/html; charset=utf-8");
			}	
			Transport.send(message);
			resul = true;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return resul;
	}
	
	/**
	 * Genera un String a partir de una plantilla y un HashMap de parametros.
	 * @param plantilla Ruta donde se encuentra la plantilla del email,
	 *  debe estar en "src/resources"
	 * @param parametros HashMap con variables a sustituir en la plantilla
	 * @return String con HTML listo para enviar
	 * @throws IOException 
	 */
	
	public final String generarPlantilla(final String plantilla,
			final HashMap<String, String> parametros)
			throws IOException {
		String resul = "";

		ClassLoader classLoader = this.getClass().getClassLoader();
		resul = (IOUtils.toString(classLoader
				.getResourceAsStream(plantilla), "UTF-8"));

		Iterator<Map.Entry<String, String>> it =
				parametros.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> e = it.next();
			resul = resul.replace(e.getKey(), e.getValue());
		
		}
		return resul;
	}
}
package com.ipartek.formacion.utilidades;

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

public class EnviarEmails {
	
	public  static final String direccionOrigen = "skalada.ipartek@gmail.com";
	private String passwordOrigen   = "123ABC123";
	private String direccionFrom    ="";
	private String direccionDestino ="";
	private String messageSubject   =""; //Asunto	
	private String messageText      =""; //Cuerpo Texto Plano
	private String messageContent   =""; //Cuerpo Html
	
	private Session session;
	
		/**	Construye el objeto {@code EnviarEmails} 
		*
		*/
		public EnviarEmails() {
			super();
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(direccionOrigen,passwordOrigen);
						}
					});			
		}
	
	 	
	
	/*********************** GETTERS Y SETTERS **************************************************/

	public String getDireccionOrigen() {
		return direccionOrigen;
	}


	/*public void setDireccionOrigen(String direccionOrigen) {
		this.direccionOrigen = direccionOrigen;
	}
	*/


	public String getPasswordOrigen() {
		return passwordOrigen;
	}

	/*
	public void setPasswordOrigen(String passwordOrigen) {
		this.passwordOrigen = passwordOrigen;
	}
	*/


	public String getDireccionFrom() {
		return direccionFrom;
	}


	public void setDireccionFrom(String direccionFrom) {
		this.direccionFrom = direccionFrom;
	}


	public String getDireccionDestino() {
		return direccionDestino;
	}


	public void setDireccionDestino(String direccionDestino) {
		this.direccionDestino = direccionDestino;
	}


	public String getMessageContent() {
		return messageContent;
	}



	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}



	public String getMessageSubject() {
		return messageSubject;
	}


	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}


	public String getMessageText() {
		return messageText;
	}


	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	
	
	@Override
	public String toString() {
		return "EnviarEmails [direccionOrigen=" + direccionOrigen
				+ ", passwordOrigen=" + passwordOrigen + ", direccionFrom="
				+ direccionFrom + ", direccionDestino=" + direccionDestino
				+ ", messageSubject=" + messageSubject + ", messageText="
				+ messageText + ", session=" + session + "]";
	}

	
	/*************************** METODO PUBLICO ****************************************************/
	
	public boolean enviar(){
	
		boolean resul = false;
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(direccionFrom));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(direccionDestino));
			message.setSubject(MimeUtility.encodeText(messageSubject,"UTF-8","B"));
			
			if ( !"".equals(messageText) ){
				message.setText(messageText);
			}else{
				message.setContent(messageContent,"text/html; charset=utf-8");
			}	
			Transport.send(message);
			resul = true;
		}catch( Exception e) {
			e.printStackTrace();
		}	
		return resul;
	}
	
	/**
	 * Genera un String a partir de una plantilla y un HashMap de parametros
	 * @param plantilla Ruta donde se encuentra la plantilla del email, debe estar en "src/resources"
	 * @param parametros HashMap con variables a sustituir en la plantilla
	 * @return String con HTML listo para enviar
	 * @throws IOException 
	 */
	
	public String generarPlantilla(String plantilla, HashMap<String,String> parametros)
			throws IOException {
		String resul = "";

		ClassLoader classLoader = getClass().getClassLoader();
		resul = (IOUtils.toString(classLoader
				.getResourceAsStream(plantilla),"UTF-8"));

		Iterator<Map.Entry<String, String>> it = parametros.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String,String> e = (Map.Entry<String,String>)it.next();
			resul = resul.replace(e.getKey(), e.getValue());
		
		}
		return resul;
	}
}
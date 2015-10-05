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

/**
 * Clase para encapsular el envio de emails
 * 
 * @author Curso
 *
 */
public class EnviarEmails {

	public static final String DIRECCIONORIGEN = "skalada.ipartek@gmail.com";
	private String passwordOrigen = "123ABC123";
	private String direccionFrom = "";
	private String direccionDestino = "";
	private String messageSubject = ""; // Asunto
	private String messageText = ""; // Cuerpo Texto Plano
	private String messageContent = ""; // Cuerpo Html
	private String plantillaHTML = ""; // Archivo HTML a cargar
	private HashMap<String, String> reemplazos = null; // {campo},
	// valor_a_cargar

	private Session session;

	/**
	 * Construye el objeto {@code EnviarEmails}
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
					@Override()
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(DIRECCIONORIGEN,
								EnviarEmails.this.passwordOrigen);
					}
				});
		this.reemplazos = new HashMap<String, String>();
	}

	/*********************** GETTERS Y SETTERS **************************************************/
	/**
	 * 
	 * @return String
	 */
	public String getDireccionOrigen() {
		return DIRECCIONORIGEN;
	}

	/*
	 * public void setDireccionOrigen(String direccionOrigen) {
	 * this.direccionOrigen = direccionOrigen; }
	 */
	/**
	 * Getter del password de la direccion de origen
	 * 
	 * @return String
	 */
	public String getPasswordOrigen() {
		return this.passwordOrigen;
	}

	/*
	 * public void setPasswordOrigen(String passwordOrigen) {
	 * this.passwordOrigen = passwordOrigen; }
	 */
	/**
	 * Getter de la direccion de origen
	 * 
	 * @return String
	 */
	public String getDireccionFrom() {
		return this.direccionFrom;
	}

	/**
	 * Setter de la direccion de origen
	 * 
	 * @param direccionFrom
	 *            String: direccion de origen
	 */
	public void setDireccionFrom(String direccionFrom) {
		this.direccionFrom = direccionFrom;
	}

	/**
	 * Getter de la direccion de origen
	 * 
	 * @return String: direccion de origen
	 */
	public String getDireccionDestino() {
		return this.direccionDestino;
	}

	/**
	 * Setter de la direccion de destino
	 * 
	 * @param direccionDestino
	 *            String
	 */
	public void setDireccionDestino(String direccionDestino) {
		this.direccionDestino = direccionDestino;
	}

	/**
	 * Getter del contenido del mensaje
	 * 
	 * @return String: contenido del mensaje
	 */
	public String getMessageContent() {
		return this.messageContent;
	}

	/**
	 * Setter del contenido del mensaje
	 * 
	 * @param messageContent
	 *            String
	 */
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	/**
	 * Getter del asunto del mensaje
	 * 
	 * @return String: asunto del mensaje
	 */
	public String getMessageSubject() {
		return this.messageSubject;
	}

	/**
	 * Setter del asunto del mensaje
	 * 
	 * @param messageSubject
	 *            String: asunto del mensaje
	 */
	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}

	/**
	 * Getter del cuerpo del mensaje
	 * 
	 * @return String: cuerpo del mensaje
	 */
	public String getMessageText() {
		return this.messageText;
	}

	/**
	 * Setter del cuerpo del mensaje
	 * 
	 * @param messageText
	 *            String: cuerpo del mensaje
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	/**
	 * Setter de la plantilla HTML a utilizar
	 * 
	 * @param plantillaHTML
	 *            :String plantilla HTML
	 */
	public void setPlantillaHTML(String plantillaHTML) {
		this.plantillaHTML = plantillaHTML;
	}

	/**
	 * Getter de la plantilla HTML a utilizar
	 * 
	 * @return String: plantilla HTML
	 */

	public String getPlantillaHTML() {
		return this.plantillaHTML;
	}

	/**
	 * Setter del Hashmap de reemplazos
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            String
	 * 
	 */

	public void setReemplazos(String key, String value) {
		this.reemplazos.put(key, value);
	}

	@Override()
	public String toString() {
		return "EnviarEmails [direccionOrigen=" + DIRECCIONORIGEN
				+ ", passwordOrigen=" + this.passwordOrigen
				+ ", direccionFrom=" + this.direccionFrom
				+ ", direccionDestino=" + this.direccionDestino
				+ ", messageSubject=" + this.messageSubject + ", messageText="
				+ this.messageText + ", session=" + this.session + "]";
	}

	/*************************** METODOS PUBLICO ****************************************************/
	/**
	 * Envia el email
	 * 
	 * @return boolean
	 */
	public boolean enviar() {
		boolean resul = false;

		try {
			Message message = new MimeMessage(this.session);
			message.setFrom(new InternetAddress(this.direccionFrom));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(this.direccionDestino));
			message.setSubject(MimeUtility.encodeText(this.messageSubject,
					"UTF-8", "B"));

			if (!"".equals(this.messageText)) {
				message.setText(this.messageText);
			} else {
				if (!"".equals(this.plantillaHTML)) {
					try {
						ClassLoader classLoader = this.getClass()
								.getClassLoader();
						this.messageContent = (IOUtils.toString(classLoader
								.getResourceAsStream(this.plantillaHTML),
								"UTF-8"));
					} catch (IOException e) {
						e.printStackTrace();
						resul = false;
					}

					// hacer los reemplazos
					Iterator<String> keySetIterator = this.reemplazos.keySet()
							.iterator();
					while (keySetIterator.hasNext()) {
						String key = keySetIterator.next();
						this.messageContent = this.messageContent.replace(key,
								this.reemplazos.get(key));
					}
				}
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
	 * Genera un String a partir de una plantilla y un hashMap de parametros
	 * 
	 * @param plantilla
	 *            Ruta donde se encuentra la plantilla del email debe estar en
	 *            "src/resources"
	 * @param parametros
	 *            hashMap con variables a sustituir en la plantilla
	 * 
	 * @return String con HTML listo para enviar
	 * @throws IOException
	 */
	public String generarPlantilla(String plantilla,
			HashMap<String, String> parametros) throws IOException {
		String resul = "";

		ClassLoader classLoader = this.getClass().getClassLoader();
		resul = (IOUtils.toString(classLoader.getResourceAsStream(plantilla),
				"UTF-8"));

		Iterator<Map.Entry<String, String>> it = parametros.entrySet()
				.iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> e = it.next();
			resul = resul.replace(e.getKey(), e.getValue());

		}
		return resul;
	}

}
package com.ipartek.formacion.skalada.bean;

/**
 * Bean para contener la informacion de los mensajes a mostrar en FrontEnd
 * @author ur00
 *
 */
public class Mensaje {

	private String tipo;
	private String texto;
	
	//tipos de mensajes
	public static final String MSG_SUCCESS = "success";
	public static final String MSG_INFO    = "info";
	public static final String MSG_WARNING = "warning";
	public static final String MSG_DANGER  = "danger";
	
	/**
	 * Crea una clase mensaje
	 * @param tipo String
	 * @param texto String
	 * @autor Javi
	 */
	public Mensaje(String tipo, String texto) {
		super();
		this.tipo = tipo;
		this.texto = texto;
	}

	/**
	 * Getter del tipo
	 * @return String
	 * @autor Javi
	 */
	public String getTipo() {
		return this.tipo;
	}

	/**
	 * Setter del tipo
	 * @param tipo String
	 * @autor Javi
	 */	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Getter del texto
	 * @return String
	 * @autor Javi
	 */	
	public String getTexto() {
		return this.texto;
	}
	/**
	 * Setter del texto
	 * @param texto String
	 * @autor Javi
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override()
	public String toString() {
		return "Mensaje [tipo=" + this.tipo + ", texto=" + this.texto + "]";
	}
	
	
	
	
}

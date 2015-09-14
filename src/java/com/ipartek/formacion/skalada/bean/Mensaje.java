package com.ipartek.formacion.skalada.bean;

/**
 * Bean para contener la informacion de los mensajes a mostrar en el FRONTEND
 * @author Curso
 *
 */
public class Mensaje {
	private String tipo;
	private String texto;
	
	public static final String MSG_SUCCESS = "alert-success";
	public static final String MSG_WARNING = "alert-warning";
	public static final String MSG_DANGER = "alert-danger";
	public static final String MSG_INFO = "alert-info";
	/**
	 * @param tipo
	 * @param mensaje
	 */
	public Mensaje(String tipo, String texto) {
		super();
		this.setTipo(tipo);
		this.setTexto(texto);
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	@Override
	public String toString() {
		return "Mensaje [tipo=" + tipo + ", texto=" + texto + "]";
	}
	
	
}

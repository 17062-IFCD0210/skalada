package com.ipartek.formacion.skalada.bean;


public class Mensaje {
	private String tipo;
	private String mensaje;
	
	public static final String MSG_SUCCESS = "alert-success";
	public static final String MSG_WARNING = "alert-warning";
	public static final String MSG_DANGER = "alert-danger";
	/**
	 * @param tipo
	 * @param mensaje
	 */
	public Mensaje(String tipo, String mensaje) {
		super();
		this.setTipo(tipo);
		this.setMensaje(mensaje);
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	@Override
	public String toString() {
		return "Mensaje [tipo=" + tipo + ", mensaje=" + mensaje + "]";
	}
	
	
}

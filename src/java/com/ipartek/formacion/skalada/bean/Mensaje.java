package com.ipartek.formacion.skalada.bean;

/**
 * Bean para contener la informacion de los mensajes a mostrar en FrontEnd
 * @author ur00
 *
 */
public class Mensaje {
/**
 * 
 */
	private String tipo;
	/**
	 * 
	 */
	private String texto;
	/**
	 * 
	 */
	// Tipos de mensajes
	public static final String MSG_SUCCESS = "success";
	/**
	 * 
	 */
	public static final String MSG_INFO    = "info";
	/**
	 * 
	 */
	public static final String MSG_WARNING = "warning";
	/**
	 * 
	 */
	public static final String MSG_DANGER  = "danger";
	
	/**
	 * @param sTipo
	 * @param sTexto
	 */
	public Mensaje(final String sTipo, final String sTexto) {
		super();
		this.tipo = sTipo;
		this.texto = sTexto;
	}
/**
 * 
 * @return tipo
 */
	public final String getTipo() {
		return this.tipo;
	}
/**
 * 
 * @param sTipo
 */
	public final void setTipo(final String sTipo) {
		this.tipo = sTipo;
	}
/**
 * 
 * @return texto
 */
	public final String getTexto() {
		return this.texto;
	}
/**
 * 
 * @param sTexto
 */
	public final void setTexto(final String sTexto) {
		this.texto = sTexto;
	}

	@Override
	public final String toString() {
		return "Mensaje [tipo=" + this.tipo + ", texto=" + this.texto + "]";
	}
	
	
	
	
}

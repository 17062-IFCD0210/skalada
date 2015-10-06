package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

import com.ipartek.formacion.skalada.Constantes;
/**
 * 
 * @author Curso
 *
 */
public class Sector implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2537948970066821711L;
	
	/**
	 * Atributos.
	 */
	private int id;
	/**
	 * 
	 */
	private String nombre;
	/**
	 * 
	 */
	private Zona zona;
	/**
	 * 
	 */
	private String imagen; // path + nombre de la imagen a mostrar

	/**
	 * Constructor.
	 * @param zZona
	 * @param sNombre
	 */
	public Sector(final String sNombre, final Zona zZona) {
		super();
		this.setId(-1);
		this.setNombre(sNombre);
		this.setZona(zZona);
		this.setImagen(Constantes.IMG_DEFAULT_SECTOR);
	}

	/**
	 * Getters y Setters.
	 * @return id
	 */
	public final int getId() {
		return this.id;
	}
	/**
	 * 
	 * @param iId
	 */
	public final void setId(final int iId) {
		this.id = iId;
	}
	/**
	 * 
	 * @return nombre
	 */
	public final String getNombre() {
		return this.nombre;
	}
	/**
	 * 
	 * @param sNombre
	 */
	public final void setNombre(final String sNombre) {
		this.nombre = sNombre;
	}
	/**
	 * 
	 * @return zona
	 */
	public final Zona getZona() {
		return this.zona;
	}
	/**
	 * 
	 * @param zZona
	 */
	public final void setZona(final Zona zZona) {
		this.zona = zZona;
	}
/**
 * 
 * @return imagen
 */
	public final String getImagen() {
		return this.imagen;
	}
/**
 * 
 * @param sImagen
 */
	public final void setImagen(final String sImagen) {
		this.imagen = sImagen;
	}

	@Override
	public final String toString() {
		return "Sector [id=" + this.id + ", nombre=" 
				+ this.nombre + ", zona=" + this.zona
				+ ", imagen=" + this.imagen + "]";
	}
	
	

}

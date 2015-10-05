package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

import com.ipartek.formacion.skalada.Constantes;


/**
 * 
 * @author Curso
 *
 */
public class Sector implements Serializable{
	private static final long serialVersionUID = -2537948970066821711L;
	
	/**
	 * Atributos
	 */
	private int id;
	private String nombre;
	private Zona zona;
	private String imagen; // path + nombre de la imagen a mostrar

	/**
	 * 
	 * @param nombre nombre del sector 
	 * @param zona la zona
	 */
	public Sector(String nombre, Zona zona) {
		super();
		this.setId(-1);
		this.setNombre(nombre);
		this.setZona(zona);
		this.setImagen(Constantes.IMG_DEFAULT_SECTOR);
	}

	/**
	 * 
	 * @return id
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * 
	 * @param id 
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * 
	 * @param nombre 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * 
	 * @return la zona
	 */
	public Zona getZona() {
		return this.zona;
	}
	/**
	 * 
	 * @param zona 
	 */
	public void setZona(Zona zona) {
		this.zona = zona;
	}

	/**
	 * 
	 * @return imagen
	 */
	public String getImagen() {
		return this.imagen;
	}

	/**
	 * 
	 * @param imagen string con la imagen
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Sector [id=" + id + ", nombre=" + nombre + ", zona=" + zona
				+ ", imagen=" + imagen + "]";
	}
	
	

}

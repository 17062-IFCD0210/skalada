package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

import com.ipartek.formacion.skalada.Constantes;

public class Sector implements Serializable {
	private static final long serialVersionUID = -2537948970066821711L;
	
	/**
	 * Atributos
	 */
	private int id;
	private String nombre;
	private Zona zona;
	private String imagen; // path + nombre de la imagen a mostrar

	/**
	 * Constructor
	 */
	public Sector(String sNombre, Zona zZona) {
		super();
		this.setId(-1);
		this.setNombre(sNombre);
		this.setZona(zZona);
		this.setImagen(Constantes.IMG_DEFAULT_SECTOR);
	}

	/**
	 * Getters y Setters
	 */
	public int getId() {
		return this.id;
	}
	public void setId(int iId) {
		this.id = iId;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String sNombre) {
		this.nombre = sNombre;
	}
	public Zona getZona() {
		return this.zona;
	}
	public void setZona(Zona zZona) {
		this.zona = zZona;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String sImagen) {
		this.imagen = sImagen;
	}

	@Override
	public String toString() {
		return "Sector [id=" + this.id + ", nombre=" 
				+ this.nombre + ", zona=" + this.zona
				+ ", imagen=" + this.imagen + "]";
	}
	
	

}

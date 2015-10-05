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
	public Sector(String nombre, Zona zona) {
		super();
		this.id = -1;
		this.nombre = nombre;
		this.zona = zona;
		this.imagen = Constantes.IMG_DEFAULT_SECTOR;
	}

	/**
	 * Getters y Setters
	 */
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Zona getZona() {
		return this.zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override()
	public String toString() {
		return "Sector [id=" + this.id + ", nombre=" + this.nombre + ", zona="
				+ this.zona + ", imagen=" + this.imagen + "]";
	}

}

package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

import com.ipartek.formacion.skalada.Constantes;

/**
 * Sectores
 * 
 * @author Curso
 *
 */
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
	 * Constructor de sector
	 *
	 * @param nombre
	 *            String
	 * @param zona
	 *            Zona
	 * @autor Javi
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
	/**
	 * Getter del id
	 * 
	 * @return int
	 * @autor Javi
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Setters del id
	 * 
	 * @param id
	 *            int
	 * @autor Javi
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter del nombre
	 * 
	 * @return String
	 * @autor Javi
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Setter del nombre
	 * 
	 * @param nombre
	 *            String
	 * @autor Javi
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter de la zona
	 * 
	 * @return Zona
	 * @autor Javi
	 */
	public Zona getZona() {
		return this.zona;
	}

	/**
	 * Setter de la zona
	 * 
	 * @param zona
	 *            Zona
	 * @autor Javi
	 */
	public void setZona(Zona zona) {
		this.zona = zona;
	}

	/**
	 * Getter de la imagen
	 * 
	 * @return String
	 * @autor Javi
	 */
	public String getImagen() {
		return this.imagen;
	}

	/**
	 * Setter de la imagen
	 * 
	 * @param imagen
	 *            String
	 * @autor Javi
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override()
	public String toString() {
		return "Sector [id=" + this.id + ", nombre=" + this.nombre + ", zona="
				+ this.zona + ", imagen=" + this.imagen + "]";
	}

}

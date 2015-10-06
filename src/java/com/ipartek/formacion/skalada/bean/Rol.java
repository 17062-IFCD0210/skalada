package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

/**
 * Rol de los usuarios.
 * @author Curso
 *
 */
public class Rol implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7009856922950747575L;
	
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
	private String descripcion;
	
	/**
	 * Constructor.
	 */
	public Rol(String sNombre) {
		super();
		this.setNombre(sNombre);
		this.setId(-1);
	}
	
	/**
	 * Getters y Setters.
	 * @param id
	 * @return resul
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * 
	 * @param iId
	 */
	public void setId(int iId) {
		this.id = iId;
	}
	/**
	 * 
	 * @return
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * 
	 * @param sNombre
	 */
	public void setNombre(String sNombre) {
		this.nombre = sNombre;
	}
	/**
	 * 
	 * @return
	 */
	public String getDescripcion() {
		return this.descripcion;
	}
	/**
	 * 
	 * @param sDescripcion
	 */
	public void setDescripcion(String sDescripcion) {
		this.descripcion = sDescripcion;
	}
	
}

package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

/**
 * Grado de dificultd para las vias.
 * @author Curso
 *
 */
public class Grado implements Serializable {
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
	 * @param sNombre
	 */
	public Grado(final String sNombre) {
		super();
		this.setNombre(sNombre);
		this.setId(-1);
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
	 * @return descripcion
	 */
	public final String getDescripcion() {
		return this.descripcion;
	}
	/**
	 * 
	 * @param sDescripcion
	 */
	public final void setDescripcion(final String sDescripcion) {
		this.descripcion = sDescripcion;
	}
	
}

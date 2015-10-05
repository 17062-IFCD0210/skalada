package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

/**
 *
 * @author Curso
 *
 */
public class TipoEscalada implements Serializable {
	private static final long serialVersionUID = 4866785056536207133L;

	/**
	 * Atributos
	 */
	private int id;
	private String nombre;
	private String descripcion;

	/**
	 * Constructor
	 *
	 * @param nombre
	 */
	public TipoEscalada(String nombre) {
		super();
		this.nombre = nombre;
		this.id = -1;
	}

	/**
	 * Getters y Setters
	 *
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 *
	 * @param int
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 *
	 * @return string
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 *
	 * @param String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 *
	 * @return
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
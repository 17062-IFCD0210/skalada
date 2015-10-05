package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

/**
 * Grado de dificultd para las vias
 *
 * @author Curso
 *
 */
public class Grado implements Serializable {
	private static final long serialVersionUID = -7009856922950747575L;

	/**
	 * Atributos
	 */
	private int id;
	private String nombre;
	private String descripcion;

	/**
	 * Constructor
	 */
	public Grado(String nombre) {
		super();
		this.nombre = nombre;
		this.id = -1;
	}

	/**
	 * Getters y Setters
	 */
	public int getId() {
		return this.id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public final void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public final void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}

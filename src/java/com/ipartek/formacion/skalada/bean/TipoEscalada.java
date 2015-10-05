package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

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
	 */
	public TipoEscalada(String nombre) {
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

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
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
	public TipoEscalada(String sNombre) {
		super();
		this.setNombre(sNombre);;
		this.setId(-1);
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
	public String getDescripcion() {
		return this.descripcion;
	}
	public void setDescripcion(String sDescripcion) {
		this.descripcion = sDescripcion;
	}
		
}
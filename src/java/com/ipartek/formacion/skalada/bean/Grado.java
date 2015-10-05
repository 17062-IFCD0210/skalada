package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

/**
 * Grado de dificultd para las vias
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
	 * 
	 * @param nombre
	 */
	public Grado(String nombre) {
		super();
		this.setNombre(nombre);
		this.setId(-1);
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
	 * @return
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
	 * @return
	 */
	
	/**
	 * 
	 * @return descripcion
	 */
	public String getDescripcion() {
		return this.descripcion;
	}
	/**
	 * 
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}

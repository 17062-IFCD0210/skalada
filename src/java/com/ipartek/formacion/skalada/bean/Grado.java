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
	 * 
	 * @param nombre
	 *            String
	 * @author Javi
	 */
	public Grado(String nombre) {
		super();
		this.nombre = nombre;
		this.id = -1;
	}

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
	 * Getter de la descripcion
	 * 
	 * @return String
	 * @autor Javi
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Setter de la descripcion
	 * 
	 * @param descripcion
	 *            String
	 * @autor Javi
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}

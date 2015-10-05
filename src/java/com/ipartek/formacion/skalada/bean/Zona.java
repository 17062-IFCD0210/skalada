package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Zona implements Serializable {
	private static final long serialVersionUID = 2585448916481098409L;

	/**
	 * Atributos
	 */
	private int id;
	private String nombre;
	private List<Sector> sectores;

	/**
	 * Constructor
	 */
	public Zona(String nombre) {
		super();
		this.id = -1;
		this.nombre = nombre;
		this.sectores = new ArrayList<Sector>();
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

	public List<Sector> getSectores() {
		return this.sectores;
	}

	public void setSectores(List<Sector> sectores) {
		this.sectores = sectores;
	}

}

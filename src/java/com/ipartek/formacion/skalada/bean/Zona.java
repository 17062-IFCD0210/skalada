package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Zona implements Serializable {
	private static final long serialVersionUID = 2585448916481098409L;
	
	/**
	 * Atributos.
	 */
	private int id;
	private String nombre;
	private List<Sector> sectores;

	/**
	 * Constructor.
	 */
	public Zona(String sNombre) {
		super();
		this.setId(-1);
		this.setNombre(sNombre);
		this.setSectores(new ArrayList<Sector>());		
	}

	/**
	 * Getters y Setters.
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
	public List<Sector> getSectores() {
		return this.sectores;
	}
	public void setSectores(List<Sector> sSectores) {
		this.sectores = sSectores;
	}

	
}

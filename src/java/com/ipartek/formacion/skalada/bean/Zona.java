package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Curso
 *
 */
public class Zona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2585448916481098409L;
	
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
	private List<Sector> sectores;

	/**
	 * Constructor.
	 */
	public Zona(final String sNombre) {
		super();
		this.setId(-1);
		this.setNombre(sNombre);
		this.setSectores(new ArrayList<Sector>());		
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
	 * @return sectores
	 */
	public final List<Sector> getSectores() {
		return this.sectores;
	}
	/**
	 * 
	 * @param sSectores
	 */
	public final void setSectores(final List<Sector> sSectores) {
		this.sectores = sSectores;
	}

	
}

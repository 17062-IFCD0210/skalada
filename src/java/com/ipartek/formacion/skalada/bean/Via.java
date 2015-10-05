package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

public class Via implements Serializable {
	private static final long serialVersionUID = 3869513622336875450L;

//**********************************
//****		Atributos			****
//**********************************
	/**
	 * Identificador.
	 */
	private int id;	

	/**
	 * Nombre de la Via.
	 */
	private String nombre;
	
	/**
	 * Nivel de dificultad de la Via.
	 */
	private Grado grado;	
	
	/**
	 * Longitud de la via en metros.
	 */
	private int longitud;
	
	/**
	 * Descripcion.
	 */
	private String descripcion;
	
	/**
	 * Tipo de escalada practicada en la via.
	 */
	private TipoEscalada tipoEscalada;
	
	/**
	 * Sector a la que pertenece la via.
	 */
	private Sector sector;

	
//**********************************
//****		Constructores		****
//**********************************	
	/**
	 * @param id
	 * @param nombre
	 * @param grado
	 * @param longitud
	 * @param tipoEscalada
	 * @param sector
	 */
	public Via(String sNombre, int iLongitud, Grado sGrado, TipoEscalada tTipoEscalada, Sector sSector) {
		super();
		this.setId(-1);
		this.setNombre(sNombre);
		this.setLongitud(iLongitud);
		this.setGrado(sGrado);
		this.setTipoEscalada(tTipoEscalada);
		this.setSector(sSector);
	}

	
	/**
	 * @param nombre
	 */
	public Via(String sNombre) {
		super();
		this.setId(-1);
		this.setNombre(sNombre);
		this.setGrado(null);
		this.setTipoEscalada(null);
		this.setSector(null);
	}
	
	
//**********************************
//****		Getters/Setters		****
//**********************************	
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
	public Grado getGrado() {
		return this.grado;
	}
	public void setGrado(Grado sGrado) {
		this.grado = sGrado;
	}
	public int getLongitud() {
		return this.longitud;
	}
	public void setLongitud(int iLongitud) {
		this.longitud = iLongitud;
	}
	public String getDescripcion() {
		return this.descripcion;
	}
	public void setDescripcion(String sDescripcion) {
		this.descripcion = sDescripcion;
	}
	public TipoEscalada getTipoEscalada() {
		return this.tipoEscalada;
	}
	public void setTipoEscalada(TipoEscalada tTipoEscalada) {
		this.tipoEscalada = tTipoEscalada;
	}
	public Sector getSector() {
		return this.sector;
	}
	public void setSector(Sector sSector) {
		this.sector = sSector;
	}

//**********************************
//****		ToString()			****
//**********************************		
	@Override
	public String toString() {
		return "Via [id=" + this.id + ", nombre=" 
	+ this.nombre + ", grado=" + this.grado
				+ ", longitud=" + this.longitud
				+ ", descripcion=" + this.descripcion
				+ ", tipoEscalada=" + this.tipoEscalada 
				+ ", sector=" + this.sector + "]";
	}
}

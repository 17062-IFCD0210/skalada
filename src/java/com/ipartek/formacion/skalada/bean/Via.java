package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;
/**
 * 
 * @author Curso
 *
 */
public class Via implements Serializable {
	/**
	 * 
	 */
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
	 * @param sNombre
	 * @param sGrado
	 * @param ilongitud
	 * @param tTipoEscalada
	 * @param sSector
	 */
	public Via(final String sNombre, final int iLongitud,
			final Grado sGrado, final TipoEscalada tTipoEscalada,
			final Sector sSector) {
		super();
		this.setId(-1);
		this.setNombre(sNombre);
		this.setLongitud(iLongitud);
		this.setGrado(sGrado);
		this.setTipoEscalada(tTipoEscalada);
		this.setSector(sSector);
	}

	
	/**
	 * @param sNombre
	 */
	public Via(final String sNombre) {
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
	/**
	 * 
	 * @return resul
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
	 * @return resul
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
	 * @return grado
	 */
	public final Grado getGrado() {
		return this.grado;
	}
	/**
	 * 
	 * @param sGrado
	 */
	public final void setGrado(final Grado sGrado) {
		this.grado = sGrado;
	}
	/**
	 * 
	 * @return longitud
	 */
	public final int getLongitud() {
		return this.longitud;
	}
	/**
	 * 
	 * @param iLongitud
	 */
	public final void setLongitud(final int iLongitud) {
		this.longitud = iLongitud;
	}
	/**
	 * 
	 * @return descripcion
	 */
	public final String getDescripcion() {
		return this.descripcion;
	}
	/**
	 * 
	 * @param sDescripcion
	 */
	public final void setDescripcion(final String sDescripcion) {
		this.descripcion = sDescripcion;
	}
	/**
	 * 
	 * @return tipoescalada
	 */
	public final TipoEscalada getTipoEscalada() {
		return this.tipoEscalada;
	}
	/**
	 * 
	 * @param tTipoEscalada
	 */
	public final void setTipoEscalada(final TipoEscalada tTipoEscalada) {
		this.tipoEscalada = tTipoEscalada;
	}
	/**
	 * 
	 * @return sector
	 */
	public final Sector getSector() {
		return this.sector;
	}
	/**
	 * 
	 * @param sSector
	 */
	public final void setSector(final Sector sSector) {
		this.sector = sSector;
	}

//**********************************
//****		ToString()			****
//**********************************		
	@Override
	public final String toString() {
		return "Via [id=" + this.id + ", nombre=" 
	+ this.nombre + ", grado=" + this.grado
				+ ", longitud=" + this.longitud
				+ ", descripcion=" + this.descripcion
				+ ", tipoEscalada=" + this.tipoEscalada 
				+ ", sector=" + this.sector + "]";
	}
}

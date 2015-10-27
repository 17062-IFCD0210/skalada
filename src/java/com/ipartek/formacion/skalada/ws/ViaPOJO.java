package com.ipartek.formacion.skalada.ws;

import java.io.Serializable;

public class ViaPOJO implements Serializable{
	private static final long serialVersionUID = -4421665818926574264L;
	
	private int id;
	private String nombre;
	private int longitud;
	private String descripcion;
	private boolean validado;
	
	private String grado;
	private String tipoEscalada;
	private String sector;
	private String zona;
	private String usuario;

	
	/**
	 * 
	 */
	public ViaPOJO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getGrado() {
		return grado;
	}

	public void setGrado(String grado) {
		this.grado = grado;
	}

	public String getTipoEscalada() {
		return tipoEscalada;
	}

	public void setTipoEscalada(String tipoEscalada) {
		this.tipoEscalada = tipoEscalada;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public boolean isValidado() {
		return validado;
	}

	public void setValidado(boolean validado) {
		this.validado = validado;
	}
	
	
	
	
	
}

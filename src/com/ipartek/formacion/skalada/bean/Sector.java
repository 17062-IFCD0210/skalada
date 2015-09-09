package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

public class Sector implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Atributos
	private int id;
	private String nombre;
	private Zona zona;
	
	//Constructores
	public Sector(String nombre, Zona zona) {
		super();
		this.nombre = nombre;
		this.zona = zona;
	}

	//Getters y Setters
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

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
	//ToString
	@Override
	public String toString() {
		return "Sector [id=" + id + ", nombre=" + nombre + ", zona=" + zona
				+ "]";
	}
	

	
	
}

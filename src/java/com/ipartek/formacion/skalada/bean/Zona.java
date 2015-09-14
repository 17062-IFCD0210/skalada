package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Zona implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//Atributos
	private int id;
	private String nombre;
	private List<Sector> sectores;

	//Constructores
	public Zona(String nombre, List<Sector> sectores) {
		super();
		this.nombre = nombre;
		this.id = -1;
		if(sectores != null) {
			this.sectores = sectores;
		} else {
			this.sectores = new ArrayList<Sector>();
		}
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
	
	public List<Sector> getSectores() {
		return sectores;
	}

	public void setSectores(List<Sector> sectores) {
		this.sectores = sectores;
	}

	//ToString
	@Override
	public String toString() {
		return "Zona [id=" + id + ", nombre=" + nombre + ", sectores="
				+ sectores + "]";
	}
}

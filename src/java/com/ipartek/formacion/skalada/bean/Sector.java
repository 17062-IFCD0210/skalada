package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

import com.ipartek.formacion.skalada.Constantes;

public class Sector implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Atributos
	private int id;
	private String nombre;
	private Zona zona;
	private String imagen; //path + nombre de la imagen a mostrar
	
	//Constructores
	public Sector(String nombre, Zona zona) {
		super();
		this.setNombre(nombre);
		this.setZona(zona);
		this.setId(-1);
		this.setImagen(Constantes.IMG_DEFAULT_SECTOR);
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
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	//ToString
	@Override
	public String toString() {
		return "Sector [id=" + id + ", nombre=" + nombre + ", zona=" + zona
				+ ", imagen=" + imagen + "]";
	}

	

	
	
}

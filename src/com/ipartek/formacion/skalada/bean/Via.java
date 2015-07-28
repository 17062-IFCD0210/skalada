package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

import com.ipartek.formacion.skalada.Grado;

/**
 * 
 * Ruta para escalada en una pared
 * @author Curso
 *
 */
public class Via implements Serializable {
	
	//Atributos
	/**
	 * Identificador
	 */
	private int id; //Identificador
	/**
	 * Nombre de la v�a
	 */
	private String nombre;
	/**
	 * Nivel de dificultad de la v�a
	 */
	private Grado grado; //Pertenece a la ENUM Grado
	/**
	 * Longitud de la v�a en metros
	 */
	private int longitud;
	/**
	 * Descripci�n general de la v�a
	 */
	private String descripcion;

	//Constructores
	public Via() {
		super();
		this.id = -1;
		this.nombre = "";
		this.grado = Grado.NORMAL;
		this.longitud = 0;
		this.descripcion = "";
	}
	
	public Via(String nombre) {
		super();
		this.id = -1;
		this.nombre = nombre;
		this.grado = Grado.NORMAL;
		this.longitud = 0;
		this.descripcion = "";
	}
	
	//Getters y Setters
	//El ID se encarga el ordenador de settearlo
	//public int getId() {
	//	return id;
	//}
	//public void setId(int id) {
	//	this.id = id;
	//}
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
	public Grado getGrado() {
		return grado;
	}
	public void setGrado(Grado grado) {
		this.grado = grado;
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

	//M�todos/Funciones
	@Override
	public String toString() {
		return "Via [id=" + id + ", nombre=" + nombre + ", grado=" + grado
				+ ", longitud=" + longitud + ", descripcion=" + descripcion
				+ "]";
	}

}

package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Grado de dificultd para las vias
 *
 * @author Curso
 *
 */
public class Oferta implements Serializable {
	private static final long serialVersionUID = -7009856922950747575L;

	/**
	 * Atributos
	 */
	private int id;
	private String titulo;
	private String descripcion;
	private float precio;
	private Timestamp fecha_alta;
	private Timestamp fecha_baja;

	/**
	 *
	 */
	public Oferta() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param titulo
	 * @param descripcion
	 * @param precio
	 * @param fecha_alta
	 * @param fecha_baja
	 */
	public Oferta(int id, String titulo, String descripcion, float precio,
			Timestamp fecha_alta, Timestamp fecha_baja) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.fecha_alta = fecha_alta;
		this.fecha_baja = fecha_baja;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return this.precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Timestamp getFecha_alta() {
		return this.fecha_alta;
	}

	public void setFecha_alta(Timestamp fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public Timestamp getFecha_baja() {
		return this.fecha_baja;
	}

	public void setFecha_baja(Timestamp fecha_baja) {
		this.fecha_baja = fecha_baja;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Oferta [id=" + this.id + ", titulo=" + this.titulo
				+ ", descripcion=" + this.descripcion + ", precio="
				+ this.precio + ", fecha_alta=" + this.fecha_alta
				+ ", fecha_baja=" + this.fecha_baja + "]";
	}

}

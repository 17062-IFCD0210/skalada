package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

public class Auxiliar implements Serializable{

		private static final long serialVersionUID = -7009856922950747575L;
		
		private int id;
		private String nombre;
		private String descripcion;
		
		public Auxiliar(String nombre) {
			super();
			this.setNombre(nombre);
			this.setId(-1);
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
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		@Override
		public String toString() {
			return "Auxiliar [id=" + id + ", nombre=" + nombre
					+ ", descripcion=" + descripcion + "]";
		}

}

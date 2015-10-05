package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

import com.ipartek.formacion.skalada.Constantes;

public class Usuario implements Serializable {
	private static final long serialVersionUID = -6253794296547129164L;

	// **********************************
	// **** Atributos ****
	// **********************************
	private int id;
	private String nombre;
	private String email;
	private String password;
	private int validado;
	private Rol rol;
	private String token;

	// **********************************
	// **** Constructor ****
	// **********************************
	/**
	 * @param nombre
	 * @param email
	 * @param password
	 * @param rol
	 */
	public Usuario(String nombre, String email, String password, Rol rol) {
		super();
		this.id = -1;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		if (rol != null) {
			this.rol = rol;
		} else {
			this.rol = new Rol(Constantes.ROLE_USER);
		}
	}

	// **********************************
	// **** Getters/Setters ****
	// **********************************
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getValidado() {
		return this.validado;
	}

	public void setValidado(int validado) {
		this.validado = validado;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	// **********************************
	// **** ToString() ****
	// **********************************
	@Override
	public String toString() {
		return "Usuario [id=" + this.id + ", nombre=" + this.nombre
				+ ", email=" + this.email + ", password=" + this.password
				+ ", validado=" + this.validado + ", rol=" + this.rol + "]";
	}

}

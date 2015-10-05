package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

import com.ipartek.formacion.skalada.Constantes;
/**
 * 
 * @author Curso
 *
 */
public class Usuario implements Serializable {
	private static final long serialVersionUID = -6253794296547129164L;
	
	//**********************************
	//****		Atributos			****
	//**********************************
	private int id;
	private String nombre;
	private String email;
	private String password;
	private int validado;
	private Rol rol;
	private String token;
	
	
	//**********************************
	//****		Constructor			****
	//**********************************
	/**
	 * @param nombre
	 * @param email
	 * @param password
	 * @param rol
	 */
	public Usuario(String sNombre, String sEmail, String sPassword, Rol rRol) {
		super();
		this.setId(-1);
		this.setNombre(sNombre);
		this.setEmail(sEmail);
		this.setPassword(sPassword);
		if (this.rol != null) {
			this.setRol(rRol);
		} else {
			this.setRol(new Rol(Constantes.ROLE_USER));
		}
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
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String sEmail) {
		this.email = sEmail;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String sPassword) {
		this.password = sPassword;
	}
	public int getValidado() {
		return this.validado;
	}
	public void setValidado(int iValidado) {
		this.validado = iValidado;
	}
	public Rol getRol() {
		return this.rol;
	}
	public void setRol(Rol rRol) {
		this.rol = rRol;
	}
	
	public String getToken() {
		return this.token;
	}

	public void setToken(String sToken) {
		this.token = sToken;
	}


	//**********************************
	//****		ToString()			****
	//**********************************	
	@Override
	public String toString() {
		return "Usuario [id=" + this.id + ", nombre=" 
				+ this.nombre + ", email=" + this.email
				+ ", password=" + this.password + ", validado=" + this.validado
				+ ", rol=" + this.rol + "]";
	}
	
}

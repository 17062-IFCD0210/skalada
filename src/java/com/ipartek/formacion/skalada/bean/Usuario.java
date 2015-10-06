package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

import com.ipartek.formacion.skalada.Constantes;
/**
 * 
 * @author Curso
 *
 */
public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6253794296547129164L;
	
	//**********************************
	//****		Atributos			****
	//**********************************
	/**
	 * 
	 */
	private int id;
	/**
	 * 
	 */
	private String nombre;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */
	private int validado;
	/**
	 * 
	 */
	private Rol rol;
	/**
	 * 
	 */
	private String token;
	
	
	//**********************************
	//****		Constructor			****
	//**********************************
	/**
	 * @param sNombre
	 * @param sEmail
	 * @param sPassword
	 * @param rRol
	 */
	public Usuario(final String sNombre, final String sEmail,
			final String sPassword, final Rol rRol) {
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
	 * @return nomrbe
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
	 * @return email
	 */
	public final String getEmail() {
		return this.email;
	}
	/**
	 * 
	 * @param sEmail
	 */
	public final void setEmail(final String sEmail) {
		this.email = sEmail;
	}
	/**
	 * 
	 * @return password
	 */
	public final String getPassword() {
		return this.password;
	}
	/**
	 * 
	 * @param sPassword
	 */
	public final void setPassword(final String sPassword) {
		this.password = sPassword;
	}
	/**
	 * 
	 * @return validado
	 */
	public final int getValidado() {
		return this.validado;
	}
	/**
	 * 
	 * @param iValidado
	 */
	public final void setValidado(final int iValidado) {
		this.validado = iValidado;
	}
	/**
	 * 
	 * @return rol
	 */
	public final Rol getRol() {
		return this.rol;
	}
	/**
	 * 
	 * @param rRol
	 */
	public final void setRol(final Rol rRol) {
		this.rol = rRol;
	}
	/**
	 * 
	 * @return token
	 */
	
	public final String getToken() {
		return this.token;
	}

	/**
	 * 
	 * @param sToken
	 */
	public final void setToken(final String sToken) {
		this.token = sToken;
	}


	//**********************************
	//****		ToString()			****
	//**********************************	
	@Override
	public final String toString() {
		return "Usuario [id=" + this.id + ", nombre=" 
				+ this.nombre + ", email=" + this.email
				+ ", password=" + this.password + ", validado=" + this.validado
				+ ", rol=" + this.rol + "]";
	}
	
}

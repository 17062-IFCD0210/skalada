package com.ipartek.formacion.skalada.bean;

import java.io.Serializable;

import com.ipartek.formacion.skalada.util.Utilidades;

public class Token implements Serializable{
	private static final long serialVersionUID = -6911733211103462642L;
	
	private String email;
	private String token;
	
	private static final int LONGITUD_CADENA = 30;
	
	/**
	 * @param email
	 * @param token
	 */
	public Token(String email) {
		super();
		this.setEmail(email);
		this.setToken(Utilidades.getCadenaAlfanumAleatoria(LONGITUD_CADENA));
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Token [email=" + email + ", token=" + token + "]";
	}
}

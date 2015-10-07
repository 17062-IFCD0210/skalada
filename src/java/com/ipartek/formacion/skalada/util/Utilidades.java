package com.ipartek.formacion.skalada.util;

import java.util.Random;

public class Utilidades {
	
	private static final int TAM_MAX = 255;

	/**
	 * Constructor privado de Utilidades
	 */
	private Utilidades() {
		super();
	}

	public static String getCadenaAlfanumAleatoria (int longitud){
		String cadenaAleatoria = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		int i = 0;
		while ( i < longitud){
			char c = (char)r.nextInt(TAM_MAX);
			if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
				cadenaAleatoria += c;
				i ++;
			}
		}
		return cadenaAleatoria;
	}
	
}

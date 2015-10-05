package com.ipartek.formacion.utilidades;

import java.util.Random;

import com.ipartek.formacion.skalada.Constantes;

/**
 * Utilidades para utilizar durante el proyecto
 *
 * @author Curso
 *
 */
public final class Utilidades {

	/**
	 * Constructor de Utilidades
	 */
	private Utilidades() {

	}

	/**
	 *
	 * @param longitud
	 *            de la cadena
	 * @return String de caracters
	 */
	public static String getCadenaAlfanumAleatoria(int longitud) {
		String cadenaAleatoria = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		int i = 0;
		while (i < longitud) {
			char c = (char) r.nextInt(Constantes.CADENACARACTERES);
			if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
				cadenaAleatoria += c;
				i++;
			}
		}
		return cadenaAleatoria;
	}
}

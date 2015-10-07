package com.ipartek.formacion.skalada.util;

import java.util.Random;
/**
 * 
 * @author Curso
 *
 */
public class Utilidades {

	/**
	 * Nos genera un password aleatorio.
	 * @param longitud
	 * @return resul
	 */
	public static String getCadenaAlfanumAleatoria(final int longitud) {
		String cadenaAleatoria = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		int i = 0;
		while (i < longitud) {
			char c = (char) r.nextInt(255);
			if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
				cadenaAleatoria += c;
				i++;
			}
		}
		return cadenaAleatoria;
	}
}

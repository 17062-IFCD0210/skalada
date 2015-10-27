package com.ipartek.formacion.skalada.ws;

import java.util.List;

/**
 * Interfaz para definir los metodos del WebService basado en SOAP,
 * e implementado con AXIS
 */
public interface ServiceSOAP {
	
	/**
	 * Metodo del WebService que devuelve todas las Vias
	 * @return
	 */
	public List<ViaPOJO> vias();
	
	/**
	 * Metodo del WebService que devuelve una Via por su ID
	 * @param id
	 * @return
	 */
	public ViaPOJO viaDetalle(int id);
	
}

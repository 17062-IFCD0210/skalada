package com.ipartek.formacion.skalada.ws;

import java.util.ArrayList;

import com.ipartek.formacion.skalada.bean.Via;

/**
 * Interfaz para definir los metodos de WS basado en SOAP
 * implementado con AXIS
 * @author Curso
 *
 */
public interface ServiceSOAP {
	
	ArrayList<Via> vias();
	
	public Via viaDetalle(int id);

}

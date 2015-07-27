package com.ipartek.formacion.skalada.modelo;

import java.util.ArrayList;

/**
 * Clase encargada de persistir los objetos de tipo Via {@code Via} en ficheros
 * serializando y des-serializando
 * @author Curso
 *
 */

public class ModeloVia implements Persistable{
	
	private static final String PATH_DATA = "data/via/"; //Creamos la carpeta a mano, luego ser� una tabla de la bbdd
	private static final String PATH_INDEX = "data/via/index.txt"; //Fichero con el �ltimo �ndice guardado
	private static final String PATH_EXTENSION = ".dat"; //extensi�n de ficheros a utilizar
	
	/**
	 * Indentificador con el �ltimo objeto creado, valor inicial 0
	 */
	private static int indice;
	
	/**
	 * Actualiza el �ndice
	 */
	public ModeloVia() { //Constructor ModeloVia
		super();
		
	}

	//Men� Source --> Override/Implements Methods
	@Override
	public int save(Object o) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public Object getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Object> getAll() {
		ArrayList<Object> resul = new ArrayList<Object>();
		//TODO Implementar
		return null;
	}

	@Override
	public boolean update(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Recupera el �ndice actual del fichero de texto {@code PATH_INDEX}
	 * @return �ndice actual, valor inicial 0
	 */
	private int getIndex(){
		return indice;
	}
	
	/**
	 * Incrementa en 1 el �ndice actual del fichero de texto {@code PATH_INDEX}
	 * @return el �ndice incrementado
	 */
	private int updateIndex(){
		return indice;
	}
	
	
}

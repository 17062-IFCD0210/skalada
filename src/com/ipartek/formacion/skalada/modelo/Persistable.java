package com.ipartek.formacion.skalada.modelo;

import java.util.ArrayList;

/**
 * Interfaz para permitir guardar, recuperar, modificar y elimnar beans.
 *
 * Soporta las operaciones basicas de CRUD
 *
 * @author Raul
 *
 */
public interface Persistable {
	/**
	 * Persiste el objeto y lo guarda
	 *
	 * @param o
	 *            {@code Object} a guardar
	 * @return {@code int} identificador del objeto guardado, -1 en caso de
	 *         fallo
	 */
	int save(Object o);

	/**
	 * Recupera Objeto por su identificador
	 *
	 * @param id
	 *            {@code int} identificador del objeto a recuperar
	 * @return {@code Object} objeto encontrado o null en caso contrario
	 */
	Object getById(int id);

	/**
	 * Recupera una coleccion de Objetos
	 *
	 * @return coleccion de objetos, si no existen coleccion vacia
	 */
	ArrayList<Object> getAll();

	/**
	 * Modificado un objeto el cual debe tener un identificador
	 *
	 * @param o
	 *            {@code Object} a modificar
	 * @return true si se modifica bien, false en caso contrario
	 */
	boolean update(Object o);

	/**
	 * Eliminar un objeto por su identificador
	 *
	 * @param id
	 *            {@code int} identificador del objeto
	 * @return true si elimina, false en caso contrario
	 */
	boolean delete(int id);

}

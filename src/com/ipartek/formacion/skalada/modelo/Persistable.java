package com.ipartek.formacion.skalada.modelo;

import java.util.ArrayList;

/**
 * Interfaz para permitir guardar, recuperar, modificar y eliminar beans.
 * Soporta las operaciones basicas de CRUD:
 * 		- Create
 * 		- Read
 * 		- Update
 * 		- Delete
 * 
 * @author Curso
 *
 */
public interface Persistable {

	/**
	 **** 		CREATE		****
	 * Persiste el Objeto y lo guarda
	 * @param o {@code Object} objeto a guardar
	 * @return {@code int} Identificador del objeto guardado, -1 en caso de error
	 */
	int save(Object o);
	
	/**
	 ****		READ I		****
	 * Recupera Objeto por su Identificador
	 * @param id {@code int} identificador del objeto a recuperar
	 * @return {@code Object} objeto encontrado o null en caso contrario
	 */
	Object getById(int id);
	
	/**
	 ****		READ II		****
	 * Recupera una coleccion de  Objetos
	 * @return {@code Object} coleccion de objetos, si no existen coleccion vacia
	 */
	ArrayList<Object> getAll();
	
	/**
	 ****		UPDATE		****
	 * Modificar un Objeto el cual debe tener un identificador definido
	 * @param o {@code Object} Objeto a modificar
	 * @return true si se modificaba bien, false en caso contrario
	 */
	boolean update(Object o);
	
	/**
	 ****		DELETE		****
	 * Eliminar un Objeto por su identificador
	 * @param o {@code int} identificador del recurso a eliminar
	 * @return true si se elimina, false en caso contrario
	 */
	boolean delete(int id);
	
}
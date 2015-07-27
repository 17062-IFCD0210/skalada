package com.ipartek.formacion.skalada.modelo;

import java.util.ArrayList;

/**
<<<<<<< HEAD
 * Interfaz para permitir guardar, recuperar, modificar y eliminar Beans
 * Soporta las operaciones b�sicas de CRUD
 * @author Curso
 *
 */

//UN INTERFACE NO TIENE C�DIGO, LE PASA EL MARR�N AL QUE LO IMPLEMENTA (ModeloVia)
public interface Persistable {
	
	// {@code int} y {@code Object} para que lo ponga en los Tag de code
	/**
	 * Persiste el Objeto y lo guarda
	 * @param o {@code Object} a guardar
	 * @return {@code int} Identificador del objeto guardado, -1 en caso de fallo
	 */
	int save ( Object o );
	
	/**
	 * Recupera Objeto por su identificador
	 * @param id {@code int} identificador del objeto a recuperar
	 * @return {@code Object} objeto encontrado o null en caso contrario
	 */
	Object getById(int id);
	
	/**
	 * Recupera una colecci�n de Objetos
	 * @return colecci�n de objetos, si no existe colecci�n vac�a
	 */
	ArrayList<Object> getAll();
	
	/**
	 * Modifica un objeto el cual debe tener un identificador definido
	 * @param o {@code Object} a modificar
	 * @return true si se modifica bien, false en caso contrario
	 */
	boolean update(Object o);
	
	/**
	 * Eliminar un Objeto por su identificador
	 * @param id {@code int} identificador del objeto a eliminar
	 * @return true si elimina, false en caso contrario
	 */
	boolean delete(int id);
	
	
	
	
	
=======
 * Interfaz para permitir guardar, recuperar, modificar y eliminar beans.
 * Soporta las operaciones basicas de CRUD 
 * @author ur00
 */
public interface Persistable {

	/**
	 * Persiste el Objeto y lo guarda
	 * @param o {@code Obejct} a guardar
	 * @return {@code int} Identificador del objeto guardado, -1 en caso de fallo
	 */
	int save ( Object o);
	
	/**
	 * Recupera Objecto por su Identificador
	 * @param id {@code int} identificador del objeto a recuperar
	 * @return {@code Object} objeto encontrado o null en caso contrario
	 */
	Object getById(int id);
	
	/**
	 * Recupera una coleccion de Objetos
	 * @return coleccion de objetos, si no existen coleccion vacia
	 */
	ArrayList<Object> getAll();
	
	/**
	 * Modificado un Objeto el cual debe tener un identificador definido
	 * @param o {@code Obejct} a modificar
	 * @return true si se modificaba bien, false en caso contrario
	 */
	boolean update (Object o);
	
	/**
	 * Eliminar un objecto por su identificador
	 * @param id {@code int} identificador del objeto a eliminar
	 * @return true si elimina, false en caso contrario
	 */
	boolean delete (int id);
>>>>>>> refs/remotes/origin/master
	
}

package com.ipartek.formacion.skalada.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase especializada en trabajar con la base de datos
 * La usaran los DAOs para abrir y cerrar conexiones
 * @author Curso
 *
 */


public class DataBaseHelper {
	
	//Parametros configuracion base datos
	static final public String DRIVER    = "com.mysql.jdbc.Driver";
	static final public String SERVER    = "localhost";
	static final public String DATA_BASE = "eskalada";
	static final public String USER      = "root";
	static final public String PASS      = "";
	
	//Conexion
	private static Connection con;
	
	/**
	 * Retornar la conexion abierta
	 * implementa un patron singleton
	 * @return {@code Connection} conexion 
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		
		if ( con == null ){
			Class.forName(DRIVER);
			//TODO cambiar parametro
			con = DriverManager.getConnection ("jdbc:mysql://"+ SERVER + "/" + DATA_BASE, USER, PASS);
		}
		return con;
	}
	
	/**
	 * Cierra la conexion, tener cuidado porque al cerrar una conexion con el metodo .close()
	 * no la pone a null
	 * @return
	 */
	public static boolean closeConnection(){
		boolean resul = false;
		try{
			con.close();
			con=null;
			resul = true;
		}catch ( SQLException e){
			con=null;
			e.printStackTrace();
			resul = false;
		}
		return resul;
	}
	
	/**
	 * Crear la Base Datos ejecutando un Script
	 */
	void crear() {
		
	}
	
	/**
	 * Elimina la base de datos con sentencia DROP
	 */
	void eliminar() {
		
	}
	
	/**
	 * Crea las tablas:
	 * <ol>
	 * 	<li>test</li>
	 * </ol>
	 */
	void crearTablas() {
		
	}
	
	/**
	 * Insertar en las tablas un juego de datos para testear
	 */
	void insertarDatosPrueba() {
		
	}

	
}

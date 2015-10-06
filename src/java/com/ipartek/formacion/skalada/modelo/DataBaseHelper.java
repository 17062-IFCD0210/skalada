package com.ipartek.formacion.skalada.modelo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Clase especializada en trabajar con la Base Datos.
 * La usaran los DAOs para abrir y cerrar conexiones
 * @author Curso
 *
 */
public class DataBaseHelper {
	/**
	 * 
	 */
	//Parametros configuracion base datos
	public static final String DRIVER    = "com.mysql.jdbc.Driver";
	/**
	 * 
	 */
	public static final String SERVER    = "localhost";
	/**
	 * 
	 */
	public static final String DATA_BASE = "eskalada";
	/**
	 * 
	 */
	public static final String USER      = "root";
	/**
	 * 
	 */
	public static final String PASS      = "";
	/**
	 * 
	 */
	//Conexion
	private static Connection con;
	
	/**
	 * Metodo para realizar la conexion.
	 * implementa un patron singleton (solo existira un unico objeto)
	 * @return {@code Connection} la conexion abierta
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		
		/* 
		 *  Conexion usando DriverManager
		 * 
		 * 
		if ( con == null ){
			Class.forName(DRIVER);
			con = DriverManager.getConnection 
			("jdbc:mysql://" + SERVER + "/" + DATA_BASE, USER, PASS);
		}
		return con;
		*/
		
		/* Conexion usando DataSource y PoolConexiones */
		
		InitialContext ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/skaladaDB");
		if (ds == null) {
			throw new Exception("Data source no encontrado!");
		} else {	
			con = ds.getConnection();
		}	
		
		if (con == null) {
			throw new Exception("No se ha podido establecer conexion");
		}	
		
		return con;
	}
	
	/**
	 * Metodo para cerrar la conexion.
	 * Cuidado porque al cerrar una conexion 
	 * con el metodo .close() no la pone a null
	 * @return resul
	 */
	public static boolean closeConnection() {
		boolean resul = false;
		try {
			con.close();
			con = null;
			resul = true;
		} catch (SQLException e) {
			con = null;
			e.printStackTrace();
			resul = false;
		}
		return resul;
	}
	
	/**
	 * Crea la Base Datos ejecutando un Script.
	 */
	void crear() {
		
	}
	
	/**
	 * Elimina la Base Datos con sentencia DROP.
	 */
	void eliminar() {
	
	}
	
	/**
	 * Crea las tablas necesarias.
	 * <ol>
	 * 	<li>test</li>
	 * </ol>
	 */
	void crearTablas() {
		
	}
	
	/**
	 * Insertar en las tablas un juego de datos para testear.
	 */
	void insertarDatosPrueba() {
		
	}
	
}
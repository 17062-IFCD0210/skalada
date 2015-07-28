package com.ipartek.formacion.skalada.modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.ipartek.formacion.skalada.bean.Via;

/**
 * Clase encargada de persistir los objetos de tipo {@code Via} 
 * en ficheros serializando y des-serializando
 * @author ur00
 *
 */
public class ModeloVia implements Persistable {
	
	private static final String PATH_DATA_FOLDER = "data/";
	private static final String PATH_DATA_VIA    = PATH_DATA_FOLDER + "via/";
	private static final String PATH_INDEX       = PATH_DATA_VIA + "index.dat";
	private static final String FILE_EXTENSION   = ".dat";
	
	/**
	 * Identificador del ultimo objeto creado, valor inicial 0
	 */
	private static int indice; 
	

	/**
	 * Actualiza el indice
	 */
	public ModeloVia() {
		super();
		
		//Crea la estructura de carpetas si no existe
		File fDtaFolder = new File(PATH_DATA_FOLDER);
		if ( !fDtaFolder.exists()){
			fDtaFolder.mkdir();
		}
		
		File fDataFolderVia = new File(PATH_DATA_VIA);
		if ( !fDataFolderVia.exists()){
			fDataFolderVia.mkdir();
		}
		
		File findex = new File(PATH_INDEX);
		if ( !findex.exists()){
			createIndex();
		}	
		
		//obtiene el indice actual
		getIndex();
	}
	

	public int save(Object o) {
		
		ObjectOutputStream oos = null;
		int resul = -1;
		
		try{
			Via v  = (Via)o;		
			String file = PATH_DATA_VIA + (indice+1) + FILE_EXTENSION;			
			oos = new ObjectOutputStream(new FileOutputStream( file ));
			//guardar objeto
			oos.writeObject(v);
			//actualizar indice
			resul = updateIndex();			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if ( oos != null ){
				try {
					oos.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}		
			
		}			
		return resul;
	}


	public Object getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<Object> getAll() {
		ArrayList<Object> resul = new ArrayList<Object>();
		//TODO implementar
		return resul;
	}

	
	public boolean update(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}	
	
	/**
	 * Recupera el indice actual del fichero de texto {@code PATH_INDEX}
	 * @return indice actual, valor inicial 0
	 */
	private int getIndex(){
		DataInputStream fr = null;		
		try {
			fr = new DataInputStream(new FileInputStream(PATH_INDEX));			
			indice = fr.readInt();			
		} catch ( Exception e) {			
			e.printStackTrace();
		}finally{			
			
			if ( fr != null ){
				try {fr.close();} catch (IOException e) {e.printStackTrace();}
			}
			
		}	
		System.out.println("getIndex: " + indice );
		return indice;
	}
	
	/**
	 * Incrementa en 1 el indice actual del fichero de texto {@code PATH_INDEX}
	 * @return indice incrementado
	 */
	private int updateIndex(){
		System.out.println("updateIndex entrar: " + indice );
		DataOutputStream fr = null;		
		
		indice++;
		try {
			fr = new DataOutputStream(new FileOutputStream(PATH_INDEX));		
			fr.writeInt(indice);	
		} catch ( Exception e) {			
			e.printStackTrace();
		}finally{
		
			if ( fr != null ){
				try {fr.close();} catch (IOException e) {e.printStackTrace();}
			}
		}	
		System.out.println("updateIndex salir: " + indice );
		return indice;		
	}
	
	/**
	 * Crea fichero de indice
	 */
	private void createIndex(){
		
		System.out.println("createIndex");
		DataOutputStream fr = null;		
		indice=0;
		try {
			fr = new DataOutputStream(new FileOutputStream(PATH_INDEX));			
			fr.writeInt(indice);	
		} catch ( Exception e) {			
			e.printStackTrace();
		}finally{						
			if ( fr != null ){
				try {fr.close();} catch (IOException e) {e.printStackTrace();}
			}
			
		}	
		
	}
	
}

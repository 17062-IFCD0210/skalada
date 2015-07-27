package com.ipartek.formacion.skalada.modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.ipartek.formacion.skalada.bean.Via;

/**
 * Clase encargada de persistir los objetos de tipo {@code via} en ficheros
 * serializando y des-serializando
 *
 * @author Raul
 *
 */
public class ModeloVia implements Persistable {
	/**************
	 * CONSTANTES *
	 **************/
	private static final String PATH_DATA = "data/via/";
	private static final String PATH_INDEX = "data/via/index.dat";
	private static final String FILE_EXTENSION = ".dat";

	/*************
	 * ATRIBUTOS *
	 *************/

	/**
	 * Identificador del ultimo objeto creado, valor inicial = 0
	 */
	private static int indice;

	/***************
	 * CONSTRUCTOR *
	 ***************/

	/**
	 * Actualiza el indice
	 */
	public ModeloVia() {
		super();
		File findex = new File(PATH_INDEX);
		if (!findex.exists()) {
			createIndex();
		}
		getIndex();
	}

	/***********
	 * METODOS *
	 ***********/

	@Override
	public int save(Object o) {
		ObjectOutputStream oos = null;
		int resul = -1;

		try {
			Via v = (Via) o;
			String file = PATH_DATA + (indice + 1) + FILE_EXTENSION;
			oos = new ObjectOutputStream(new FileOutputStream(file));

			// Guardar objeto
			oos.writeObject(v);
			resul = updateIndex();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resul;
	}

	@Override
	public Object getById(int id) {
		ObjectInputStream out = null;
		Via v = null;
		String file = PATH_DATA + id + FILE_EXTENSION;
		try {
			out = new ObjectInputStream(new FileInputStream(file));
			v = (Via) out.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return v;
	}

	@Override
	public ArrayList<Object> getAll() {
		ArrayList<Object> resul = new ArrayList<Object>();
		for (int i = 0; i < resul.size(); i++) {

		}
		return resul;
	}

	@Override
	public boolean update(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		String file = PATH_DATA + id + FILE_EXTENSION;
		boolean resul = false;
		File f = new File(file);
		if (f.delete()) {
			resul = true;
		}
		return resul;
	}

	/**
	 * Recupera el indice actual del fichero de texto {@code PATH_INDEX}
	 *
	 * @return indice actual, valor inicial = 0
	 */
	private int getIndex() {
		DataInputStream fr = null;
		try {
			fr = new DataInputStream(new FileInputStream(PATH_INDEX));
			indice = fr.readInt();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("getIndex: " + indice);
		return indice;
	}

	/**
	 * Incrementa en 1 el indice actual del fichero de texto {@code PATH_INDEX}
	 *
	 * @return indice incrementado
	 */
	private int updateIndex() {
		System.out.println("updateIndex entrar: " + indice);
		DataOutputStream fr = null;

		indice++;
		try {
			fr = new DataOutputStream(new FileOutputStream(PATH_INDEX));
			fr.writeInt(indice);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("updateIndex salir: " + indice);
		return indice;
	}

	/**
	 * Crea fichero de indice
	 */
	private void createIndex() {

		System.out.println("createIndex");
		DataOutputStream fr = null;
		indice = 0;
		try {
			fr = new DataOutputStream(new FileOutputStream(PATH_INDEX));
			fr.writeInt(indice);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}
}

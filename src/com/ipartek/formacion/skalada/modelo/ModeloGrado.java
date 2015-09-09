package com.ipartek.formacion.skalada.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.skalada.bean.Grado;

public class ModeloGrado implements Persistable{
	
	private static final String TABLA = "grado";
	private static final String COL_ID = "id";
	private static final String COL_NOMBRE = "nombre";
	private static final String COL_DESCRIPCION = "descripcion";
	
	private static final String SQL_INSERT = "INSERT INTO `" + TABLA + "` (`" + COL_NOMBRE  + "`, `" + COL_DESCRIPCION + "`) VALUES (?,?);";
	private static final String SQL_DELETE = "DELETE FROM `"+ TABLA + "` WHERE  `" + COL_ID + "`=?;";
	
	@Override
	public int save(Object o) {
		int resul = -1;
		PreparedStatement pst = null;
		ResultSet rsKeys = null;
		try{
			Grado grado = (Grado) o;
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_INSERT);
			
			pst.setString(1, grado.getNombre());
			pst.setString(2, grado.getDescripcion());
	    	
	    	if(pst.executeUpdate() == 1) {
	    		rsKeys = pst.getGeneratedKeys();
	    		if(rsKeys.next()) {
	    			resul = rsKeys.getInt(1);
	    		} else {
	    			throw new Exception("No se ha realizado insercion " + SQL_INSERT);
	    		}
	    	}    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rsKeys != null) {
					rsKeys.close();
				}
				if(pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DataBaseHelper.closeConnection();
		}
		return resul;
	}

	@Override
	public Object getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		PreparedStatement pst = null;
		boolean resul = false;
		try {
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_DELETE);
			pst.setInt(1, id);
			
			if(pst.executeUpdate() == 1) {
	    		resul = true;
	    	}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DataBaseHelper.closeConnection();
		}
		
		return resul;
	}

}

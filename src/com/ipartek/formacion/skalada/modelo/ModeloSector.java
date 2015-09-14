package com.ipartek.formacion.skalada.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.skalada.bean.Sector;
import com.ipartek.formacion.skalada.bean.Zona;

public class ModeloSector implements Persistable{
	
	private static final String TABLA_SECTOR = "sector";
	private static final String TABLA_ZONA = "zona";
	private static final String COL_ID = "id";
	
	private static final String COL_NOMBRE = "nombre";
	private static final String COL_ZONA_ID = "id_zona";
	private static final String COL_ZONA_NOMBRE = "zona_nombre";
	
	private static final String SQL_INSERT = "INSERT INTO " + TABLA_SECTOR + " (" + COL_NOMBRE  + ", " + COL_ZONA_ID + ") VALUES (?,?);";
	private static final String SQL_DELETE = "DELETE FROM `"+ TABLA_SECTOR + "` WHERE  `" + COL_ID + "`=?;";
	private static final String SQL_GETBYID = "SELECT s." + COL_ID + ", s." + COL_NOMBRE +", z.nombre as " + COL_ZONA_NOMBRE + ", "+ COL_ZONA_ID +" FROM `" + TABLA_SECTOR + "` s inner join " + TABLA_ZONA + " z on(s." + COL_ZONA_ID + " = z.id) WHERE s." + COL_ID + "= ?";
	private static final String SQL_GETALL = "SELECT s." + COL_ID + ", s." + COL_NOMBRE +", z.nombre as " + COL_ZONA_NOMBRE + ", "+ COL_ZONA_ID +" FROM `" + TABLA_SECTOR + "` s inner join " + TABLA_ZONA + " z on(s." + COL_ZONA_ID + " = z.id);";
	private static final String SQL_UPDATE = "UPDATE " + TABLA_SECTOR + "  SET " + COL_NOMBRE + " = ?, " + COL_ZONA_ID + " = ? WHERE " + COL_ID + " = ?;";
	private static final String SQL_GETALLZONAS = "SELECT " + COL_NOMBRE + " FROM " + TABLA_ZONA + ";";
	private static final String SQL_GETZONASBYID = "SELECT " + COL_NOMBRE + " FROM " + TABLA_ZONA + " WHERE " + COL_ID + "= ?;";
	
	
	@Override
	public int save(Object o) {
		int resul = -1;
		PreparedStatement pst = null;
		ResultSet rsKeys = null;
		if(o != null) {
			try{
				Sector s = (Sector) o;
				Connection con = DataBaseHelper.getConnection();
				pst = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
				
				pst.setString(1, s.getNombre());
				pst.setInt(2, s.getZona().getId());
		    	
		    	if(pst.executeUpdate() == 1) {
		    		rsKeys = pst.getGeneratedKeys();
		    		if(rsKeys.next()) {
		    			resul = rsKeys.getInt(1);
		    			s.setId(resul);
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
		}
		
		return resul;
	}

	@Override
	public Object getById(int id) {
		Sector s = null;
		PreparedStatement pst = null;
		try{
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_GETBYID);
			
			pst.setInt(1, id);
			
	    	ResultSet rs = pst.executeQuery();
	    	
	    	//mapeo resultSet => ArrayList<Grado>	    	
	    	while(rs.next()) {
	    		s = mapeo(rs);
	    	}	
	    	
	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DataBaseHelper.closeConnection();
		}
		
		return s;
	}

	@Override
	public ArrayList<Object> getAll() {
		PreparedStatement pst = null;
		ArrayList<Object> resul = new ArrayList<Object>();
		try{
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_GETALL); 
	    	ResultSet rs = pst.executeQuery ();
	    	
	    	//mapeo resultSet => ArrayList<Sector>	    	
	    	while(rs.next()) {
	    		resul.add(mapeo(rs));
	    	}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
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

	@Override
	public boolean update(Object o) {
		PreparedStatement pst = null;
		boolean resul = false;
		try{
			Sector s = (Sector) o;
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_UPDATE);
			
			pst.setString(1, s.getNombre());
			pst.setInt(2, s.getZona().getId());
			pst.setInt(3, s.getId());
			
	    	if(pst.executeUpdate() == 1) {
	    		resul = true;
	    	}
	    		    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
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
	
	private Sector mapeo(ResultSet rs) throws SQLException{
		Zona z = new Zona(rs.getString("zona_nombre"),null);
		z.setId(rs.getInt("id_zona"));
		z.setNombre(rs.getString("zona_nombre"));
		Sector s = new Sector( rs.getString("nombre"), z );
		s.setId( rs.getInt("id"));
		s.setNombre(rs.getString("nombre"));
		
		
		return s;
	}
	
	
	public int getLastID() {
		int resul = 0;
		Statement st = null;
		ResultSet rs = null;
		try {
			Connection con = DataBaseHelper.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("SELECT MAX(id) as last_id FROM `" + TABLA_SECTOR
					+ "`");

			while (rs.next()) {
				resul = rs.getInt("last_id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			DataBaseHelper.closeConnection();
		}
		return resul;
	}

	public ArrayList<Object> getAllZonas() {
		PreparedStatement pst = null;
		ArrayList<Object> resul = new ArrayList<Object>();
		try{
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_GETALLZONAS); 
	    	ResultSet rs = pst.executeQuery ();
	    	
	    	//mapeo resultSet => ArrayList<Sector>	    	
	    	while(rs.next()) {
	    		resul.add(rs.getString("nombre"));
	    	}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
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
	
	public Object getZonasById(int id) {
		Zona s = null;
		PreparedStatement pst = null;
		try{
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_GETZONASBYID);
			
			pst.setInt(1, id);
			
	    	ResultSet rs = pst.executeQuery();
	    	
	    	//mapeo resultSet => ArrayList<Grado>	    	
	    	while(rs.next()) {
	    		s = new Zona(rs.getString("nombre"), null);
	    	}	
	    	
	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DataBaseHelper.closeConnection();
		}
		
		return s;
	}
	
}

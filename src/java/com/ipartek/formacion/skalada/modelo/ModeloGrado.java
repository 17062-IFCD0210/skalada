package com.ipartek.formacion.skalada.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.skalada.bean.Grado;

public class ModeloGrado implements Persistable{
	
	private static final String TABLA = "grado";
	private static final String COL_ID = "id";
	private static final String COL_NOMBRE = "nombre";
	private static final String COL_DESCRIPCION = "descripcion";
	
	private static final String SQL_INSERT = "INSERT INTO `" + TABLA + "` (`" + COL_NOMBRE  + "`, `" + COL_DESCRIPCION + "`) VALUES (?,?);";
	private static final String SQL_DELETE = "DELETE FROM `"+ TABLA + "` WHERE  `" + COL_ID + "`=?;";
	private static final String SQL_GETBYID = "SELECT * FROM `" + TABLA + "` WHERE `" + COL_ID + "`= ?";
	private static final String SQL_GETALL = "SELECT * FROM `" + TABLA + "` ";
	private static final String SQL_UPDATE = "UPDATE `" + TABLA + "` SET `" + COL_NOMBRE + "` = ?, `" + COL_DESCRIPCION + "` = ? WHERE `" + COL_ID + "` = ?;";
	
	@Override
	public int save(Object o) {
		int resul = -1;
		PreparedStatement pst = null;
		ResultSet rsKeys = null;
		if(o != null) {
			try{
				Grado grado = (Grado) o;
				Connection con = DataBaseHelper.getConnection();
				pst = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
				
				pst.setString(1, grado.getNombre());
				pst.setString(2, grado.getDescripcion());
		    	
		    	if(pst.executeUpdate() == 1) {
		    		rsKeys = pst.getGeneratedKeys();
		    		if(rsKeys.next()) {
		    			resul = rsKeys.getInt(1);
		    			grado.setId(resul);
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
		Grado g = null;
		PreparedStatement pst = null;
		try{
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_GETBYID);
			
			pst.setInt(1, id);
			
	    	ResultSet rs = pst.executeQuery();
	    	
	    	//mapeo resultSet => ArrayList<Grado>	    	
	    	while(rs.next()) {
	    		g = mapeo(rs);
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
		
		return g;
	}

	@Override
	public ArrayList<Object> getAll() {
		PreparedStatement pst = null;
		ArrayList<Object> resul = new ArrayList<Object>();
		try{
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_GETALL); 
	    	ResultSet rs = pst.executeQuery ();
	    	
	    	//mapeo resultSet => ArrayList<Grado>	    	
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
			Grado g = (Grado) o;
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_UPDATE);
			
			pst.setString(1, g.getNombre());
			pst.setString(2, g.getDescripcion());
			pst.setInt(3, g.getId());
			
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
	
	private Grado mapeo(ResultSet rs) throws SQLException{
		
		Grado g = new Grado( rs.getString("nombre") );
		g.setId( rs.getInt("id"));
		g.setDescripcion(rs.getString("descripcion"));;
		
		return g;
	}
	
	
	public int getLastID() {
		int resul = 0;
		Statement st = null;
		ResultSet rs = null;
		try {
			Connection con = DataBaseHelper.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("SELECT MAX(id) as last_id FROM `" + TABLA
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

}

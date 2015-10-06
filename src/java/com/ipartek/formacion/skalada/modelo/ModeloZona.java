package com.ipartek.formacion.skalada.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.skalada.bean.Zona;
/**
 * 
 * @author Curso
 *
 */
public class ModeloZona implements Persistable<Zona> {
	/**
	 * 
	 */
	private static final String TABLA = "zona";
	/**
	 * 
	 */
	private static final String COL_ID = "id";
	/**
	 * 
	 */
	private static final String COL_NOMBRE = "nombre";
	/**
	 * 
	 */
	private static final String SQL_INSERT = "INSERT INTO `" 
				+ TABLA + "` (`" + COL_NOMBRE + "`) VALUES (?);";
	/**
	 * 
	 */
	private static final String SQL_DELETE = "DELETE FROM `" 
				+ TABLA + "` WHERE `" + COL_ID + "`= ?;";
	/**
	 * 
	 */
	private static final String SQL_GETONE = "SELECT * FROM `" 
				+ TABLA + "` WHERE `" + COL_ID + "`= ?;";
	/**
	 * 
	 */
	private static final String SQL_GETALL = "SELECT * FROM " + TABLA;
	/**
	 * 
	 */
	private static final String SQL_UPDATE = "UPDATE `" + TABLA 
			+ "` SET `" + COL_NOMBRE + "`= ? WHERE `" + COL_ID + "`= ? ;";
	/**
	 * 
	 */
	@Override
	public final int save(final Zona zona) {
		int resul = -1;
		PreparedStatement pst = null;
		ResultSet rsKeys = null;
		if (zona != null) {
			try {
				Connection con = DataBaseHelper.getConnection();
				pst = con.prepareStatement(SQL_INSERT,
						Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, zona.getNombre());		
		    	if (pst.executeUpdate() != 1) {
					throw new Exception("No se ha realizado la insercion");
				} else {		
					rsKeys = pst.getGeneratedKeys();
					if (rsKeys.next()) {
						resul = rsKeys.getInt(1);
						zona.setId(resul);
					} else {
						throw new Exception("No se ha podido generar ID");
					}
				}	    		    		
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rsKeys != null) {
						rsKeys.close();
					}
					if (pst != null) {
						pst.close();
					}
					DataBaseHelper.closeConnection();			
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}	
		}
		return resul;
	}

	@Override
	public final Object getById(final int id) {
		Object resul = null;
		PreparedStatement pst = null;
		ResultSet rs = null;		
		try {
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_GETONE);
			pst.setInt(1, id);
	    	rs = pst.executeQuery();	      	   	
	    	while (rs.next()) {
	    		resul = this.mapeo(rs);
	    	}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				DataBaseHelper.closeConnection();			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return resul;		
	}

	@Override
	public final ArrayList<Zona> getAll() {
		ArrayList<Zona> resul = new ArrayList<Zona>();
		PreparedStatement pst = null;
		ResultSet rs = null;		
		try {
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_GETALL);
	    	rs = pst.executeQuery();   	   	
	    	while (rs.next()) {
	    		resul.add(this.mapeo(rs));
	    	}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				DataBaseHelper.closeConnection();			
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}	
		return resul;				
	}

	@Override
	public final boolean update(final Zona zona) {
		boolean resul = false;
		PreparedStatement pst = null;
		if (zona != null) {
			try {
				Connection con = DataBaseHelper.getConnection();
				String sql = SQL_UPDATE;
				pst = con.prepareStatement(sql);
				pst.setString(1, zona.getNombre());
				pst.setInt(2, zona.getId());				
		    	if (pst.executeUpdate() == 1) {
		    		resul = true;	    		
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pst != null) {
						pst.close();
					}				
					DataBaseHelper.
					closeConnection();									
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}	
		}
		return resul;
	}

	@Override
	public final boolean delete(final int id) {
		boolean resul = false;
		PreparedStatement pst = null;
		try {
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_DELETE);
			pst.setInt(1, id);			
			if (pst.executeUpdate() == 1) {
				resul = true;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				DataBaseHelper.closeConnection();	
				return resul;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return resul;
	}
	
	/**
	 * Mapea un ResultSet a Zona.
	 * @param rs
	 * @return resul
	 * @throws SQLException 
	 */
	private Zona mapeo(final ResultSet rs) throws SQLException {
		Zona resul = null;    
		
		resul = new Zona(rs.getString(COL_NOMBRE));
		resul.setId(rs.getInt(COL_ID));
				
		return resul;
	}
	
	/**
	 * 
	 * @param id
	 * @return resul
	 */
	//TODO OBTENER SECTORES DE UNA VIA
	public final ArrayList<Zona> getSectores(final int id) {
		ArrayList<Zona> resul = new ArrayList<Zona>();
		
		
		return resul;
	}

}

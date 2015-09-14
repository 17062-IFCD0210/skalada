package com.ipartek.formacion.skalada.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.skalada.bean.Grado;
import com.ipartek.formacion.skalada.bean.Sector;
import com.ipartek.formacion.skalada.bean.TipoEscalada;
import com.ipartek.formacion.skalada.bean.Via;

public class ModeloVia implements Persistable{
	
	private static final String TABLA_VIA = "via";
	private static final String TABLA_GRADO = "grado";
	private static final String TABLA_SECTOR = "sector";
	
	private static final String COL_ID = "id";
	private static final String COL_NOMBRE = "nombre";
	private static final String COL_LONG = "longitud";
	private static final String COL_DESC = "descripcion";
	private static final String COL_ID_GRADO = "id_grado";
	private static final String COL_ID_TIPO_ESC = "id_tipo_escalada";
	private static final String COL_ID_SECTOR = "id_sector";
	
	private static final String SQL_INSERT = "INSERT INTO via (nombre, longitud, descripcion, id_grado, id_tipo_escalada, id_sector) VALUES (?,?,?,?,?,?);";
	private static final String SQL_DELETE = "DELETE FROM `"+ TABLA_VIA + "` WHERE  `" + COL_ID + "`=?;";
	private static final String SQL_GETBYID = "SELECT v.id, v.nombre, v.longitud, v.descripcion, v.id_grado as gr, v.id_tipo_escalada as te, v.id_sector as sec, g.nombre as nom_grado, t.nombre as nom_tipo_esc , s.nombre as nom_sector"
			+ " FROM via v inner join grado g"
			+ " on(v.id_grado = g.id)"
			+ " inner join tipo_escalada t"
			+ " on(v.id_tipo_escalada = t.id)"
			+ " inner join sector s"
			+ " on(v.id_sector = s.id)"
			+ " where v.id = ?";
	private static final String SQL_GETALL = "SELECT v.id, v.nombre, v.longitud, v.descripcion, v.id_grado as gr, v.id_tipo_escalada as te, v.id_sector as sec, g.nombre as nom_grado, t.nombre as nom_tipo_esc , s.nombre as nom_sector"
			+ " FROM via v inner join grado g"
			+ " on(v.id_grado = g.id)"
			+ " inner join tipo_escalada t"
			+ " on(v.id_tipo_escalada = t.id)"
			+ " inner join sector s"
			+ " on(v.id_sector = s.id);";
	private static final String SQL_UPDATE = "UPDATE via SET nombre= ?, longitud= ?, descripcion= ?, id_grado= ?, id_tipo_escalada= ?, id_sector= ? WHERE id= ?";
	
	
	@Override
	public int save(Object o) {
		int resul = -1;
		PreparedStatement pst = null;
		ResultSet rsKeys = null;
		if(o != null) {
			try{
				Via v = (Via) o;
				Connection con = DataBaseHelper.getConnection();
				pst = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
				
				pst.setString(1, v.getNombre());
				pst.setInt(2, v.getLongitud());
				pst.setString(3, v.getDescripcion());
				pst.setInt(4,v.getGrado().getId());
				pst.setInt(5, v.getTipoEscalada().getId());
				pst.setInt(6, v.getSector().getId());
		    	
		    	if(pst.executeUpdate() == 1) {
		    		rsKeys = pst.getGeneratedKeys();
		    		if(rsKeys.next()) {
		    			resul = rsKeys.getInt(1);
		    			v.setId(resul);
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
		Via v = null;
		PreparedStatement pst = null;
		try{
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_GETBYID);
			
			pst.setInt(1, id);
			
	    	ResultSet rs = pst.executeQuery();
	    	
	    	//mapeo resultSet => ArrayList<Via>	    	
	    	while(rs.next()) {
	    		v = mapeo(rs);
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
		
		return v;
	}

	@Override
	public ArrayList<Object> getAll() {
		PreparedStatement pst = null;
		ArrayList<Object> resul = new ArrayList<Object>();
		try{
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_GETALL); 
	    	ResultSet rs = pst.executeQuery ();
	    	
	    	//mapeo resultSet => ArrayList<Via>	    	
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
			Via v = (Via) o;
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_UPDATE);
			
			pst.setString(1, v.getNombre());
			pst.setInt(2, v.getLongitud());
			pst.setString(3, v.getDescripcion());
			pst.setInt(4,v.getGrado().getId());
			pst.setInt(5, v.getTipoEscalada().getId());
			pst.setInt(6, v.getSector().getId());
			pst.setInt(7, v.getId());
			
			
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
	
	private Via mapeo(ResultSet rs) throws SQLException{
		Grado g = new Grado(rs.getString("nom_grado"));
		g.setId(rs.getInt("gr"));
		TipoEscalada t = new TipoEscalada(rs.getString("nom_tipo_esc"));
		t.setId(rs.getInt("te"));
		Sector s = new Sector( rs.getString("nom_sector"), null );
		s.setId(rs.getInt("sec"));
		Via v = new Via(rs.getString("nombre"),g,rs.getInt("longitud"),t,s);
		v.setId( rs.getInt("id"));
		v.setNombre(rs.getString("nombre"));
		v.setDescripcion(rs.getString("descripcion"));
		v.setGrado(g);
		v.setTipoEscalada(t);
		v.setLongitud(rs.getInt("longitud"));
		v.setSector(s);
		
		return v;
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
}

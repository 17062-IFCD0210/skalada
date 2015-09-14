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
import com.ipartek.formacion.skalada.bean.Zona;

/**
 * Clase encargada de persistir los objetos de tipo {@code Via} 
 * en ficheros serializando y des-serializando
 * @author ur00
 *
 */
public class ModeloVia implements Persistable {
	
	private static final String TABLA_VIA = "via";
	
	private static final String COL_ID = "id";
	private static final String COL_NOMBRE = "nombre";
	private static final String COL_LONGITUD = "longitud";	
	private static final String COL_DESCRIPCION = "descripcion";	
	private static final String COL_GRADO_ID = "id_grado";	
	private static final String COL_TIPO_ESCALADA_ID = "id_tipo_escalada";
	private static final String COL_SECTOR_ID = "id_sector";
	
	private static final String SQL_INSERT = "INSERT INTO `" + TABLA_VIA + "` (`" + COL_NOMBRE + "`, `" + COL_LONGITUD + "`, `" + COL_DESCRIPCION + "`, `" + COL_GRADO_ID + "`, `" + COL_SECTOR_ID + "`, `" + COL_TIPO_ESCALADA_ID + "`) VALUES (?,?,?,?,?,?);";	
	private static final String SQL_GETALL = "SELECT v.id, v.nombre, v.longitud, v.descripcion, v.id_grado, g.nombre AS nombre_grado, "
											+ "v.id_tipo_escalada, te.nombre AS nombre_tipo_escalada, "
											+ "v.id_sector, s.nombre AS nombre_sector, "
											+ "s.id_zona, z.nombre AS nombre_zona "
											+ "FROM via AS v "
											+ "INNER JOIN grado AS g ON (v.id_grado = g.id) "
											+ "INNER JOIN tipo_escalada AS te ON (v.id_tipo_escalada = te.id) "
											+ "INNER JOIN sector AS s ON (v.id_sector = s.id) "
											+ "INNER JOIN zona AS z ON (s.id_zona = z.id)";
	private static final String SQL_GETONE = SQL_GETALL + "WHERE v.id = ?";
	private static final String SQL_UPDATE = "UPDATE `" + TABLA_VIA + "` SET `" + COL_NOMBRE + "`= ? , `" + COL_LONGITUD + "`= ?, `" + COL_DESCRIPCION + "`= ?, `" + COL_SECTOR_ID + "`= ?, `" + COL_LONGITUD + "`= ?, `" + COL_TIPO_ESCALADA_ID + "`= ? WHERE `" + COL_ID + "`= ? ;";
	private static final String SQL_DELETE = "DELETE FROM `" + TABLA_VIA + "` WHERE `" + COL_ID + "`= ?;";
	

	@Override
	public int save(Object o) {
		int resul = -1;
		Via v = null;	
		PreparedStatement pst = null;
		ResultSet rsKeys = null;
		if(o != null){
			try{
				v = (Via)o;
				Connection con = DataBaseHelper.getConnection();
				pst = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, v.getNombre());
				pst.setInt(2, v.getLongitud());
				pst.setString(3, v.getDescripcion());
				pst.setInt(4, v.getGrado().getId());
				pst.setInt(5, v.getTipoEscalada().getId());
				pst.setInt(6, v.getSector().getId());				
		    	if ( pst.executeUpdate() != 1 ){
					throw new Exception("No se ha realizado la insercion");
				} else {		
					rsKeys = pst.getGeneratedKeys();
					if (rsKeys.next()) {
						resul = rsKeys.getInt(1);
						v.setId(resul);
					} else {
						throw new Exception("No se ha podido generar ID");
					}
				}	    		    		
			} catch (Exception e){
				e.printStackTrace();
			} finally {
				try {
					if(rsKeys != null){
						rsKeys.close();
					}
					if(pst != null){
						pst.close();
					}
					DataBaseHelper.closeConnection();			
				}catch(Exception e){
					e.printStackTrace();
				}			
			}	
		}
		return resul;
	}

	@Override
	public Object getById(int id) {
		Object resul = null;
		PreparedStatement pst = null;
		ResultSet rs = null;		
		try{
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_GETONE);
			pst.setInt(1, id);
	    	rs = pst.executeQuery();	      	   	
	    	while(rs.next()){
	    		resul = mapeo(rs);
	    	}	
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(pst != null){
					pst.close();
				}
				DataBaseHelper.closeConnection();			
			}catch(Exception e){
				e.printStackTrace();
			}
		}		
		return resul;		
	}

	@Override
	public ArrayList<Object> getAll() {
		ArrayList<Object> resul = new ArrayList<Object>();
		PreparedStatement pst = null;
		ResultSet rs = null;		
		try{
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_GETALL);
	    	rs = pst.executeQuery();   	   	
	    	while(rs.next()){
	    		resul.add(mapeo(rs));
	    	}	
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(pst != null){
					pst.close();
				}
				DataBaseHelper.closeConnection();			
			}catch(Exception e){
				e.printStackTrace();
			}			
		}	
		return resul;				
	}

	@Override
	public boolean update(Object o) {
		boolean resul = false;
		Via v = null;
		PreparedStatement pst = null;
		if (o != null){
			try{
				v = (Via)o;
				Connection con = DataBaseHelper.getConnection();
				String sql = SQL_UPDATE;
				pst = con.prepareStatement(sql);
				pst.setString(1, v.getNombre());
				pst.setInt(2, v.getLongitud());
				pst.setString(3, v.getDescripcion());
				pst.setInt(4, v.getGrado().getId());
				pst.setInt(5, v.getTipoEscalada().getId());
				pst.setInt(6, v.getSector().getId());	
				pst.setInt(7, v.getId());				
		    	if ( pst.executeUpdate() == 1 ){
		    		resul = true;	    		
				}
			} catch (Exception e){
				e.printStackTrace();
			} finally {
				try {
					if(pst != null){
						pst.close();
					}				
					DataBaseHelper.closeConnection();									
				}catch(Exception e){
					e.printStackTrace();
				}			
			}	
		}
		return resul;
	}

	@Override
	public boolean delete(int id) {
		boolean resul = false;
		PreparedStatement pst = null;
		try{
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_DELETE);
			pst.setInt(1, id);			
			if ( pst.executeUpdate() == 1 ){
				resul = true;
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(pst != null){
					pst.close();
				}
				DataBaseHelper.closeConnection();	
				return resul;
			}catch(Exception e){
				e.printStackTrace();
			}
		}		
		return resul;
	}
	
	/**
	 * Mapea un ResultSet a Via
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	private Via mapeo (ResultSet rs) throws SQLException{
		Via resul = null; 
		
		Grado grado = new Grado(rs.getString("nombre_grado"));
		grado.setId(rs.getInt(COL_GRADO_ID));
		
		TipoEscalada tipoEscalada = new TipoEscalada(rs.getString("nombre_tipo_escalada"));
		tipoEscalada.setId(rs.getInt(COL_SECTOR_ID));
		
		Zona zona = new Zona( rs.getString("nombre_zona") );
		zona.setId(rs.getInt("id_zona"));
		
		Sector sector = new Sector(rs.getString("nombre_sector"), zona);
		sector.setId(rs.getInt(COL_SECTOR_ID));
		
		resul = new Via(rs.getString(COL_NOMBRE), rs.getInt(COL_LONGITUD), grado, tipoEscalada, sector);
		resul.setId( rs.getInt(COL_ID));
		resul.setDescripcion(rs.getString(COL_DESCRIPCION));		
		
		return resul;
	}
	
	
	

}

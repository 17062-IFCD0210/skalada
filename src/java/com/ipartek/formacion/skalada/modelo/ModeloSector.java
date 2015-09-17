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
	
	private static final String COL_ID = "id";
	private static final String COL_NOMBRE = "nombre";
	private static final String COL_IMAGEN = "imagen";
	private static final String COL_ZONA_ID = "id_zona";
	private static final String COL_ZONA_NOMBRE = "nombre_zona";
	
	private static final String SQL_INSERT = "INSERT INTO `" + TABLA_SECTOR + "` (`" + COL_NOMBRE + "`, `" + COL_IMAGEN + "`, `" + COL_ZONA_ID + "`) VALUES (?,?,?);";
	private static final String SQL_DELETE = "DELETE FROM `" + TABLA_SECTOR + "` WHERE `" + COL_ID + "`= ?;";
	private static final String SQL_GETALL = "SELECT  s.id, s.nombre, id_zona, z.nombre AS nombre_zona, s.imagen "
											+ "FROM sector AS s "
											+ "INNER JOIN zona AS z ON (s.id_zona = z.id)";
	private static final String SQL_GETONE = SQL_GETALL + "WHERE s.id = ?";
	private static final String SQL_UPDATE = "UPDATE `" + TABLA_SECTOR + "` SET `" + COL_NOMBRE + "`= ? , `" + COL_IMAGEN + "`= ? , `" + COL_ZONA_ID + "`= ? WHERE `" + COL_ID + "`= ? ;";
	
	private static final String SQL_GETALL_BY_ZONA = "SELECT `id`, `nombre`, `imagen` FROM sector WHERE `id_zona` = ?";
	
	@Override
	public int save(Object o) {
		int resul = -1;
		Sector g = null;	
		PreparedStatement pst = null;
		ResultSet rsKeys = null;
		if(o != null){
			try{
				g = (Sector)o;
				Connection con = DataBaseHelper.getConnection();
				pst = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, g.getNombre());
				pst.setString(2, g.getImagen());
				pst.setInt(3, g.getZona().getId());		
		    	if ( pst.executeUpdate() != 1 ){
					throw new Exception("No se ha realizado la insercion");
				} else {		
					rsKeys = pst.getGeneratedKeys();
					if (rsKeys.next()) {
						resul = rsKeys.getInt(1);
						g.setId(resul);
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
		Sector s = null;
		PreparedStatement pst = null;
		if (o != null){
			try{
				s = (Sector)o;
				Connection con = DataBaseHelper.getConnection();
				String sql = SQL_UPDATE;
				pst = con.prepareStatement(sql);
				pst.setString(1, s.getNombre());
				pst.setString(2, s.getImagen());
				pst.setInt(3, s.getZona().getId());
				pst.setInt(4, s.getId());				
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
	 * Mapea un ResultSet a Sector
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	private Sector mapeo (ResultSet rs) throws SQLException{
		Sector resul = null;    

		Zona zona = new Zona( rs.getString(COL_ZONA_NOMBRE) );
		zona.setId(rs.getInt(COL_ZONA_ID));
		
		resul = new Sector( rs.getString(COL_NOMBRE), zona );
		resul.setId( rs.getInt(COL_ID));
		resul.setImagen(rs.getString(COL_IMAGEN));
		
		return resul;
	}
	
	
	/**
	 * Ontiene todos los sectores de una {@code Zona}, 
	 * <b> Cuidado: getZona() retorna <code>null</code>, se supone que ya la conocemos </b>
	 * @param id_zona {@code int} identificador de la {@code Zona}
	 * @return {@code ArrayList<Sector>} coleccion de sectores de la {@code Zona},
	 * 			si no existe ninguno coleccion inicializada con new().
	 */
	public ArrayList<Sector> getAllByZona (int id_zona){
		ArrayList<Sector> resul = new ArrayList<Sector>();		
		PreparedStatement pst = null;
		ResultSet rs = null;		
		try{
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_GETALL_BY_ZONA);
			pst.setInt(1, id_zona);	
	    	rs = pst.executeQuery();  
	    	Sector sector = null;
	    	while(rs.next()){	    		
	    		sector = new Sector( rs.getString(COL_NOMBRE), null );
	    		sector.setId( rs.getInt(COL_ID));
	    		sector.setImagen(rs.getString(COL_IMAGEN));
	    		resul.add(sector);
	    		sector = null;
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

}

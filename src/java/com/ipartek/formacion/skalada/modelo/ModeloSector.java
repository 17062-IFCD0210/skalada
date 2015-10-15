package com.ipartek.formacion.skalada.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ipartek.formacion.skalada.Constantes;
import com.ipartek.formacion.skalada.bean.Rol;
import com.ipartek.formacion.skalada.bean.Sector;
import com.ipartek.formacion.skalada.bean.Usuario;
import com.ipartek.formacion.skalada.bean.Zona;

public class ModeloSector implements Persistable<Sector> {
	
	private static final Logger LOG = Logger.getLogger(ModeloSector.class);

	private static final String SQL_INSERT = "INSERT INTO `sector` (`nombre`, `id_zona` , `imagen`, `validado`, `id_usuario`) VALUES (?,?,?,?,?);";
	private static final String SQL_DELETE = "DELETE FROM `sector` WHERE `id`= ?;";
	private static final String SQL_GETALL = "SELECT  s.id, s.nombre, s.imagen, s.validado, "
									+ "s.id_zona AS zona_id, z.nombre AS zona_nombre, "
									+ "s.id_usuario AS usuario_id, u.nombre AS usuario_nombre, u.email AS usuario_email, u.password AS usuario_password, "
									+ "r.id AS rol_id, r.nombre AS rol_nombre "
									+ "FROM sector AS s "
									+ "INNER JOIN zona AS z ON (s.id_zona = z.id) "
									+ "INNER JOIN usuario AS u ON (s.id_usuario = u.id) "
									+ "INNER JOIN rol AS r ON (u.id_rol = r.id)";
	private static final String SQL_GETONE = SQL_GETALL + " WHERE s.id = ?";
	private static final String SQL_UPDATE = "UPDATE `sector` SET `nombre`= ? , `id_zona`= ? , `imagen`= ? , `validado`= ? , `id_usuario` = ? WHERE `id`= ? ";
	
	private static final String SQL_UPDATE_AUTORIZACION = SQL_UPDATE + " AND `id_usuario` = ? ;";
	
	private static final String SQL_GETALL_BY_USER = SQL_GETALL + " AND s.id_usuario = ?";
	
	private static final String SQL_GETALL_BY_ZONA = SQL_GETALL + " WHERE `id_zona` = ?";
	private static final String SQL_CANT_SECTORES_PUBLICADOS = "SELECT COUNT(`id`) as `sectores` FROM `SECTOR`;";

	
	
	@Override()
	public int save(Sector sector) {
		int resul = -1;
		PreparedStatement pst = null;
		ResultSet rsKeys = null;
		if (sector != null) {
			try {
				Connection con = DataBaseHelper.getConnection();
				pst = con.prepareStatement(SQL_INSERT,
						Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, sector.getNombre());
				pst.setInt(2, sector.getZona().getId());
				pst.setString(3, sector.getImagen());
				if(sector.isValidado()){
					pst.setInt(4, Constantes.VALIDADO);
				} else {
					pst.setInt(4, Constantes.NO_VALIDADO);
				}
				pst.setInt(5, sector.getUsuario().getId());
				if (pst.executeUpdate() != 1) {
					throw new Exception("No se ha realizado la insercion");
				} else {
					rsKeys = pst.getGeneratedKeys();
					if (rsKeys.next()) {
						resul = rsKeys.getInt(1);
						sector.setId(resul);
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

	@Override()
	public Object getById(int id) {
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

	@Override()
	public ArrayList<Sector> getAll(Usuario usuario) {
		ArrayList<Sector> resul = new ArrayList<Sector>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Connection con = DataBaseHelper.getConnection();
			if(usuario != null){
				if (Constantes.ROLE_ID_ADMIN == usuario.getRol().getId()){
					pst = con.prepareStatement(SQL_GETALL);
				} else{
					pst = con.prepareStatement(SQL_GETALL_BY_USER);
					pst.setInt(1, usuario.getId());
				}
			}else{
				throw new Exception("Peticion sin autorizacion: Usuario nulo");
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				resul.add(this.mapeo(rs));
			}
		} catch (Exception e) {
			LOG.error("Peticion sin autorizacion: Usuario nulo");
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
				LOG.error("Excepcion cerrando recursos");
				e.printStackTrace();
			}
		}
		return resul;
	}

	@Override()
	public boolean update(Sector sector) {
		boolean resul = false;
		PreparedStatement pst = null;
		if (sector != null) {
			try {
				Connection con = DataBaseHelper.getConnection();
				String sql = SQL_UPDATE;
				pst = con.prepareStatement(sql);
				pst.setString(1, sector.getNombre());
				pst.setInt(2, sector.getZona().getId());
				pst.setString(3, sector.getImagen());
				if(sector.isValidado()){
					pst.setInt(4, Constantes.VALIDADO);
				} else {
					pst.setInt(4, Constantes.NO_VALIDADO);
				}
				pst.setInt(5, sector.getUsuario().getId());
				pst.setInt(6, sector.getId());				
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return resul;
	}
	
	/**
	 * Modificar Sectro comprobando autorizacion del Usuario
	 * @param sector {@code Sector} a modificar
	 * @param usuario {@code Usuario} logueado en session
	 * @return true si modifica, false en caso contrario
	 */
	public boolean update(Sector sector, Usuario usuario) {
		boolean resul = false;
		PreparedStatement pst = null;
		if (sector != null) {
			try {
				Connection con = DataBaseHelper.getConnection();
				String sql = SQL_UPDATE;
				if(!usuario.isAdmin()){
					sql = SQL_UPDATE_AUTORIZACION;
				}
				pst = con.prepareStatement(sql);
				pst.setString(1, sector.getNombre());
				pst.setInt(2, sector.getZona().getId());
				pst.setString(3, sector.getImagen());
				if(sector.isValidado()){
					pst.setInt(4, Constantes.VALIDADO);
				} else {
					pst.setInt(4, Constantes.NO_VALIDADO);
				}
				pst.setInt(5, sector.getUsuario().getId());
				pst.setInt(6, sector.getId());
				//Comprobar que le pertenezca el Sector al usuario
				if(!usuario.isAdmin()){
					pst.setInt(7, usuario.getId());;
				}
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return resul;
	}

	@Override()
	public boolean delete(int id) {
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
	 * Mapea un ResultSet a Sector
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Sector mapeo(ResultSet rs) throws SQLException {
		Sector resul = null;

		Zona zona = new Zona(rs.getString("zona_nombre"));
		zona.setId(rs.getInt("zona_id"));
		
		Rol rol = new Rol(rs.getString("rol_nombre"));
		rol.setId(rs.getInt("rol_id"));
		Usuario user = new Usuario(
							rs.getString("usuario_nombre"),
							rs.getString("usuario_email"),
							rs.getString("usuario_password"),
							rol);
		user.setId(rs.getInt("usuario_id"));

		resul = new Sector(rs.getString("nombre"), zona);
		resul.setId(rs.getInt("id"));
		resul.setImagen(rs.getString("imagen"));
		
		if(rs.getInt("validado") == Constantes.VALIDADO){
			resul.setValidado(true);
		}
		
		resul.setUsuario(user);

		return resul;
	}

	/**
	 * Obtiene todos los sectores de una {@code Zona}, <b> Cuidado: getZona()
	 * retorna <code>null</code>, se supone que ya la conocemos </b>
	 * 
	 * @param id_zona
	 *            {@code int} identificador de la {@code Zona}
	 * @return ArrayList<Sector> coleccion de sectores de la {@code Zona}, si no
	 *         existe ninguno coleccion inicializada con new()
	 *
	 */
	public ArrayList<Sector> getAllByZona(int id_zona) {
		ArrayList<Sector> resul = new ArrayList<Sector>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_GETALL_BY_ZONA);
			pst.setInt(1, id_zona);
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

	public static int cantSectoresPublicados() {
		int resul = 0;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_CANT_SECTORES_PUBLICADOS);
			rs = pst.executeQuery();
			while (rs.next()) {
				resul = rs.getInt("sectores");
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

}

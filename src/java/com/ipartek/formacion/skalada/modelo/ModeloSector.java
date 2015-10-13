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

	private static final String TABLA_SECTOR = "sector";
	private static final String TABLA_ZONA = "zona";
	private static final String COL_ID = "id";
	private static final String COL_NOMBRE = "nombre";
	private static final String COL_ZONA_ID = "zona_id";
	private static final String COL_ZONA_NOMBRE = "zona_nombre";
	private static final String COL_IMAGEN = "imagen";

	private static final String SQL_INSERT = "INSERT INTO `" + TABLA_SECTOR
			+ "` (`" + COL_NOMBRE + "`, `" + COL_ZONA_ID + "` , `" + COL_IMAGEN
			+ "`) VALUES (?,?,?);";
	private static final String SQL_DELETE = "DELETE FROM `" + TABLA_SECTOR
			+ "` WHERE `" + COL_ID + "`= ?;";

	// Se deberían poner los campos entre comillas ``
	private static final String SQL_GETALL = "select s.nombre, "
											+ "s.id, "
											+ "s.imagen, "
											+ "s.validado, "
											+ "z.nombre as zona_nombre, "
											+ "z.id as zona_id, "
											+ "r.nombre as rol_nombre, "
											+ "r.id as rol_id, "
											+ "u.nombre as usuario_nombre, "
 + "u.password as usuario_pass, "
											+ "u.email as usuario_email, "
											+ "u.id as usuario_id "
											+ "FROM sector as s "
											+ "INNER JOIN zona as z ON s.id_zona = z.id "
											+ "INNER JOIN usuario as u ON s.id_usuario = u.id "
 + "INNER JOIN rol as r ON u.id_rol = r.id";
	private static final String SQL_GETONE = SQL_GETALL + " WHERE s.id = ?;";
	// "SELECT  s.id, s.imagen, s.nombre, s.id_zona as zona_id, z.nombre AS zona_nombre FROM sector AS s INNER JOIN zona AS z ON (s.id_zona = z.id) WHERE s.id = ?";

	private static final String SQL_GETALL_BY_USER = SQL_GETALL + " AND s.id_usuario = ?;";
	private static final String SQL_UPDATE = "UPDATE `validado`, `id_usuario`, `" + TABLA_SECTOR + "` SET `validado`= ? , `id_usuario`= ? , `"
			+ COL_NOMBRE + "`= ? , `" + COL_ZONA_ID + "`= ? , `"
			+ COL_IMAGEN + "`= ? WHERE `" + COL_ID + "`= ? ;";

	private static final String SQL_GETALL_BY_ZONA = "select `id`,`nombre`,`imagen` from `sector` where `id_zona` = ?";

	private static final String SQL_SECTORES_PUBLICADOS = "SELECT COUNT(`id`) as `sectores` FROM `SECTOR`;";

	@Override()
	public int save(Sector s) {
		int resul = -1;
		Sector g = null;
		PreparedStatement pst = null;
		ResultSet rsKeys = null;
		if (s != null) {
			try {
				g = s;
				Connection con = DataBaseHelper.getConnection();
				pst = con.prepareStatement(SQL_INSERT,
						Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, g.getNombre());
				pst.setInt(2, g.getZona().getId());
				pst.setString(3, g.getImagen());
				if (pst.executeUpdate() != 1) {
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
				resul = mapeo(rs);
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
	// Cogemos todos por el usuario que esté en sesión
	public ArrayList<Sector> getAll(Usuario usuario) {
		ArrayList<Sector> resul = new ArrayList<Sector>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Connection con = DataBaseHelper.getConnection();
			if(usuario != null){
				if (Constantes.ROLE_ID_ADMIN == usuario.getRol().getId()){
					pst = con.prepareStatement(SQL_GETALL);
				}else{
					pst = con.prepareStatement(SQL_GETALL_BY_USER);
					pst.setInt(1, usuario.getId()); // Le damos el id del usuario en sesión
				}
			}else{
				throw new Exception("Petición sin autorización: Usuario nulo");
			}

			rs = pst.executeQuery();
			while (rs.next()) {
				resul.add(mapeo(rs));
			}
		} catch (Exception e) {
			LOG.error("Petición sin autorización: Usuario nulo");
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
				LOG.error("Excepción cerrando recursos");
				e.printStackTrace();
			}
		}
		return resul;
	}

	@Override()
	public boolean update(Sector s) {
		boolean resul = false;
		PreparedStatement pst = null;
		if (s != null) {
			try {
				s = s;
				Connection con = DataBaseHelper.getConnection();
				String sql = SQL_UPDATE;
				pst = con.prepareStatement(sql);
				pst.setString(1, s.getNombre());
				pst.setInt(2, s.getZona().getId());
				pst.setString(3, s.getImagen());
				pst.setInt(4, s.getId());
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

		// Zona
		Zona zona = new Zona(rs.getString("zona_nombre"));
		zona.setId(rs.getInt("zona_id"));

		// Usuario
		Rol rol = new Rol(rs.getString("rol_nombre"));
		rol.setId(rs.getInt("rol_id"));
		Usuario user = new Usuario(rs.getString("usuario_nombre"), rs.getString("usuario_email"), rs.getString("usuario_pass"), rol);
		user.setId(rs.getInt("usuario_id"));

		// Sector
		resul = new Sector(rs.getString("nombre"), zona);
		resul.setId(rs.getInt("id"));
		resul.setImagen(rs.getString("imagen"));

		if (rs.getInt("validado") == Constantes.VALIDADO) {
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

			Sector sector = null;
			while (rs.next()) {
				sector = new Sector(rs.getString("nombre"), null);
				sector.setId(rs.getInt("id"));
				sector.setImagen(rs.getString("imagen"));
				resul.add(sector);
				sector = null;
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

	public static int sectoresPublicados() {
		int resul = 0;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_SECTORES_PUBLICADOS);
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

package com.ipartek.formacion.skalada.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ipartek.formacion.skalada.bean.Token;

public class ModeloToken{
	
	private static final String TABLA = "usuario";
	
	private static final String SQL_UPDATE = "UPDATE `" + TABLA + "` SET `token`= ? WHERE `email`= ? ;";
	private static final String SQL_GETONE =  "SELECT * FROM `" + TABLA + "` WHERE `email`= ?;";

	
	public Token getByEmail(String email) {
		Token resul = null;
		PreparedStatement pst = null;
		ResultSet rs = null;		
		try{
			Connection con = DataBaseHelper.getConnection();
			pst = con.prepareStatement(SQL_GETONE);
			pst.setString(1, email);
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

	public boolean update(Object o) {
		boolean resul = false;
		Token token = null;
		PreparedStatement pst = null;
		if (o != null){
			try{
				token = (Token)o;
				Connection con = DataBaseHelper.getConnection();
				String sql = SQL_UPDATE;
				pst = con.prepareStatement(sql);
				pst.setString(1, token.getToken());
				pst.setString(2, token.getEmail());				
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
	
	/**
	 * Mapea un ResultSet a Token
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	private Token mapeo (ResultSet rs) throws SQLException{
		Token resul = null;    
		
		resul = new Token( rs.getString("email") );
		resul.setToken(rs.getString("token"));
				
		return resul;
	}
}

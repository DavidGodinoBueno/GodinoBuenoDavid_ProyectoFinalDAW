package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexiones.Conexion;
import entidades.Comentarios;

public class DaoComentarios {
	
	Conexion c = new Conexion();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public void escribirComentarios(Comentarios comentario) throws SQLException, Exception {
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String comentar = "INSERT INTO COMENTARIOS(DESCRIPCION, NOMBRECLIENTE)"
			               +" VALUES(?, ?)";
			ps = con.prepareStatement(comentar);
			ps.setString(1, comentario.getDescripcion());
			ps.setString(2, comentario.getNombrecliente());
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		} 
	}
	
/*************************************************************************/
	
	public List<Comentarios> listarComentarios() throws SQLException, Exception {
		ArrayList<Comentarios> listacomentarios = new ArrayList<>();
		try {
			con = c.getConexion();
			String strSQL = "select * from comentarios";
			ps = con.prepareStatement(strSQL);
			rs = ps.executeQuery();
			while(rs.next()) {
				Comentarios comentario = new Comentarios();
				comentario.setIdcomentario(rs.getInt(1));
				comentario.setDescripcion(rs.getString(2));
				comentario.setNombrecliente(rs.getString(3));
				comentario.setFechacomentario(rs.getTimestamp(4));
				listacomentarios.add(comentario);
			}
		} catch(SQLException ex) {
			throw ex;
		}
		catch(Exception e) {
			 throw e;
		}
		return listacomentarios;
	}
	
/***************************************************************************/
	
	public void deleteComentario(int idcomentario) throws SQLException, Exception {
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String eliminar = "DELETE FROM COMENTARIOS WHERE ID_COMENTARIO = ?";
			ps = con.prepareStatement(eliminar);
			ps.setInt(1, idcomentario);
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
	}
	
}

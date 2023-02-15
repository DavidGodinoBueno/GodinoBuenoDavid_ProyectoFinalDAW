package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Detallepeliculas;

public class DaoDetallepeliculas {

	Conexion c = new Conexion();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ArrayList<Detallepeliculas> peliculasByCompra(int idcompra) throws SQLException, Exception {
		ArrayList<Detallepeliculas> listapeliculas = new ArrayList<>();
		try {
			con = c.getConexion();
			String listar = "SELECT D.ID_PELICULA, P.TITULO, P.PRECIO, D.CANTIDAD, D.PRECIOCOMPRA"
					+ " FROM DETALLE_PELICULAS D, COMPRAS C, PELICULAS P"
					+ " WHERE D.ID_COMPRA = C.ID_COMPRA"
					+ " AND P.ID_PELICULA=D.ID_PELICULA"
					+ " AND D.ID_COMPRA = ?";
			ps = con.prepareStatement(listar);
			ps.setInt(1, idcompra);
			rs = ps.executeQuery();
			while(rs.next()) {
				Detallepeliculas detalle = new Detallepeliculas();
				detalle.setIdpelicula(rs.getInt("D.ID_PELICULA"));
				detalle.setTitulopelicula(rs.getString("P.TITULO"));
				detalle.setPreciounitario(rs.getDouble("P.PRECIO"));
				detalle.setCantidad(rs.getInt("D.CANTIDAD"));
				detalle.setPreciocompra(rs.getDouble("D.PRECIOCOMPRA"));
				listapeliculas.add(detalle);
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return listapeliculas;
	}
}

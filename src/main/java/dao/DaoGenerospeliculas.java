package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Generospeliculas;

public class DaoGenerospeliculas {

	Conexion c = new Conexion();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ArrayList<Generospeliculas> listarGeneros() throws SQLException, Exception {
		ArrayList<Generospeliculas> generos = new ArrayList<>();
		try {
			con = c.getConexion();
			String listar = "select * from generos_peliculas";
			ps = con.prepareStatement(listar);
			rs = ps.executeQuery();
			while(rs.next()) {
				Generospeliculas genero = new Generospeliculas();
				genero.setIdgenero(rs.getInt(1));
				genero.setDescripcion(rs.getString(2));
				generos.add(genero);
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return generos;
	}
	
}

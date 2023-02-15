package dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import conexiones.Conexion;
import entidades.Clientes;
import entidades.Peliculas;
import excepciones.PeliculaException;
import excepciones.LoginException;

public class DaoPeliculas {

	Conexion c = new Conexion();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public List<Peliculas> listarPeliculas(String nombre, String clave) throws LoginException, SQLException, Exception {
		ArrayList<Peliculas> listapeliculas = new ArrayList<>();
		String strSQL = "SELECT P.ID_PELICULA, P.TITULO, P.DESCRIPCION,"
		 +" P.PRECIO, P.FOTOPORTADA, P.BAJA, G.DESCRIPCION"+
	      " FROM PELICULAS P JOIN GENEROS_PELICULAS G"
		 +" WHERE P.ID_GENERO = G.ID_GENERO"
	     +" AND P.BAJA != 'S'"
		 +" AND P.STOCK > 0"
	     +" ORDER BY P.ID_PELICULA";
		DaoClientes daocliente = new DaoClientes();
		Clientes cliente = daocliente.findClientes(nombre, clave);
		if(cliente == null) {
			throw new LoginException("Credenciales no validas");
		}
		try {
			con = c.getConexion();
			ps = con.prepareStatement(strSQL);
			rs = ps.executeQuery();
			while(rs.next()) {
				Peliculas pelicula = new Peliculas();
				pelicula.setIdpelicula(rs.getInt("P.ID_PELICULA"));
				pelicula.setTitulo(rs.getString("P.TITULO"));
				pelicula.setDescripcion(rs.getString("P.DESCRIPCION"));
				pelicula.setPrecio(rs.getFloat("P.PRECIO"));
				pelicula.setFotoportada(rs.getBinaryStream("P.FOTOPORTADA"));
				pelicula.setBaja(rs.getString("P.BAJA"));
				pelicula.setNombregenero(rs.getString("G.DESCRIPCION"));
				listapeliculas.add(pelicula);
			}
		} catch(Exception e) {
			 e.printStackTrace();
		}
		return listapeliculas;
	}
	
/*************************************************************************/
	
	public void listarIMGpeliculas(int id, HttpServletResponse response) {
		String sql = "select * from peliculas where id_pelicula = "+id;
		InputStream inputstream = null;
		OutputStream outputstream = null;
		BufferedInputStream bufferedinputstream = null;
		BufferedOutputStream bufferedoutputstream = null;
		response.setContentType("image/*");
		try {
			outputstream = response.getOutputStream();
			con = c.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				inputstream = rs.getBinaryStream("FOTOPORTADA");
			}
			bufferedinputstream = new BufferedInputStream(inputstream);
			bufferedoutputstream = new BufferedOutputStream(outputstream);
			int i = 0;
			while((i=bufferedinputstream.read()) != -1) {
				bufferedoutputstream.write(i);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
/*****************************************************************************/
	
	public List<Peliculas> buscarPeliculasByGeneroYTitulo(String criterio, String patron) throws SQLException, Exception {
		ArrayList<Peliculas> peliculas = new ArrayList<>();
		try {
			con = c.getConexion();
			String buscar = "SELECT P.ID_PELICULA, P.TITULO, P.DESCRIPCION,"
					 +" P.PRECIO, P.FOTOPORTADA, P.BAJA,  G.DESCRIPCION"
					+ " FROM PELICULAS P, GENEROS_PELICULAS G"
					+ " WHERE P.ID_GENERO = G.ID_GENERO"
					+ " AND P.STOCK > 0"
					+ " AND P.BAJA != 'S'"
					+ " AND G.DESCRIPCION LIKE ?"
					+ " AND UPPER(P.TITULO) LIKE UPPER(?)"
					+ " ORDER BY P.ID_PELICULA";
			ps = con.prepareStatement(buscar);
			ps.setString(1, "%"+criterio+"%");
			ps.setString(2, "%"+patron+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				Peliculas pelicula = new Peliculas();
				pelicula.setIdpelicula(rs.getInt("P.ID_PELICULA"));
				pelicula.setTitulo(rs.getString("P.TITULO"));
				pelicula.setDescripcion(rs.getString("P.DESCRIPCION"));
				pelicula.setPrecio(rs.getFloat("P.PRECIO"));
				pelicula.setFotoportada(rs.getBinaryStream("P.FOTOPORTADA"));
				pelicula.setBaja(rs.getString("P.BAJA"));
				pelicula.setNombregenero(rs.getString("G.DESCRIPCION"));
				peliculas.add(pelicula);
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return peliculas;
	}
	
/**************************************************************************/
	
	public Peliculas buscarPeliculasPorId(int idpelicula) throws SQLException, Exception {
		Peliculas pelicula = null;
		try {
			con = c.getConexion();
			String buscar = "SELECT P.ID_PELICULA, P.TITULO, P.DESCRIPCION,"+
			               " P.PRECIO, P.FOTOPORTADA, P.ID_GENERO, P.STOCK,"
					      +" P.BAJA, G.DESCRIPCION"
					       +" FROM PELICULAS P, GENEROS_PELICULAS G"
			               +" WHERE P.ID_GENERO = G.ID_GENERO"
					       +" AND P.ID_PELICULA = ?";
			ps = con.prepareStatement(buscar);
			ps.setInt(1, idpelicula);
			rs = ps.executeQuery();
			while(rs.next()) {
				pelicula = new Peliculas();
				pelicula.setIdpelicula(rs.getInt("P.ID_PELICULA"));
				pelicula.setTitulo(rs.getString("P.TITULO"));
				pelicula.setDescripcion(rs.getString("P.DESCRIPCION"));
				pelicula.setPrecio(rs.getFloat("P.PRECIO"));
				pelicula.setFotoportada(rs.getBinaryStream("P.FOTOPORTADA"));
				pelicula.setIdgenero(rs.getInt("P.ID_GENERO"));
				pelicula.setStock(rs.getInt("P.STOCK"));
				pelicula.setBaja(rs.getString("P.BAJA"));
				pelicula.setNombregenero(rs.getString("G.DESCRIPCION"));
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return pelicula;
	}
	
/***********************************************************************/

	public Peliculas buscarUnaPeliByTitulo(String titulo) throws SQLException, Exception {
		Peliculas pelicula = null;
		try {
			con = c.getConexion();
			String buscar = "select * from peliculas where titulo = ?"
			              +" and baja != 'S' and stock > 0";
			ps = con.prepareStatement(buscar);
			ps.setString(1, titulo);
			rs = ps.executeQuery();
			if(rs.next()) {
				pelicula = new Peliculas();
				pelicula.setIdpelicula(rs.getInt(1));
				pelicula.setTitulo(rs.getString(2));
				pelicula.setDescripcion(rs.getString(3));
				pelicula.setPrecio(rs.getFloat(4));
				pelicula.setFotoportada(rs.getBinaryStream(5));
				pelicula.setIdgenero(rs.getInt(6));
				pelicula.setBaja(rs.getString(8));
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return pelicula;
	}
	
/************************************************************************/
	public void agregarPelicula(Peliculas pelicula) throws PeliculaException, SQLException, Exception {
		Peliculas buscarpelicula = buscarUnaPeliByTitulo(pelicula.getTitulo());
		if(buscarpelicula != null) {
			throw new PeliculaException("Ya existe una pelicula con título "+pelicula.getTitulo());
		}
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String nuevapelicula = "INSERT INTO PELICULAS(TITULO, DESCRIPCION, PRECIO, FOTOPORTADA, ID_GENERO, STOCK, BAJA)"
			               +" VALUES(?, ?, ?, ?, ?, ?, 'N')";
			ps = con.prepareStatement(nuevapelicula);
			ps.setString(1, pelicula.getTitulo());
			ps.setString(2, pelicula.getDescripcion());
			ps.setFloat(3, pelicula.getPrecio());
			ps.setBlob(4, pelicula.getFotoportada());
			ps.setInt(5, pelicula.getIdgenero());
			ps.setInt(6, pelicula.getStock());
			ps.executeUpdate();
			con.commit();
		} 
		catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
	}
	
/**********************************************************************/
	
	public List<Peliculas> buscarPeliculasPorTitulo(String patron) throws SQLException, Exception {
		ArrayList<Peliculas> peliculas = new ArrayList<>();
		try {
			con = c.getConexion();
			String buscar = "SELECT P.ID_PELICULA, P.TITULO, P.DESCRIPCION,"
					 +" P.PRECIO, P.FOTOPORTADA, P.BAJA, G.DESCRIPCION"
					+ " FROM PELICULAS P, GENEROS_PELICULAS G"
					+ " WHERE P.ID_GENERO = G.ID_GENERO"
					+ " AND P.STOCK > 0"
					+ " AND P.BAJA != 'S'"
					+ " AND UPPER(P.TITULO) LIKE UPPER(?)"
					+ " ORDER BY P.ID_PELICULA";
			ps = con.prepareStatement(buscar);
			ps.setString(1, "%"+patron+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				Peliculas pelicula = new Peliculas();
				pelicula.setIdpelicula(rs.getInt("P.ID_PELICULA"));
				pelicula.setTitulo(rs.getString("P.TITULO"));
				pelicula.setDescripcion(rs.getString("P.DESCRIPCION"));
				pelicula.setPrecio(rs.getFloat("P.PRECIO"));
				pelicula.setFotoportada(rs.getBinaryStream("P.FOTOPORTADA"));
				pelicula.setBaja(rs.getString("P.BAJA"));
				pelicula.setNombregenero(rs.getString("G.DESCRIPCION"));
				peliculas.add(pelicula);
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return peliculas;
	}
	
/********************************************************************************/
	
	public void modificarPelicula(Peliculas p) throws SQLException, Exception {
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String modificar = "UPDATE PELICULAS SET TITULO = ?, DESCRIPCION = ?, PRECIO = ?,"
			                +" FOTOPORTADA = ?, ID_GENERO = ?, STOCK = ?"
					        +" WHERE ID_PELICULA = ?";
			ps = con.prepareStatement(modificar);
			ps.setString(1, p.getTitulo());
			ps.setString(2, p.getDescripcion());
			ps.setFloat(3, p.getPrecio());
			ps.setBinaryStream(4, p.getFotoportada());
			ps.setInt(5, p.getIdgenero());
			ps.setInt(6, p.getStock());
			ps.setInt(7, p.getIdpelicula());
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
	}
	
/**************************************************************************/
	
	public void eliminarPelicula(int idpelicula) throws SQLException, Exception {
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String dardebaja = "UPDATE PELICULAS SET BAJA = 'S'"
			                 +" WHERE ID_PELICULA = ?";
			ps = con.prepareStatement(dardebaja);
			ps.setInt(1, idpelicula);
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
	}
	
}

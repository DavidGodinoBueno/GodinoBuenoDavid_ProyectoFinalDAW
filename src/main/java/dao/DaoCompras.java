package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import carrocompras.Carrito;
import conexiones.Conexion;
import entidades.Compras;
import entidades.Pago;
import entidades.Peliculas;

public class DaoCompras {

	Conexion c = new Conexion();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ArrayList<Compras> listarComprasByCliente(String nombre, int rowsByPage, int firstPage) throws SQLException, Exception {
		ArrayList<Compras> listacompras = new ArrayList<>();
		try {
			con = c.getConexion();
			String listar = "SELECT * FROM COMPRAS CM JOIN CLIENTES CL"
			            +" ON CM.ID_CLIENTE = CL.ID_CLIENTE"
					    +" WHERE CL.NOMBRE = ?"
			            +" LIMIT ? OFFSET ?";
			ps = con.prepareStatement(listar);
			ps.setString(1, nombre);
			ps.setInt(2, rowsByPage);
			ps.setInt(3, firstPage);
			rs = ps.executeQuery();
			while(rs.next()) {
				Compras compra = new Compras();
				compra.setId(rs.getInt(1));
				compra.setIdcliente(rs.getInt(2));
				compra.setFechacompra(rs.getTimestamp(4));
				compra.setImporte(rs.getDouble(5));
				compra.setEstado(rs.getString(6));
				listacompras.add(compra);
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return listacompras;
	}
	
	/*******************************************************************************************/	
	
	public int totalComprasCliente(String elnombre) throws SQLException, Exception {
		int contador = 0;
		try {
			con = c.getConexion();
			String listar = "SELECT COUNT(*) AS 'TOTALCOMPRAS'"
			            +" FROM COMPRAS CM JOIN CLIENTES CL"
			            +" ON CM.ID_CLIENTE = CL.ID_CLIENTE"
					    +" WHERE CL.NOMBRE = ?";
			ps = con.prepareStatement(listar);
			ps.setString(1, elnombre);
			rs = ps.executeQuery();
			if(rs.next()) {
				contador = rs.getInt("TOTALCOMPRAS");
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return contador;
	}
	
/*******************************************************************************************/	
	
	public void realizarCompra(Compras compra) {
		int idcompra;
		try {
			con = c.getConexion();
			String comprar = "INSERT INTO COMPRAS (ID_CLIENTE, ID_PAGO, IMPORTE, ESTADO)"
		             +" VALUES(?, ?, ?, ?)";
			ps = con.prepareStatement(comprar);
			ps.setInt(1, compra.getIdcliente());
			ps.setInt(2, compra.getIdpago());
			ps.setDouble(3, compra.getImporte());
			ps.setString(4, compra.getEstado());
			ps.executeUpdate();
			
			comprar = "SELECT @@IDENTITY AS ID_COMPRA";
			rs = ps.executeQuery(comprar);
			rs.next();
			idcompra = rs.getInt("ID_COMPRA");
			rs.close();
			
			for(Carrito detalle:compra.getDetallecompra()) {
				comprar = "INSERT INTO DETALLE_PELICULAS (ID_PELICULA, ID_COMPRA, CANTIDAD, PRECIOCOMPRA)"
			       +" VALUES(?, ?, ?, ?)";
				ps = con.prepareStatement(comprar);
				ps.setInt(1, detalle.getIdpelicula());
				ps.setInt(2, idcompra);
				ps.setInt(3, detalle.getCantidad());
				double subtutolredondeo = Math.round(detalle.getSubtotal()*100.0)/100.0;
				ps.setDouble(4, subtutolredondeo);
				ps.executeUpdate();
				ps.close();
				con.close();
				con = c.getConexion();
				String modificar = "UPDATE PELICULAS SET STOCK = ?"
				                 +" WHERE ID_PELICULA = ?";
				ps = con.prepareStatement(modificar);
				DaoPeliculas daopelis = new DaoPeliculas();
				Peliculas peliculas = daopelis.buscarPeliculasPorId(detalle.getIdpelicula());
				int stockpelis = peliculas.getStock();
				int menosstock = stockpelis - detalle.getCantidad();
				peliculas.setStock(menosstock);
				ps.setInt(1, peliculas.getStock());
				ps.setInt(2, detalle.getIdpelicula());
				ps.executeUpdate();
				ps.close();
				con.close();
				con = c.getConexion();
				String stockacero = "UPDATE PELICULAS SET STOCK = 0"
				                  +" WHERE STOCK < 0";
				ps = con.prepareStatement(stockacero);
				ps.executeUpdate();
			}
		}  catch(Exception ex) {
			
		}
	}
	

}

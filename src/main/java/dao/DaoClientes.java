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

import javax.servlet.http.HttpServletResponse;

import conexiones.Conexion;
import entidades.Clientes;
import excepciones.RegistroException;

public class DaoClientes {

	Conexion c = new Conexion();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public Clientes findClientes(String nombre, String clave) throws SQLException, Exception {
		Clientes cliente = null;
		try {
			con = c.getConexion();
			String strSQL = "SELECT * FROM CLIENTES "+
			                " WHERE NOMBRE = ? AND CLAVE = ?";
			ps = con.prepareStatement(strSQL);
			ps.setString(1, nombre);
			ps.setString(2, clave);
			rs = ps.executeQuery();
			if(rs.next()) {
				cliente = new Clientes();
				cliente.setIdcliente(rs.getInt(1));
				cliente.setDni(rs.getString(2));
				cliente.setNombre(rs.getString(3));
				cliente.setCorreo(rs.getString(4));
				cliente.setClave(rs.getString(5));
				cliente.setDireccion(rs.getString(6));
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return cliente;
	}
	
/********************************************************************************/	
	
	public Clientes findClientesByName(String nombre) throws SQLException, Exception {
		Clientes cliente = null;
		try {
			con = c.getConexion();
			String strSQL = "SELECT * FROM CLIENTES "+
			                " WHERE NOMBRE = ?";
			ps = con.prepareStatement(strSQL);
			ps.setString(1, nombre);
			rs = ps.executeQuery();
			if(rs.next()) {
				cliente = new Clientes();
				cliente.setIdcliente(rs.getInt(1));
				cliente.setDni(rs.getString(2));
				cliente.setNombre(rs.getString(3));
				cliente.setCorreo(rs.getString(4));
				cliente.setClave(rs.getString(5));
				cliente.setDireccion(rs.getString(6));
				cliente.setRol(rs.getString(8));
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return cliente;
	}
	
/*******************************************************************************/
	
	public void registrarCliente(Clientes cliente) throws RegistroException, SQLException, Exception {
		Clientes buscarcliente = findClientesByName(cliente.getNombre());
		if(buscarcliente != null) {
			throw new RegistroException("El cliente "+buscarcliente.getNombre()+" ya está registrado, registre uno nuevo");
		}
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String registrar = "INSERT INTO CLIENTES(DNI, NOMBRE, EMAIL, CLAVE, DIRECCION, FOTOPERFIL, ROL)"
			               +" VALUES(?, ?, ?, ?, ?, ?, 'UNCLIENTE')";
			ps = con.prepareStatement(registrar);
			ps.setString(1, cliente.getDni());
			ps.setString(2, cliente.getNombre());
			ps.setString(3, cliente.getCorreo());
			ps.setString(4, cliente.getClave());
			ps.setString(5, cliente.getDireccion());
			ps.setBlob(6, cliente.getFotoperfil());
			ps.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
	}
	
/*************************************************************************/
	
	public ArrayList<Clientes> listarClientes() throws SQLException, Exception {
		ArrayList<Clientes> listaclientes = new ArrayList<>();
		try {
			con = c.getConexion();
			String strSQL = "SELECT * FROM CLIENTES";
			ps = con.prepareStatement(strSQL);
			rs = ps.executeQuery();
			while(rs.next()) {
				Clientes cliente = new Clientes();
				cliente.setIdcliente(rs.getInt(1));
				cliente.setDni(rs.getString(2));
				cliente.setNombre(rs.getString(3));
				cliente.setCorreo(rs.getString(4));
				cliente.setClave(rs.getString(5));
				cliente.setDireccion(rs.getString(6));
				listaclientes.add(cliente);
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception ex) {
			throw ex;
		}
		return listaclientes;
	}
	
/**********************************************************************/
	
	public void listarIMGperfilCliente(String nombre, HttpServletResponse response) {
		String sql = "select * from clientes"+
	                " where nombre = '"+nombre+"'";
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
				inputstream = rs.getBinaryStream("FOTOPERFIL");
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
	
}

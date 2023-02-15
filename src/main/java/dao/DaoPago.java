package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexiones.Conexion;
import entidades.Pago;

public class DaoPago {

	Conexion c = new Conexion();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public void agregarPago(Pago pago) {
		try {
			con = c.getConexion();
			con.setAutoCommit(false);
			String pagar = "INSERT INTO PAGO(IMPORTE, TARJETA_PAGO)"
			               +" VALUES(?, ?)";
			ps = con.prepareStatement(pagar);
			ps.setDouble(1, pago.getImporte());
			ps.setString(2, pago.getTarjetapago());
			ps.executeUpdate();
			con.commit();
		}  catch(Exception ex) {
			
		}
	}
	
/***********************************************************************/
	
	public int buscarPago() {
		int pagoultimo = 0;
		try {
		   con = c.getConexion();
		   String maximopago = "select max(id_pago) from pago";
		   ps = con.prepareStatement(maximopago);
		   rs = ps.executeQuery();
		   if(rs.next()) {
			   pagoultimo = rs.getInt(1);
		   }
		} catch(Exception e) {
			
		}
		
		return pagoultimo;
	}

}

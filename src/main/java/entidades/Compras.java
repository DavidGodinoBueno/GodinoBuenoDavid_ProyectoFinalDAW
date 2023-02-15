package entidades;

import java.sql.Timestamp;
import java.util.List;

import carrocompras.Carrito;

public class Compras {

	private int id;
	private int idcliente;
    private int idpago;
	private Timestamp fechacompra;
	private double importe;
	private String estado;
	
	private List<Carrito> detallecompra;

	public Compras() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public int getIdpago() {
		return idpago;
	}

	public void setIdpago(int idpago) {
		this.idpago = idpago;
	}

	public Timestamp getFechacompra() {
		return fechacompra;
	}

	public void setFechacompra(Timestamp fechacompra) {
		this.fechacompra = fechacompra;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Carrito> getDetallecompra() {
		return detallecompra;
	}

	public void setDetallecompra(List<Carrito> detallecompra) {
		this.detallecompra = detallecompra;
	}

	
	
}

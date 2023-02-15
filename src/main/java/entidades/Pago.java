package entidades;

public class Pago {

	private int idpago;
	private double importe;
	private String tarjetapago;
	public Pago() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pago(int idpago, double importe, String tarjetapago) {
		super();
		this.idpago = idpago;
		this.importe = importe;
		this.tarjetapago = tarjetapago;
	}
	public int getIdpago() {
		return idpago;
	}
	public void setIdpago(int idpago) {
		this.idpago = idpago;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public String getTarjetapago() {
		return tarjetapago;
	}
	public void setTarjetapago(String tarjetapago) {
		this.tarjetapago = tarjetapago;
	}
	
	
}

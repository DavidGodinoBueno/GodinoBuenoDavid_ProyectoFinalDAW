package entidades;

public class Detallepeliculas {

	private int iddetalle;
	private int idpelicula;
	private int idcompra;
	private String titulopelicula;
	private double preciounitario;
	private int cantidad;
	private double preciocompra;
	public Detallepeliculas() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIddetalle() {
		return iddetalle;
	}
	public void setIddetalle(int iddetalle) {
		this.iddetalle = iddetalle;
	}
	public int getIdpelicula() {
		return idpelicula;
	}
	public void setIdpelicula(int idpelicula) {
		this.idpelicula = idpelicula;
	}
	public int getIdcompra() {
		return idcompra;
	}
	public void setIdcompra(int idcompra) {
		this.idcompra = idcompra;
	}
	public String getTitulopelicula() {
		return titulopelicula;
	}
	public void setTitulopelicula(String titulopelicula) {
		this.titulopelicula = titulopelicula;
	}
	
	public double getPreciounitario() {
		return preciounitario;
	}
	public void setPreciounitario(double preciounitario) {
		this.preciounitario = preciounitario;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPreciocompra() {
		return preciocompra;
	}
	public void setPreciocompra(double preciocompra) {
		this.preciocompra = preciocompra;
	}
	
	
}

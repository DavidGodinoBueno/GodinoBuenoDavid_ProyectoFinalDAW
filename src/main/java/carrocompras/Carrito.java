package carrocompras;

public class Carrito {

	private int item;
	private int idpelicula;
	private String titulo;
	private String descripcion;
	private double preciocompra;
	private int cantidad;
	private double subtotal;
	public Carrito() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Carrito(int item, int idpelicula, String titulo, String descripcion, double preciocompra, int cantidad,
			double subtotal) {
		super();
		this.item = item;
		this.idpelicula = idpelicula;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.preciocompra = preciocompra;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

    public int getIdpelicula() {
		return idpelicula;
	}

	public void setIdpelicula(int idpelicula) {
		this.idpelicula = idpelicula;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPreciocompra() {
		return preciocompra;
	}

	public void setPreciocompra(double preciocompra) {
		this.preciocompra = preciocompra;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	
}

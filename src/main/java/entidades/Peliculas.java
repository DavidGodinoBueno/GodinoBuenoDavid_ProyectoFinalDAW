package entidades;

import java.io.InputStream;

public class Peliculas {

	private int idpelicula;
	private String titulo;
	private String descripcion;
	private float precio;
	private InputStream fotoportada;
	private int idgenero;
	private String nombregenero;
	private int stock;
	private String baja;
	public Peliculas() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Peliculas(int idpelicula, String titulo, String descripcion, float precio, InputStream fotoportada,
			int idgenero, String nombregenero, int stock, String baja) {
		super();
		this.idpelicula = idpelicula;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.fotoportada = fotoportada;
		this.idgenero = idgenero;
		this.nombregenero = nombregenero;
		this.stock = stock;
		this.baja = baja;
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
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public InputStream getFotoportada() {
		return fotoportada;
	}
	public void setFotoportada(InputStream fotoportada) {
		this.fotoportada = fotoportada;
	}
	public int getIdgenero() {
		return idgenero;
	}
	public void setIdgenero(int idgenero) {
		this.idgenero = idgenero;
	}
	public String getNombregenero() {
		return nombregenero;
	}
	public void setNombregenero(String nombregenero) {
		this.nombregenero = nombregenero;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getBaja() {
		return baja;
	}

	public void setBaja(String baja) {
		this.baja = baja;
	}

	@Override
	public String toString() {
		return "Peliculas [idpelicula=" + idpelicula + ", titulo=" + titulo + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", fotoportada=" + fotoportada + ", idgenero=" + idgenero + ", nombregenero="
				+ nombregenero + ", stock=" + stock + ", baja=" + baja + "]";
	}	
}

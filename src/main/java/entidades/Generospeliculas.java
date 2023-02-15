package entidades;

public class Generospeliculas {

	private int idgenero;
	private String descripcion;
	public Generospeliculas() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdgenero() {
		return idgenero;
	}
	public void setIdgenero(int idgenero) {
		this.idgenero = idgenero;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Generospeliculas [idgenero=" + idgenero + ", descripcion=" + descripcion + "]";
	}
	
	
}

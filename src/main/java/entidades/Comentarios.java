package entidades;

import java.sql.Timestamp;

public class Comentarios {

	private int idcomentario;
	private String descripcion;
	private String nombrecliente;
	private Timestamp fechacomentario;

	public Comentarios() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdcomentario() {
		return idcomentario;
	}
	public void setIdcomentario(int idcomentario) {
		this.idcomentario = idcomentario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombrecliente() {
		return nombrecliente;
	}
	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}
	public Timestamp getFechacomentario() {
		return fechacomentario;
	}
	public void setFechacomentario(Timestamp fechacomentario) {
		this.fechacomentario = fechacomentario;
	}
	@Override
	public String toString() {
		return "Comentarios [idcomentario=" + idcomentario + ", descripcion=" + descripcion + ", nombrecliente="
				+ nombrecliente + ", fechacomentario=" + fechacomentario + "]";
	}
	
}

package entidades;

import java.io.InputStream;

public class Clientes {

	private int idcliente;
	private String dni;
	private String nombre;
	private String correo;
	private String clave;
	private String direccion;
	private InputStream fotoperfil;
	private String rol;
	public Clientes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public InputStream getFotoperfil() {
		return fotoperfil;
	}
	public void setFotoperfil(InputStream fotoperfil) {
		this.fotoperfil = fotoperfil;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Clientes [idcliente=" + idcliente + ", dni=" + dni + ", nombre=" + nombre + ", correo=" + correo
				+ ", clave=" + clave + ", direccion=" + direccion + ", fotoperfil=" + fotoperfil + ", rol=" + rol + "]";
	}

}

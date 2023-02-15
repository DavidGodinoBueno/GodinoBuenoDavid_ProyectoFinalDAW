package excepciones;

public class PeliculaException extends Exception{

	private static final long serialVersionUID = 1L;

	public PeliculaException() {
		
	}
	
    public PeliculaException(String mensaje) {
		super(mensaje);
	}
}

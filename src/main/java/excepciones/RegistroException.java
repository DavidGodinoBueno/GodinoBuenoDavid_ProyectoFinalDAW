package excepciones;

public class RegistroException extends Exception {

	private static final long serialVersionUID = 1L;

	public RegistroException() {
		
	}
	
    public RegistroException(String mensaje) {
		super(mensaje);
	}
}


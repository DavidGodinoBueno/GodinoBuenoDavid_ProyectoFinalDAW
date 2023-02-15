package excepciones;

public class LoginException extends Exception {

	private static final long serialVersionUID = 1L;

	public LoginException() {
		
	}
	
    public LoginException(String mensaje) {
		super(mensaje);
	}
}

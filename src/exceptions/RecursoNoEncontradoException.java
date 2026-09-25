package exceptions;

public class RecursoNoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
	
}

package service.exceptions;

public class DuplicadoRecursoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicadoRecursoException(String mensaje) {
        super(mensaje);
    }

}

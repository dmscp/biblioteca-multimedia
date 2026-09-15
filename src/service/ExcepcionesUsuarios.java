package service;


public class ExcepcionesUsuarios {

}

// Excepción 1: ID Duplicado (Accesible dentro del mismo paquete)
class UsuarioDuplicadoException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsuarioDuplicadoException(String mensaje) {
        super(mensaje);
    }
}

// Excepción 2: Usuario No Encontrado (Accesible dentro del mismo paquete)
class UsuarioNoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsuarioNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}

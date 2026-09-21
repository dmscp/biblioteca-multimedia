package service;

public class ExcepcionesUsuarios {

    // Excepción 1: ID Duplicado (Accesible desde cualquier paquete)
    public static class UsuarioDuplicadoException extends Exception {
        private static final long serialVersionUID = 1L;

        public UsuarioDuplicadoException(String mensaje) {
            super(mensaje);
        }
    }

    // Excepción 2: Usuario No Encontrado (Accesible desde cualquier paquete)
    public static class UsuarioNoEncontradoException extends Exception {
        private static final long serialVersionUID = 1L;

        public UsuarioNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }
}

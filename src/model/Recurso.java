package model;

/**
 * Clase abstracta que representa cualquier recurso que se puede
 * gestionar (dar de alta, prestar, devolver...).
 *
 * Concentra los datos comunes a todos los recursos (identificador,
 * título, año y estado) y obliga a cada subtipo concreto a describir
 * sus propios datos específicos mediante {@link #getDetallesEspecificos()}.
 */
public abstract class Recurso {

    private final String identificador;
    private String titulo;
    private int anio;
    private EstadoRecurso estado;

    protected Recurso(String identificador, String titulo, int anio) {
        this.identificador = identificador;
        this.titulo = titulo;
        this.anio = anio;
        this.estado = EstadoRecurso.DISPONIBLE;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public EstadoRecurso getEstado() {
        return estado;
    }

    public void setEstado(EstadoRecurso estado) {
        this.estado = estado;
    }

    public boolean estaDisponible() {
        return estado == EstadoRecurso.DISPONIBLE;
    }

    /**
     * Cada tipo concreto (Libro, Película, Videojuego, ...) debe
     * describir aquí sus datos propios (autor y páginas; director y
     * duración; plataforma y PEGI; etc.).
     */
    public abstract String getDetallesEspecificos();

    @Override
    public String toString() {
        return String.format("[%s] %s (%d) - Estado: %s | %s",
                identificador, titulo, anio, estado, getDetallesEspecificos());
    }
}
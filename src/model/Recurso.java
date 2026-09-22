package model;
 
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
 
    public abstract String getDetallesEspecificos();
    
 @Override
public String toString() {
    return "[" + identificador + "] " + titulo + " (" + anio + ") - Estado: " + estado + " | " + getDetallesEspecificos();
 }
}
package model;

import java.time.LocalDate;

public class Prestamo {
    private String idPrestamo;
    private Usuario usuario;
    private Recurso recurso;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion; 
    private boolean activo;

    public Prestamo(String idPrestamo, Usuario usuario, Recurso recurso) {
        this.idPrestamo = idPrestamo;
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null;
        this.activo = true;
    }

    public void registrarDevolucion() {
        this.fechaDevolucion = LocalDate.now();
        this.activo = false;
    }

    // Getters y Setters
    public String getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(String idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        String estado = activo ? "ACTIVO" : "DEVUELTO (" + fechaDevolucion + ")";
        return "Préstamo [ID=" + idPrestamo + " | Usuario=" + usuario.getId() 
                + " | Recurso=" + recurso.getId() + " | Fecha=" + fechaPrestamo + " | Estado=" + estado + "]";
    }
}
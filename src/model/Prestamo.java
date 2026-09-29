package model;

import java.time.LocalDate;

public class Prestamo {
    private int idPrestamo;
    private Usuario usuario;
    private Recurso recurso;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion; 
    private boolean activo;

    public Prestamo(int idPrestamo, Usuario usuario, Recurso recurso) {
        this.idPrestamo = idPrestamo;
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null;
        this.activo = true;
    }

    
    // Getters y Setters
    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
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
    
    
    
    public void registrarDevolucion() {
        this.fechaDevolucion = LocalDate.now();
        this.activo = false;
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
                + " | Recurso=" + recurso.getIdentificador() + " | Fecha=" + fechaPrestamo + " | Estado=" + estado + "]";
    }
}
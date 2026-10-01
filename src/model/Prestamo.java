package model;

import java.time.LocalDate;
import java.util.UUID;

public class Prestamo {
    private final String idPrestamo;
    private Usuario usuario;
    private Recurso recurso;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion; 
    private boolean activo;

    // CONSTRUCTOR PRINCIPAL: Para préstamos nuevos (Genera el ID solo aleatoriamente en base al tiempo/hash)
    public Prestamo(Usuario usuario, Recurso recurso) {
        // Genera un número entero único positivo basado en el hash del UUID para simular la misma estructura
        this.idPrestamo = UUID.randomUUID().toString();
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null;
        this.activo = true;
    }

    // CONSTRUCTOR DE PERSISTENCIA: Usado por el CSV para mantener el ID original
    public Prestamo(String idPrestamo, Usuario usuario, Recurso recurso) {
        this.idPrestamo = idPrestamo;
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null;
        this.activo = true;
    }

    
    // Getters y Setters
    public String getIdPrestamo() {
        return idPrestamo;
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
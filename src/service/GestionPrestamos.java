package service;

import exceptions.RecursoNoEncontradoException;
import exceptions.UsuarioNoEncontradoException;
import model.EstadoRecurso;
import model.Prestamo;
import model.Recurso;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class GestionPrestamos {
    private List<Prestamo> prestamos;
    private List<Usuario> usuarios;
    private List<Recurso> recursos;

    public GestionPrestamos(List<Usuario> usuarios, List<Recurso> recursos) {
        this.prestamos = new ArrayList<>();
        this.usuarios = usuarios;
        this.recursos = recursos;
    }

    // ==========================================
    // PRÉSTAMOS Y DEVOLUCIONES
    // ==========================================

    /**
     * Registra un nuevo préstamo comprobando la existencia de usuario y recurso,
     * y actualizando la disponibilidad.
     */
    public Prestamo prestarRecurso(int idPrestamo, int idUsuario, String idRecurso) 
            throws UsuarioNoEncontradoException, RecursoNoEncontradoException, IllegalStateException {

        // 1. Comprobar que el usuario existe
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException("Error: El usuario con ID '" + idUsuario + "' no existe.");
        }

        Recurso recurso = buscarRecursoPorId(idRecurso);
        if (recurso == null) {
            throw new RecursoNoEncontradoException("Error: El recurso con ID '" + idRecurso + "' no existe.");
        }

        if (!recurso.estaDisponible()) {
            throw new IllegalStateException("Error: El recurso '" + recurso.getTitulo() + "' ya está prestado.");
        }

        Prestamo nuevoPrestamo = new Prestamo(idPrestamo, usuario, recurso);
        recurso.setEstado(EstadoRecurso.PRESTADO);
        prestamos.add(nuevoPrestamo);
        return nuevoPrestamo;
    }

    public boolean devolverRecurso(int idPrestamo) {
        Prestamo prestamo = buscarPrestamoActivoPorId(idPrestamo);

        if (prestamo == null) {
            return false;
        }

        prestamo.registrarDevolucion();
        prestamo.getRecurso().setEstado(EstadoRecurso.DISPONIBLE);

        return true;
    }

    public List<Recurso> obtenerRecursosPorEstado(boolean disponible) {
        List<Recurso> resultado = new ArrayList<>();
        for (Recurso r : recursos) {
            if (r.estaDisponible() == disponible) {
                resultado.add(r);
            }
        }
        return resultado;
    }

  
    public List<Prestamo> buscarPrestamosPorTitulo(String titulo) {
        List<Prestamo> resultado = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.getRecurso().getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    
    public List<Prestamo> obtenerPrestamosDeUsuario(int idUsuario) throws UsuarioNoEncontradoException {
        if (buscarUsuarioPorId(idUsuario) == null) {
            throw new UsuarioNoEncontradoException("Error: El usuario con ID '" + idUsuario + "' no existe.");
        }

        List<Prestamo> resultado = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.getUsuario().getId() == idUsuario) {
                resultado.add(p);
            }
        }
        return resultado;
    }


    public List<Prestamo> obtenerPrestamosActivos() {
        List<Prestamo> activos = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.isActivo()) {
                activos.add(p);
            }
        }
        return activos;
    }


    private Usuario buscarUsuarioPorId(int idUsuario) {
        for (Usuario u : usuarios) {
            if (u.getId() == idUsuario) {
                return u;
            }
        }
        return null;
    }


    private Prestamo buscarPrestamoActivoPorId(int idPrestamo) {
        for (Prestamo p : prestamos) {
            if (p.getIdPrestamo() == idPrestamo && p.isActivo()) {
                return p;
            }
        }
        return null;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }
}
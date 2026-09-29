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
    private GestionUsuarios gestionUsuarios;
    private GestionRecursos gestionRecursos;

    public GestionPrestamos(GestionUsuarios gestionUsuarios, GestionRecursos gestionRecursos) {
        this.prestamos = new ArrayList<>();
        this.gestionUsuarios = gestionUsuarios;
        this.gestionRecursos = gestionRecursos;
    }

    // Registra un nuevo préstamo verificando existencia de usuario y recurso
    public Prestamo prestarRecurso(int idPrestamo, String idUsuario, String idRecurso) 
            throws UsuarioNoEncontradoException, RecursoNoEncontradoException, IllegalStateException {

        Usuario usuario = gestionUsuarios.buscarUsuarioPorId(idUsuario);
        Recurso recurso = gestionRecursos.buscarRecursoPorIdentificador(idRecurso);

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

    // consulta de recursos por disponibilidad
    public List<Recurso> obtenerRecursosPorEstado(boolean disponible) {
        List<Recurso> resultado = new ArrayList<>();
        for (Recurso r : gestionRecursos.getListaRecursos()) {
            if (r.estaDisponible() == disponible) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    // búsqueda de préstamos por título del recurso
    public List<Prestamo> buscarPrestamosPorTitulo(String titulo) {
        List<Prestamo> resultado = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.getRecurso().getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    // consulta de préstamos por usuario
    public List<Prestamo> obtenerPrestamosDeUsuario(String idUsuario) throws UsuarioNoEncontradoException {
        gestionUsuarios.buscarUsuarioPorId(idUsuario);

        List<Prestamo> resultado = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.getUsuario().getId().equalsIgnoreCase(idUsuario)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    // consulta de préstamos activos
    public List<Prestamo> obtenerPrestamosActivos() {
        List<Prestamo> activos = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.isActivo()) {
                activos.add(p);
            }
        }
        return activos;
    }

    // búsqueda de préstamos activos por ID
    private Prestamo buscarPrestamoActivoPorId(int idPrestamo) {
        for (Prestamo p : prestamos) {
            if (p.getIdPrestamo() == idPrestamo && p.isActivo()) {
                return p;
            }
        }
        return null;
    }

    // Getters y Setters para lista de préstamos y persistencia
    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public List<Prestamo> getListaPrestamos() {
        return prestamos;
    }

    public void setListaPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
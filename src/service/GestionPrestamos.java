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

    //REGISTRAR PRESTAMO
    public Prestamo prestarRecurso(String idUsuario, String idRecurso) 
            throws UsuarioNoEncontradoException, RecursoNoEncontradoException, IllegalStateException {

        Usuario usuario = gestionUsuarios.buscarUsuarioPorId(idUsuario);
        Recurso recurso = gestionRecursos.buscarRecursoPorIdentificador(idRecurso);

        if (!recurso.estaDisponible()) {
            throw new IllegalStateException("Error: El recurso '" + recurso.getTitulo() + "' ya está prestado.");
        }

        Prestamo nuevoPrestamo = new Prestamo(usuario, recurso);
        recurso.setEstado(EstadoRecurso.PRESTADO);
        prestamos.add(nuevoPrestamo);
        return nuevoPrestamo;
    }

    //DEVOLVER PRESTAMO
    public boolean devolverRecurso(String idPrestamo) {
        Prestamo prestamo = buscarPrestamoActivoPorId(idPrestamo);

        if (prestamo == null) {
            return false;
        }

        prestamo.registrarDevolucion();
        prestamo.getRecurso().setEstado(EstadoRecurso.DISPONIBLE);

        return true;
    }

    //CONSULTA DE RECURSOS POR DISPONIBILIDAD
    public List<Recurso> obtenerRecursosPorEstado(boolean disponible) {
        List<Recurso> resultado = new ArrayList<>();
        for (Recurso r : gestionRecursos.getListaRecursos()) {
            if (r.estaDisponible() == disponible) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    //BUSCAR PRESTAMOS POR TITULO DE RECURSO
    public List<Prestamo> buscarPrestamosPorTitulo(String titulo) {
        List<Prestamo> resultado = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.getRecurso().getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    //CONSULTAR PRESTAMOS POR USUARIO
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

    //CONSULTAR PRESTAMOS ACTIVOS
    public List<Prestamo> obtenerPrestamosActivos() {
        List<Prestamo> activos = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.isActivo()) {
                activos.add(p);
            }
        }
        return activos;
    }

    //BUSCAR PRESTAMOS ACTIVOS POR ID
    private Prestamo buscarPrestamoActivoPorId(String idPrestamo) {
        for (Prestamo p : prestamos) {
            if (p.getIdPrestamo().equalsIgnoreCase(idPrestamo) && p.isActivo()) {
                return p;
            }
        }
        return null;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }
    public List<Prestamo> getListaPrestamos() {
        return prestamos;
    }
    public void setListaPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
    
    //CONSUTLA ADICIONAL: Obtener historial de préstamos devueltos
    public List<Prestamo> obtenerPrestamosDevueltos() {
        List<Prestamo> devueltos = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (!p.isActivo()) {
                devueltos.add(p);
            }
        }
        return devueltos;
    }

}
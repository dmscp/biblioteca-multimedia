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

    
     //Registra un nuevo préstamo verificando existencia de usuario y recurso,
      //comprobando que esté disponible y cambiando su estado.

    public Prestamo prestarRecurso(int idPrestamo, String idUsuario, String idRecurso) 
            throws UsuarioNoEncontradoException, RecursoNoEncontradoException, IllegalStateException {

    	//Comprobaciones de que existen usuarios y recrsos
        Usuario usuario = gestionUsuarios.buscarUsuarioPorId(idUsuario);
        Recurso recurso = gestionRecursos.buscarRecursoPorIdentificador(idRecurso);

         //Comprobar que el recurso está disponible antes de prestarlo
        if (!recurso.estaDisponible()) {
            throw new IllegalStateException("Error: El recurso '" + recurso.getTitulo() + "' ya está prestado.");
        }

        // Crear el nuevo préstamo
        Prestamo nuevoPrestamo = new Prestamo(idPrestamo, usuario, recurso);
        recurso.setEstado(EstadoRecurso.PRESTADO);
        prestamos.add(nuevoPrestamo);
        return nuevoPrestamo;
    }

    //registrar la devo de un recurso.
    public boolean devolverRecurso(int idPrestamo) {
        Prestamo prestamo = buscarPrestamoActivoPorId(idPrestamo);

        if (prestamo == null) {
            return false;
        }

        prestamo.registrarDevolucion();
        prestamo.getRecurso().setEstado(EstadoRecurso.DISPONIBLE);

        return true;
    }

    //consulta de recursos
    public List<Recurso> obtenerRecursosPorEstado(boolean disponible) {
        List<Recurso> resultado = new ArrayList<>();
        for (Recurso r : gestionRecursos.getListaRecursos()) {
            if (r.estaDisponible() == disponible) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    //consultade busqueda de prestamos por titulo
    public List<Prestamo> buscarPrestamosPorTitulo(String titulo) {
        List<Prestamo> resultado = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.getRecurso().getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    //consulta de prestamos por usuario
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

    //consulta de prestamos activos
    public List<Prestamo> obtenerPrestamosActivos() {
        List<Prestamo> activos = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.isActivo()) {
                activos.add(p);
            }
        }
        return activos;
    }

    //metodos auxiliares para busqueda de prestamos activos por id
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
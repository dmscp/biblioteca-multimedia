package service;

import java.util.ArrayList;
import java.util.List;

import exceptions.DuplicadoRecursoException;
import exceptions.RecursoNoEncontradoException;
import exceptions.RecursoTipoInvalidoExcepcion;
import model.EstadoRecurso;
import model.Libro;
import model.Pelicula;
import model.Recurso;
import model.Videojuego;

public class GestionRecursos {
    
    private List<Recurso> listaRecursos;

    public GestionRecursos() {
        this.listaRecursos = new ArrayList<>();
    }

    public List<Recurso> getListaRecursos() {
        return listaRecursos;
    }
    
    public void setListaRecursos(List<Recurso> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }

    public void crearRecurso(Recurso recurso) throws DuplicadoRecursoException {
        try {
            buscarRecursoPorIdentificador(recurso.getIdentificador());
            throw new DuplicadoRecursoException("Error: Ya existe un recurso con el identificador: " + recurso.getIdentificador());
        } catch (RecursoNoEncontradoException e) {
            this.listaRecursos.add(recurso);
        }
    }

    public void listarRecursos() {
        if (listaRecursos.isEmpty()) {
            System.out.println("No hay recursos en el sistema.");
            return;
        }
        
        System.out.println("\n=== LISTADO DE RECURSOS ===");
        for (Recurso r : listaRecursos) {
            System.out.println(r);
        }
    }

    public Recurso buscarRecursoPorIdentificador(String identificador) throws RecursoNoEncontradoException {
        for (Recurso r : listaRecursos) {
            if (r.getIdentificador().equalsIgnoreCase(identificador)) {
                return r;
            }
        }
        throw new RecursoNoEncontradoException("Error: El recurso con identificador '" + identificador + "' no existe.");
    }

    public void eliminarRecurso(String identificador) throws RecursoNoEncontradoException {
        try {
            Recurso r = buscarRecursoPorIdentificador(identificador);
            this.listaRecursos.remove(r);
        } catch (RecursoNoEncontradoException e) {
            throw e;
        }
    }

    public void modificarLibro(String identificador, String titulo, int anio, String autor, int paginas) throws RecursoNoEncontradoException, RecursoTipoInvalidoExcepcion {
        Recurso r = buscarRecursoPorIdentificador(identificador);
        if (r instanceof Libro) {
            r.setTitulo(titulo);
            r.setAnio(anio);
            ((Libro) r).setAutor(autor);
            ((Libro) r).setPaginas(paginas);
        } else {
            throw new RecursoTipoInvalidoExcepcion("Error: El recurso a modificar no es un libro");
        }
    }
    
    public void modificarPelicula(String identificador, String titulo, int anio, String director, int duracionMinutos) throws RecursoNoEncontradoException, RecursoTipoInvalidoExcepcion {
        Recurso r = buscarRecursoPorIdentificador(identificador);
        if (r instanceof Pelicula) {
            r.setTitulo(titulo);
            r.setAnio(anio);
            ((Pelicula) r).setDirector(director);
            ((Pelicula) r).setDuracionMinutos(duracionMinutos);
        } else {
            throw new RecursoTipoInvalidoExcepcion("Error: El recurso a modificar no es una pelicula");
        }
    }
    
    public void modificarVideojuego(String identificador, String titulo, int anio, String plataforma, int pegi) throws RecursoNoEncontradoException, RecursoTipoInvalidoExcepcion {
        Recurso r = buscarRecursoPorIdentificador(identificador);
        if (r instanceof Videojuego) {
            r.setTitulo(titulo);
            r.setAnio(anio);
            ((Videojuego) r).setPlataforma(plataforma);
            ((Videojuego) r).setPegi(pegi);
        } else {
            throw new RecursoTipoInvalidoExcepcion("Error: El recurso a modificar no es un videojuego");
        }
    }
    
    public EstadoRecurso obtenerEstadoRecurso(String identificador) throws RecursoNoEncontradoException {
        Recurso r = this.buscarRecursoPorIdentificador(identificador);
        return r.getEstado();
    }

    // ========================================================
    // NUEVAS FUNCIONALIDADES OBLIGATORIAS Y CONSULTAS EXTRA
    // ========================================================

    // Requisito Obligatorio: Recursos filtrados por tipo
    public List<Recurso> filtrarPorTipo(String tipo) {
        List<Recurso> filtrados = new ArrayList<>();
        for (Recurso r : listaRecursos) {
            if (tipo.equalsIgnoreCase("LIBRO") && r instanceof Libro) {
                filtrados.add(r);
            } else if (tipo.equalsIgnoreCase("PELICULA") && r instanceof Pelicula) {
                filtrados.add(r);
            } else if (tipo.equalsIgnoreCase("VIDEOJUEGO") && r instanceof Videojuego) {
                filtrados.add(r);
            }
        }
        return filtrados;
    }

    // Consulta adicional 1: Recursos por año de publicación
    public List<Recurso> buscarRecursosPorAnio(int anio) {
        List<Recurso> filtrados = new ArrayList<>();
        for (Recurso r : listaRecursos) {
            if (r.getAnio() == anio) {
                filtrados.add(r);
            }
        }
        return filtrados;
    }
}

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
	
    // Colección para almacenar los recursos en memoria durante la ejecución
    private List<Recurso> listaRecursos;

    public GestionRecursos() {
        this.listaRecursos = new ArrayList<>();
    }

    // Devuelve la lista completa (esencial para los otros compañeros)
    public List<Recurso> getListaRecursos() {
        return listaRecursos;
    }

    // CREAR (Comprueba duplicados antes de insertar)
    public void crearRecurso(Recurso recurso) throws DuplicadoRecursoException {
    	
        try {
        	// Comprobar que el recurso no exista previamente por identificador
			buscarRecursoPorIdentificador(recurso.getIdentificador());
			// el metodo buscar recurso por identificar lanza excepcion en caso de no encontrarlo.
			// si llega aqui, es porque el recurso existe....
			
			throw new DuplicadoRecursoException("Error: Ya existe un recurso con el identificador: " + recurso.getIdentificador());
        } catch (RecursoNoEncontradoException e) {
			// Creariamos el recurso nuevo...
        	this.listaRecursos.add(recurso);
		}
    }

    // Listar recursos
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

    // Buscar recurso por identificador
    public Recurso buscarRecursoPorIdentificador(String identificador) throws RecursoNoEncontradoException {
        for (Recurso r : listaRecursos) {
            if (r.getIdentificador().equalsIgnoreCase(identificador)) {
                return r;
            }
        }
        
        throw new RecursoNoEncontradoException("Error: El recurso con identificador '" + identificador + "' no existe.");
    }

    // Eliminar
    public void eliminarRecurso(String identificador) throws RecursoNoEncontradoException {
    
    	try {
			Recurso r = buscarRecursoPorIdentificador(identificador);
			// si llego aqui, es que existe... y lo borro.
			this.listaRecursos.remove(r);
		} catch (RecursoNoEncontradoException e) {
			// en caso de no encontrarse, propago la excepcion
			throw e;
		}
    }

    // Modificar
    public void modificarLibro(String identificador, String titulo, int anio, String autor, int paginas) throws RecursoNoEncontradoException, RecursoTipoInvalidoExcepcion {
    	Recurso r = buscarRecursoPorIdentificador(identificador);
    	if (r instanceof Libro) {
    		r.setTitulo(titulo);
    		r.setAnio(anio);
    		((Libro) r).setAutor(autor);
    		((Libro) r).setPaginas(paginas);
    	}
    	else {
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
    	}
    	else {
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
    	}
    	else {
    		throw new RecursoTipoInvalidoExcepcion("Error: El recurso a modificar no es una pelicula");
    	}
    }
    
    // Consultar si un recurso esta disponible o no
    public EstadoRecurso obtenerEstadoRecurso(String identificador) throws RecursoNoEncontradoException {
    	Recurso r = this.buscarRecursoPorIdentificador(identificador);
    	return r.getEstado();
    }
}
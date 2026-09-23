package service;

import java.util.ArrayList;
import java.util.List;

import model.Recurso;
import service.exceptions.DuplicadoRecursoException;
import service.exceptions.RecursoNoEncontradoException;

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
			Recurso r = buscarRecursoPorIdentificador(recurso.getIdentificador());
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
    
    // TO-DO:
    // Modificar
    // Consultar si un recurso esta disponible o no
}
package main;

import service.GestionRecursos;
import service.PruebaRecursos;

public class Main {

	public static void main(String[] args) {
		System.out.println("=== INICIANDO SISTEMA DE BIBLIOTECA MULTIMEDIA ===");
	        
        // Creamos el gestor de recursos
		GestionRecursos gestorRecursos = new GestionRecursos();
		
        // Llamamos al menú interactivo de pruebas pasándole el gestor
        PruebaRecursos.ejecutarMenu(gestorRecursos);
	        
        System.out.println("=== APLICACIÓN FINALIZADA ===");
	}

}

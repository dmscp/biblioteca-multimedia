package main;

import service.GestionUsuarios;
import service.GestionRecursos;
import service.GestionPrestamos;
import service.PersistenciaCSV; 
import view.MenuUsuarios;
import view.MenuRecursos;
import view.MenuPrestamos;
import view.MenuConsultas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO SISTEMA DE BIBLIOTECA MULTIMEDIA ===");

        GestionUsuarios gestorUsuarios = new GestionUsuarios();
        GestionRecursos gestorRecursos = new GestionRecursos();
        GestionPrestamos gestorPrestamos = new GestionPrestamos(gestorUsuarios, gestorRecursos);
        
        // Carga automática inicial
        gestorUsuarios.setListaUsuarios(PersistenciaCSV.cargarUsuarios());
        gestorRecursos.setListaRecursos(PersistenciaCSV.cargarRecursos());
        gestorPrestamos.setListaPrestamos(PersistenciaCSV.cargarPrestamos(gestorUsuarios.getListaUsuarios(), gestorRecursos.getListaRecursos()));

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestión de Usuarios");
            System.out.println("2. Gestión de Recursos");
            System.out.println("3. Préstamos y Devoluciones");
            System.out.println("4. Módulo de Búsquedas y Consultas");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        MenuUsuarios.ejecutarMenu(gestorUsuarios);
                        break;
                    case 2:
                        MenuRecursos.ejecutarMenu(gestorRecursos);
                        break;
                    case 3:
                        MenuPrestamos.ejecutarMenu(gestorPrestamos);
                        break;
                    case 4:
                        MenuConsultas.ejecutarMenu(gestorPrestamos);
                        break;
                    case 5:
                        System.out.println("Guardando datos en los archivos CSV...");
                        PersistenciaCSV.guardarUsuarios(gestorUsuarios.getListaUsuarios());
                        PersistenciaCSV.guardarRecursos(gestorRecursos.getListaRecursos());
                        PersistenciaCSV.guardarPrestamos(gestorPrestamos.getListaPrestamos());
                        System.out.println("Saliendo de la aplicación...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduce un número válido.");
            }
        } while (opcion != 5);

        System.out.println("=== APLICACIÓN FINALIZADA ===");
    }
}

package main;

import service.GestionUsuarios;
import service.PruebaUsuarios;
import service.GestionRecursos;
import service.PruebaRecursos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO SISTEMA DE BIBLIOTECA MULTIMEDIA ===");

        // Instanciamos los gestores una sola vez para que los datos persistan entre menús
        GestionUsuarios gestorUsuarios = new GestionUsuarios();
        GestionRecursos gestorRecursos = new GestionRecursos();

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestión de Usuarios");
            System.out.println("2. Gestión de Recursos");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        PruebaUsuarios.ejecutarMenu(gestorUsuarios);
                        break;
                    case 2:
                        PruebaRecursos.ejecutarMenu(gestorRecursos);
                        break;
                    case 3:
                        System.out.println("Saliendo de la aplicación...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduce un número válido.");
            }
        } while (opcion != 3);

        System.out.println("=== APLICACIÓN FINALIZADA ===");
    }

}
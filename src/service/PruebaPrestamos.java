package service;

import model.Prestamo;
import exceptions.DuplicadoRecursoException;
import exceptions.RecursoNoEncontradoException;
import exceptions.RecursoTipoInvalidoExcepcion;
import exceptions.UsuarioDuplicadoException;
import exceptions.UsuarioNoEncontradoException;



import java.util.Scanner;

public class PruebaPrestamos {
    
	public static void ejecutarMenu(GestionPrestamos gestor) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- GESTIÓN DE PRÉSTAMOS ---");
            System.out.println("1. Registrar préstamo");
            System.out.println("2. Listar préstamos");
            System.out.println("3. Buscar préstamo por título");
            System.out.println("4. Volver / Salir");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine()); 

                switch (opcion) {
                    case 1:
                        
                        break;

                    case 2:
                        
                        break;

                    case 3:
                        
                        break;

                    case 4:
                        System.out.println("Saliendo del módulo de recursos...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduce un número válido.");
            } catch (DuplicadoRecursoException | RecursoNoEncontradoException | RecursoTipoInvalidoExcepcion e) {
                System.out.println(e.getMessage()); // Muestra el mensaje limpio de la excepción
            }
        } while (opcion != 4);
    }
	
}
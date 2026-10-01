package view;

import service.GestionPrestamos;
import model.Prestamo;
import exceptions.*;
import java.util.Scanner;

public class MenuPrestamos {
    public static void ejecutarMenu(GestionPrestamos gestor) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- TRANSACCIONES DE PRÉSTAMOS ---");
            System.out.println("1. Registrar préstamo");
            System.out.println("2. Registrar devolución");
            System.out.println("3. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print("ID del usuario: ");
                        String idUsuario = scanner.nextLine();
                        System.out.print("ID del recurso: ");
                        String idRecurso = scanner.nextLine();

                        Prestamo p = gestor.prestarRecurso(idUsuario, idRecurso);
                        System.out.println("Préstamo registrado con éxito.\n" + p);
                        break;
                    case 2:
                        System.out.print("ID del préstamo a devolver: ");
                        String idDevolver = scanner.nextLine();
                        if (gestor.devolverRecurso(idDevolver)) {
                            System.out.println("Devolución registrada. Recurso disponible.");
                        } else {
                            System.out.println("No se encontró ningún préstamo activo con ese ID.");
                        }
                        break;
                    case 3:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduce un número válido.");
            } catch (UsuarioNoEncontradoException | RecursoNoEncontradoException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        } while (opcion != 3);
    }
}

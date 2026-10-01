package view;

import service.GestionPrestamos;
import model.*;
import exceptions.UsuarioNoEncontradoException;
import java.util.List;
import java.util.Scanner;

public class MenuConsultas {
    public static void ejecutarMenu(GestionPrestamos gestor) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- MÓDULO DE BÚSQUEDAS Y CONSULTAS ---");
            System.out.println("1. Histórico completo de préstamos");
            System.out.println("2. Listar préstamos activos");
            System.out.println("3. Buscar préstamos por título de recurso");
            System.out.println("4. Ver préstamos de un usuario");
            System.out.println("5. Filtrar catálogo por disponibilidad");
            System.out.println("6. Filtrar catálogo por tipo");
            System.out.println("7. Buscar recursos por año");
            System.out.println("8. Ver préstamos devueltos");
            System.out.println("9. Volver al Menú Principal");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1: mostrar(gestor.getPrestamos()); break;
                    case 2: mostrar(gestor.obtenerPrestamosActivos()); break;
                    case 3:
                        System.out.print("Título a buscar: ");
                        mostrar(gestor.buscarPrestamosPorTitulo(scanner.nextLine()));
                        break;
                    case 4:
                        System.out.print("ID del usuario: ");
                        mostrar(gestor.obtenerPrestamosDeUsuario(scanner.nextLine()));
                        break;
                    case 5:
                        System.out.print("1. Disponibles | 2. Prestados: ");
                        int est = Integer.parseInt(scanner.nextLine());
                        for (Recurso r : gestor.obtenerRecursosPorEstado(est == 1)) System.out.println(r);
                        break;
                    case 6:
                        System.out.println("¿Qué tipo de recurso deseas filtrar? (1. Libro | 2. Película | 3. Videojuego)");
                        int tipoSeleccionado = Integer.parseInt(scanner.nextLine());
                        System.out.println("\n=== RECURSOS FILTRADOS POR TIPO ===");
                        int tCount = 0;
                        for (Recurso r : gestor.obtenerRecursosPorEstado(true)) {
                            if (check(r, tipoSeleccionado)) { 
                                System.out.println(r); 
                                tCount++; 
                            }
                        }
                        for (Recurso r : gestor.obtenerRecursosPorEstado(false)) {
                            if (check(r, tipoSeleccionado)) { 
                                System.out.println(r); 
                                tCount++; 
                            }
                        }
                        if (tCount == 0) {
                            System.out.println("No hay recursos registrados de ese tipo.");
                        }
                        break;
                    case 7:
                        System.out.print("Año: ");
                        int anio = Integer.parseInt(scanner.nextLine());
                        int aCount = 0;
                        for (Recurso r : gestor.obtenerRecursosPorEstado(true)) {
                            if (r.getAnio() == anio) { System.out.println(r); aCount++; }
                        }
                        for (Recurso r : gestor.obtenerRecursosPorEstado(false)) {
                            if (r.getAnio() == anio) { System.out.println(r); aCount++; }
                        }
                        if (aCount == 0) System.out.println("Sin registros en ese año.");
                        break;
                    case 8:
                        mostrar(gestor.obtenerPrestamosDevueltos());
                        break;
                    case 9:
                        System.out.println("Volviendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Introduce un número válido.");
            } catch (UsuarioNoEncontradoException e) {
                System.out.println(e.getMessage());
            }
        } while (opcion != 9);
    }

    private static void mostrar(List<Prestamo> lista) {
        if (lista.isEmpty()) System.out.println("No hay registros.");
        else { for (Prestamo p : lista) System.out.println(p); }
    }

    private static boolean check(Recurso r, int t) {
        return (t == 1 && r instanceof Libro) ||
               (t == 2 && r instanceof Pelicula) ||
               (t == 3 && r instanceof Videojuego);
    }

}

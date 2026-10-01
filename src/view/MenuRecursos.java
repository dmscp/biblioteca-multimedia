package view;

import service.GestionRecursos;
import model.*;
import exceptions.*;
import java.util.Scanner;

public class MenuRecursos {
    public static void ejecutarMenu(GestionRecursos gestor) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- GESTIÓN DE RECURSOS ---");
            System.out.println("1. Registrar nuevo recurso");
            System.out.println("2. Listar catálogo completo");
            System.out.println("3. Buscar recurso por ID");
            System.out.println("4. Modificar recurso");
            System.out.println("5. Eliminar recurso");
            System.out.println("6. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("¿Qué tipo de recurso deseas registrar? (1. Libro | 2. Película | 3. Videojuego)");
                        int tipo = Integer.parseInt(scanner.nextLine());
                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Año de publicación: ");
                        int anio = Integer.parseInt(scanner.nextLine());

                        if (tipo == 1) {
                            System.out.print("Autor: "); String autor = scanner.nextLine();
                            System.out.print("Páginas: "); int paginas = Integer.parseInt(scanner.nextLine());
                            gestor.crearRecurso(new Libro(titulo, anio, autor, paginas));
                        } else if (tipo == 2) {
                            System.out.print("Director: "); String director = scanner.nextLine();
                            System.out.print("Duración (min): "); int duracion = Integer.parseInt(scanner.nextLine());
                            gestor.crearRecurso(new Pelicula(titulo, anio, director, duracion));
                        } else if (tipo == 3) {
                            System.out.print("Plataforma: "); String plat = scanner.nextLine();
                            System.out.print("PEGI: "); int pegi = Integer.parseInt(scanner.nextLine());
                            gestor.crearRecurso(new Videojuego(titulo, anio, plat, pegi));
                        }
                        System.out.println("Recurso registrado con éxito.");
                        break;

                    case 2:
                        gestor.listarRecursos();
                        break;

                    case 3:
                        System.out.print("Introduce el ID del recurso: ");
                        System.out.println(gestor.buscarRecursoPorIdentificador(scanner.nextLine()));
                        break;

                    case 4:
                        System.out.print("Introduce el ID del recurso a modificar: ");
                        String idMod = scanner.nextLine();
                        
                        try {
                            Recurso r = gestor.buscarRecursoPorIdentificador(idMod);
                            
                            System.out.print("Nuevo título [" + r.getTitulo() + "]: ");
                            String nT = scanner.nextLine();
                            System.out.print("Nuevo año [" + r.getAnio() + "]: ");
                            int nA = Integer.parseInt(scanner.nextLine());

                            if (r instanceof Libro) {
                                System.out.print("Nuevo autor: "); String nAut = scanner.nextLine();
                                System.out.print("Nuevas páginas: "); int nPag = Integer.parseInt(scanner.nextLine());
                                gestor.modificarLibro(idMod, nT, nA, nAut, nPag);
                            } else if (r instanceof Pelicula) {
                                System.out.print("Nuevo director: "); String nDir = scanner.nextLine();
                                System.out.print("Nueva duración (min): "); int nDur = Integer.parseInt(scanner.nextLine());
                                gestor.modificarPelicula(idMod, nT, nA, nDir, nDur);
                            } else if (r instanceof Videojuego) {
                                System.out.print("Nueva plataforma: "); String nPlat = scanner.nextLine();
                                System.out.print("Nuevo PEGI: "); int nPegi = Integer.parseInt(scanner.nextLine());
                                gestor.modificarVideojuego(idMod, nT, nA, nPlat, nPegi);
                            }
                            System.out.println("Recurso modificado correctamente.");
                            
                        } catch (RecursoNoEncontradoException | RecursoTipoInvalidoExcepcion e) {
                            System.out.println(e.getMessage());
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Formato de número incorrecto en el año o campos numéricos.");
                        }
                        break;


                    case 5:
                        System.out.print("Introduce el ID del recurso a eliminar: ");
                        gestor.eliminarRecurso(scanner.nextLine());
                        System.out.println("Recurso eliminado con éxito.");
                        break;
                    case 6:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduce un número válido.");
            } catch (DuplicadoRecursoException | RecursoNoEncontradoException e) {
                System.out.println(e.getMessage());
            }
        } while (opcion != 6);
    }
}

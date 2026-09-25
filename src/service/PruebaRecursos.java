package service;

import model.Recurso;
import model.Libro;
import model.Pelicula;
import model.Videojuego;
import model.EstadoRecurso;
import exceptions.DuplicadoRecursoException;
import exceptions.RecursoNoEncontradoException;
import exceptions.RecursoTipoInvalidoExcepcion;

import java.util.Scanner;

public class PruebaRecursos {
    
	public static void ejecutarMenu(GestionRecursos gestor) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- GESTIÓN DE RECURSOS (PRUEBA CRUD) ---");
            System.out.println("1. Registrar Libro");
            System.out.println("2. Registrar Película");
            System.out.println("3. Registrar Videojuego");
            System.out.println("4. Listar Recursos");
            System.out.println("5. Buscar Recurso por Identificador");
            System.out.println("6. Modificar Libro");
            System.out.println("7. Modificar Película");
            System.out.println("8. Modificar Videojuego");
            System.out.println("9. Eliminar Recurso");
            System.out.println("10. Consultar Estado de un Recurso");
            System.out.println("11. Volver / Salir");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine()); // Evita errores de escáner

                switch (opcion) {
                    case 1:
                        System.out.print("Introduce ID: ");
                        String idLibro = scanner.nextLine();
                        System.out.print("Introduce Título: ");
                        String tituloLibro = scanner.nextLine();
                        System.out.print("Introduce Año: ");
                        int anioLibro = Integer.parseInt(scanner.nextLine());
                        System.out.print("Introduce Autor: ");
                        String autor = scanner.nextLine();
                        System.out.print("Introduce Páginas: ");
                        int paginas = Integer.parseInt(scanner.nextLine());

                        gestor.crearRecurso(new Libro(idLibro, tituloLibro, anioLibro, autor, paginas));
                        System.out.println("Libro creado con éxito.");
                        break;

                    case 2:
                        System.out.print("Introduce ID: ");
                        String idPelicula = scanner.nextLine();
                        System.out.print("Introduce Título: ");
                        String tituloPelicula = scanner.nextLine();
                        System.out.print("Introduce Año: ");
                        int anioPelicula = Integer.parseInt(scanner.nextLine());
                        System.out.print("Introduce Director: ");
                        String director = scanner.nextLine();
                        System.out.print("Introduce Duración (minutos): ");
                        int duracion = Integer.parseInt(scanner.nextLine());

                        gestor.crearRecurso(new Pelicula(idPelicula, tituloPelicula, anioPelicula, director, duracion));
                        System.out.println("Película creada con éxito.");
                        break;

                    case 3:
                        System.out.print("Introduce ID: ");
                        String idVideojuego = scanner.nextLine();
                        System.out.print("Introduce Título: ");
                        String tituloVideojuego = scanner.nextLine();
                        System.out.print("Introduce Año: ");
                        int anioVideojuego = Integer.parseInt(scanner.nextLine());
                        System.out.print("Introduce Plataforma: ");
                        String plataforma = scanner.nextLine();
                        System.out.print("Introduce PEGI: ");
                        int pegi = Integer.parseInt(scanner.nextLine());

                        gestor.crearRecurso(new Videojuego(idVideojuego, tituloVideojuego, anioVideojuego, plataforma, pegi));
                        System.out.println("Videojuego creado con éxito.");
                        break;

                    case 4:
                        gestor.listarRecursos();
                        break;

                    case 5:
                        System.out.print("Introduce el ID a buscar: ");
                        String idBuscar = scanner.nextLine();
                        Recurso encontrado = gestor.buscarRecursoPorIdentificador(idBuscar);
                        System.out.println("Recurso encontrado -> " + encontrado);
                        break;

                    case 6:
                        System.out.print("Introduce el ID del libro a modificar: ");
                        String idModLibro = scanner.nextLine();
                        System.out.print("Nuevo Título: ");
                        String nTituloLibro = scanner.nextLine();
                        System.out.print("Nuevo Año: ");
                        int nAnioLibro = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nuevo Autor: ");
                        String nAutor = scanner.nextLine();
                        System.out.print("Nuevas Páginas: ");
                        int nPaginas = Integer.parseInt(scanner.nextLine());

                        gestor.modificarLibro(idModLibro, nTituloLibro, nAnioLibro, nAutor, nPaginas);
                        System.out.println("Libro modificado con éxito.");
                        break;

                    case 7:
                        System.out.print("Introduce el ID de la película a modificar: ");
                        String idModPelicula = scanner.nextLine();
                        System.out.print("Nuevo Título: ");
                        String nTituloPelicula = scanner.nextLine();
                        System.out.print("Nuevo Año: ");
                        int nAnioPelicula = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nuevo Director: ");
                        String nDirector = scanner.nextLine();
                        System.out.print("Nueva Duración (minutos): ");
                        int nDuracion = Integer.parseInt(scanner.nextLine());

                        gestor.modificarPelicula(idModPelicula, nTituloPelicula, nAnioPelicula, nDirector, nDuracion);
                        System.out.println("Película modificada con éxito.");
                        break;

                    case 8:
                        System.out.print("Introduce el ID del videojuego a modificar: ");
                        String idModVideojuego = scanner.nextLine();
                        System.out.print("Nuevo Título: ");
                        String nTituloVideojuego = scanner.nextLine();
                        System.out.print("Nuevo Año: ");
                        int nAnioVideojuego = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nueva Plataforma: ");
                        String nPlataforma = scanner.nextLine();
                        System.out.print("Nuevo PEGI: ");
                        int nPegi = Integer.parseInt(scanner.nextLine());

                        gestor.modificarVideojuego(idModVideojuego, nTituloVideojuego, nAnioVideojuego, nPlataforma, nPegi);
                        System.out.println("Videojuego modificado con éxito.");
                        break;

                    case 9:
                        System.out.print("Introduce el ID del recurso a eliminar: ");
                        String idEli = scanner.nextLine();
                        gestor.eliminarRecurso(idEli);
                        System.out.println("Recurso eliminado con éxito.");
                        break;

                    case 10:
                        System.out.print("Introduce el ID del recurso a consultar: ");
                        String idEstado = scanner.nextLine();
                        EstadoRecurso estado = gestor.obtenerEstadoRecurso(idEstado);
                        System.out.println("Estado del recurso -> " + estado);
                        break;

                    case 11:
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
        } while (opcion != 11);
    }
	
}
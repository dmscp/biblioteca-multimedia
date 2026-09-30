package service;

import exceptions.RecursoNoEncontradoException;
import exceptions.UsuarioNoEncontradoException;
import model.Prestamo;
import model.Recurso;

import java.util.List;
import java.util.Scanner;

public class PruebaPrestamos {

    public static void ejecutarMenu(GestionPrestamos gestor) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- GESTIÓN DE PRÉSTAMOS ---");
            System.out.println("1. Registrar préstamo");
            System.out.println("2. Registrar devolución");
            System.out.println("3. Listar préstamos (Todos / Activos)");
            System.out.println("4. Buscar préstamo por título");
            System.out.println("5. Ver préstamos de un usuario");
            System.out.println("6. Ver recursos disponibles o prestados");
            System.out.println("7. Filtrar recursos por TIPO (Libro/Película/Videojuego)");
            System.out.println("8. Buscar recursos por año de publicación (Consulta Extra 1)");
            System.out.println("9. Ver historial de préstamos DEVUELTOS (Consulta Extra 2)");
            System.out.println("10. Volver / Salir");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print("ID del préstamo (número): ");
                        int idPrestamo = Integer.parseInt(scanner.nextLine());
                        System.out.print("ID del usuario (UUID): ");
                        String idUsuario = scanner.nextLine();
                        System.out.print("ID del recurso (UUID): ");
                        String idRecurso = scanner.nextLine();

                        Prestamo prestamo = gestor.prestarRecurso(idPrestamo, idUsuario, idRecurso);
                        System.out.println("¡Préstamo registrado con éxito!");
                        System.out.println(prestamo);
                        break;

                    case 2:
                        System.out.print("ID del préstamo a devolver (número): ");
                        int idDevolver = Integer.parseInt(scanner.nextLine());
                        boolean exito = gestor.devolverRecurso(idDevolver);
                        if (exito) {
                            System.out.println("Devolución registrada correctamente. Recurso de nuevo disponible.");
                        } else {
                            System.out.println("No se encontró ningún préstamo activo con ese ID.");
                        }
                        break;

                    case 3:
                        System.out.println("1. Listar TODOS los préstamos");
                        System.out.println("2. Listar solo préstamos ACTIVOS");
                        System.out.print("Selecciona: ");
                        int subOp = Integer.parseInt(scanner.nextLine());

                        List<Prestamo> lista = (subOp == 2) ? gestor.obtenerPrestamosActivos() : gestor.getPrestamos();
                        if (lista.isEmpty()) {
                            System.out.println("No hay préstamos para mostrar.");
                        } else {
                            for (Prestamo p : lista) {
                                System.out.println(p);
                            }
                        }
                        break;

                    case 4:
                        System.out.print("Introduce el título del recurso a buscar: ");
                        String titulo = scanner.nextLine();
                        List<Prestamo> porTitulo = gestor.buscarPrestamosPorTitulo(titulo);
                        if (porTitulo.isEmpty()) {
                            System.out.println("No se encontraron préstamos con ese título.");
                        } else {
                            for (Prestamo p : porTitulo) {
                                System.out.println(p);
                            }
                        }
                        break;

                    case 5:
                        System.out.print("ID del usuario (UUID): ");
                        String idUsuarioConsulta = scanner.nextLine();
                        List<Prestamo> porUsuario = gestor.obtenerPrestamosDeUsuario(idUsuarioConsulta);
                        if (porUsuario.isEmpty()) {
                            System.out.println("Este usuario no tiene ningún préstamo registrado.");
                        } else {
                            for (Prestamo p : porUsuario) {
                                System.out.println(p);
                            }
                        }
                        break;

                    case 6:
                        System.out.println("1. Recursos DISPONIBLES");
                        System.out.println("2. Recursos PRESTADOS");
                        System.out.print("Selecciona: ");
                        int opEstado = Integer.parseInt(scanner.nextLine());

                        List<Recurso> recursosEstado = gestor.obtenerRecursosPorEstado(opEstado == 1);
                        if (recursosEstado.isEmpty()) {
                            System.out.println("No hay recursos en ese estado.");
                        } else {
                            for (Recurso r : recursosEstado) {
                                System.out.println(r);
                            }
                        }
                        break;

                    case 7:
                        System.out.print("Introduce el tipo a filtrar (LIBRO, PELICULA, VIDEOJUEGO): ");
                        String tipoFiltro = scanner.nextLine().trim().toUpperCase();
                        System.out.println("\n=== RECURSOS FILTRADOS POR TIPO ===");
                        int encontradosTipo = 0;

                        // Evaluamos tanto los disponibles como prestados mediante el puente del gestor
                        for (Recurso r : gestor.obtenerRecursosPorEstado(true)) {
                            if ((tipoFiltro.equals("LIBRO") && r instanceof model.Libro) ||
                                (tipoFiltro.equals("PELICULA") && r instanceof model.Pelicula) ||
                                (tipoFiltro.equals("VIDEOJUEGO") && r instanceof model.Videojuego)) {
                                System.out.println(r);
                                encontradosTipo++;
                            }
                        }
                        for (Recurso r : gestor.obtenerRecursosPorEstado(false)) {
                            if ((tipoFiltro.equals("LIBRO") && r instanceof model.Libro) ||
                                (tipoFiltro.equals("PELICULA") && r instanceof model.Pelicula) ||
                                (tipoFiltro.equals("VIDEOJUEGO") && r instanceof model.Videojuego)) {
                                System.out.println(r);
                                encontradosTipo++;
                            }
                        }
                        if (encontradosTipo == 0) {
                            System.out.println("No se encontraron recursos de ese tipo.");
                        }
                        break;

                    case 8:
                        System.out.print("Introduce el año de publicación a buscar: ");
                        int anioBusqueda = Integer.parseInt(scanner.nextLine());
                        System.out.println("\n=== RECURSOS DEL AÑO " + anioBusqueda + " ===");
                        int encontradosAnio = 0;

                        for (Recurso r : gestor.obtenerRecursosPorEstado(true)) {
                            if (r.getAnio() == anioBusqueda) {
                                System.out.println(r);
                                encontradosAnio++;
                            }
                        }
                        for (Recurso r : gestor.obtenerRecursosPorEstado(false)) {
                            if (r.getAnio() == anioBusqueda) {
                                System.out.println(r);
                                encontradosAnio++;
                            }
                        }
                        if (encontradosAnio == 0) {
                            System.out.println("No hay recursos registrados en ese año.");
                        }
                        break;

                    case 9:
                        System.out.println("\n=== HISTORIAL DE PRÉSTAMOS DEVUELTOS ===");
                        List<Prestamo> devueltos = gestor.obtenerPrestamosDevueltos();
                        if (devueltos.isEmpty()) {
                            System.out.println("No hay registros de devoluciones en el sistema.");
                        } else {
                            for (Prestamo p : devueltos) {
                                System.out.println(p);
                            }
                        }
                        break;

                    case 10:
                        System.out.println("Saliendo del módulo de préstamos...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduce un número válido.");
            } catch (UsuarioNoEncontradoException | RecursoNoEncontradoException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        } while (opcion != 10);
    }
}

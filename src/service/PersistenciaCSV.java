package service;

import model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaCSV {
    private static final String ARCHIVO_USUARIOS = "src/datos/usuarios.csv";
    private static final String ARCHIVO_RECURSOS = "src/datos/recursos.csv";
    private static final String SEPARADOR = ";";

    // ==========================================
    //          PERSISTENCIA DE USUARIOS
    // ==========================================

    public static void guardarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_USUARIOS))) {
            for (Usuario u : usuarios) {
                String linea = u.getId() + SEPARADOR + u.getNombre() + SEPARADOR + u.getCorreoElectronico();
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    public static List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        File file = new File(ARCHIVO_USUARIOS);
        if (!file.exists()) return usuarios;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);
                if (datos.length == 3) {
                    // Constructor: Usuario(id, nombre, correoElectronico)
                    usuarios.add(new Usuario(datos[0], datos[1], datos[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    // ==========================================
    //          PERSISTENCIA DE RECURSOS
    // ==========================================

    public static void guardarRecursos(List<Recurso> recursos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_RECURSOS))) {
            for (Recurso r : recursos) {
                String tipo = "";
                String espec1 = "";
                String espec2 = "";

                // Detectamos la subclase para extraer sus campos específicos
                if (r instanceof Libro) {
                    tipo = "LIBRO";
                    espec1 = ((Libro) r).getAutor();
                    espec2 = String.valueOf(((Libro) r).getPaginas());
                } else if (r instanceof Pelicula) {
                    tipo = "PELICULA";
                    espec1 = ((Pelicula) r).getDirector();
                    espec2 = String.valueOf(((Pelicula) r).getDuracionMinutos());
                } else if (r instanceof Videojuego) {
                    tipo = "VIDEOJUEGO";
                    espec1 = ((Videojuego) r).getPlataforma();
                    espec2 = String.valueOf(((Videojuego) r).getPegi());
                }

                // Estructura: TIPO;identificador;titulo;anio;especifico1;especifico2
                String linea = tipo + SEPARADOR + 
                               r.getIdentificador() + SEPARADOR + 
                               r.getTitulo() + SEPARADOR + 
                               r.getAnio() + SEPARADOR + 
                               espec1 + SEPARADOR + 
                               espec2;
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar recursos: " + e.getMessage());
        }
    }

    public static List<Recurso> cargarRecursos() {
        List<Recurso> recursos = new ArrayList<>();
        File file = new File(ARCHIVO_RECURSOS);
        if (!file.exists()) return recursos;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);
                if (datos.length < 6) continue;

                String tipo = datos[0];
                String id = datos[1];
                String titulo = datos[2];
                int anio = Integer.parseInt(datos[3]);

                // Instanciamos el objeto correcto basándonos en la primera columna
                switch (tipo) {
                    case "LIBRO":
                        // Libro(identificador, titulo, anio, autor, paginas)
                        recursos.add(new Libro(id, titulo, anio, datos[4], Integer.parseInt(datos[5])));
                        break;
                    case "PELICULA":
                        // Pelicula(identificador, titulo, anio, director, duracionMinutos)
                        recursos.add(new Pelicula(id, titulo, anio, datos[4], Integer.parseInt(datos[5])));
                        break;
                    case "VIDEOJUEGO":
                        // Videojuego(identificador, titulo, anio, plataforma, pegi)
                        recursos.add(new Videojuego(id, titulo, anio, datos[4], Integer.parseInt(datos[5])));
                        break;
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar recursos: " + e.getMessage());
        }
        return recursos;
    }
}


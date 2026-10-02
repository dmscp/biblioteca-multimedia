package service;

import model.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaCSV {
    private static final String ARCHIVO_USUARIOS = "src/datos/usuarios.csv";
    private static final String ARCHIVO_RECURSOS = "src/datos/recursos.csv";
    private static final String ARCHIVO_PRESTAMOS = "src/datos/prestamos.csv";
    private static final String SEPARADOR = ";";

    //PERSISTENCIA DE USUARIOS
    public static void guardarUsuarios(List<Usuario> usuarios) {

        File file = new File(ARCHIVO_USUARIOS);
        File carpeta = file.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
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
                    usuarios.add(new Usuario(datos[0], datos[1], datos[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    //PERSISTENCIA DE RECURSOS
    public static void guardarRecursos(List<Recurso> recursos) {

        File file = new File(ARCHIVO_RECURSOS);
        File carpeta = file.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Recurso r : recursos) {
                String tipo = "";
                String espec1 = "";
                String espec2 = "";

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

                String linea = tipo + SEPARADOR + 
                               r.getIdentificador() + SEPARADOR + 
                               r.getTitulo() + SEPARADOR + 
                               r.getAnio() + SEPARADOR + 
                               espec1 + SEPARADOR + 
                               espec2 + SEPARADOR +
                               r.getEstado().name();
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
                if (datos.length < 7) continue;

                String tipo = datos[0];
                String id = datos[1];
                String titulo = datos[2];
                int anio = Integer.parseInt(datos[3]);
                EstadoRecurso estado = EstadoRecurso.valueOf(datos[6]);

                switch (tipo) {
                    case "LIBRO":
                        recursos.add(new Libro(id, titulo, anio, datos[4], Integer.parseInt(datos[5]), estado));
                        break;
                    case "PELICULA":
                        recursos.add(new Pelicula(id, titulo, anio, datos[4], Integer.parseInt(datos[5]), estado));
                        break;
                    case "VIDEOJUEGO":
                        recursos.add(new Videojuego(id, titulo, anio, datos[4], Integer.parseInt(datos[5]), estado));
                        break;
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar recursos: " + e.getMessage());
        }
        return recursos;
    }

    //PERSISTENCIA DE PRÉSTAMOS
    public static void guardarPrestamos(List<Prestamo> prestamos) {
        File file = new File(ARCHIVO_PRESTAMOS);
        File carpeta = file.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Prestamo p : prestamos) {
                String fechaDev = (p.getFechaDevolucion() != null) ? p.getFechaDevolucion().toString() : "null";
                String linea = p.getIdPrestamo() + SEPARADOR + 
                               p.getUsuario().getId() + SEPARADOR + 
                               p.getRecurso().getIdentificador() + SEPARADOR + 
                               p.getFechaPrestamo() + SEPARADOR + 
                               fechaDev + SEPARADOR + 
                               p.isActivo();
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar préstamos: " + e.getMessage());
        }
    }

    public static List<Prestamo> cargarPrestamos(List<Usuario> usuarios, List<Recurso> recursos) {
        List<Prestamo> prestamos = new ArrayList<>();
        File file = new File(ARCHIVO_PRESTAMOS);
        if (!file.exists()) return prestamos;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);
                if (datos.length < 6) continue;

                String idPrestamo = datos[0];
                String idUsuario = datos[1];
                String idRecurso = datos[2];
                LocalDate fechaPrestamo = LocalDate.parse(datos[3]);
                String strFechaDev = datos[4];
                boolean activo = Boolean.parseBoolean(datos[5]);

                Usuario usuarioObj = null;
                for (Usuario u : usuarios) {
                    if (u.getId().equalsIgnoreCase(idUsuario)) {
                        usuarioObj = u;
                        break;
                    }
                }

                Recurso recursoObj = null;
                for (Recurso r : recursos) {
                    if (r.getIdentificador().equalsIgnoreCase(idRecurso)) {
                        recursoObj = r;
                        break;
                    }
                }

                if (usuarioObj != null && recursoObj != null) {
                    Prestamo p = new Prestamo(idPrestamo, usuarioObj, recursoObj);
                    p.setFechaPrestamo(fechaPrestamo);
                    if (!strFechaDev.equals("null")) {
                        p.setFechaDevolucion(LocalDate.parse(strFechaDev));
                    }
                    p.setActivo(activo);
                    
                    if (activo) {
                        recursoObj.setEstado(EstadoRecurso.PRESTADO);
                    }
                    
                    prestamos.add(p);
                }
            }
        } catch (IOException | RuntimeException e) {
            System.err.println("Error al cargar préstamos: " + e.getMessage());
        }
        return prestamos;
    }
}
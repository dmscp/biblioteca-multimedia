package model;

import java.util.UUID;

public class Usuario {
    private final String id;
    private String nombre;
    private String correoElectronico;

    // CONSTRUCTOR PRINCIPAL: Para crear usuarios nuevos (Genera el ID solo)
    public Usuario(String nombre, String correoElectronico) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
    }

    // CONSTRUCTOR DE PERSISTENCIA: Usado por PersistenciaCSV para reconstruir el objeto
    public Usuario(String id, String nombre, String correoElectronico) {
        this.id = id;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Email: " + correoElectronico;
    }
}

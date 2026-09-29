package model;

public class Usuario {
    private int id;
    private String nombre;
    private String correoElectronico;

    // Constructor completo
    public Usuario(int id, String nombre, String correoElectronico) {
        this.id = id;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    // Método toString optimizado para listados limpios en consola
    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Email: " + correoElectronico;
    }
}


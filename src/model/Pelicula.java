package model;

public class Pelicula extends Recurso {

    private String director;
    private int duracionMinutos;

    public Pelicula(String identificador, String titulo, int anio, String director, int duracionMinutos) {
        super(identificador, titulo, anio);
        this.director = director;
        this.duracionMinutos = duracionMinutos;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    @Override
    public String getDetallesEspecificos() {
        return String.format("Director: %s, Duración: %d min", director, duracionMinutos);
    }
}
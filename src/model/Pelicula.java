package model;

public class Pelicula extends Recurso {
	private String director;
	private int duracionMinutos;

    //CONSTRUCTOR PRINCIPAL
	public Pelicula(String titulo, int anio, String director, int duracionMinutos) {
		super(titulo, anio);
		this.director = director;
		this.duracionMinutos = duracionMinutos;
	}

    //CONSTRUCTOR DE PERSISTENCIA
	public Pelicula(String identificador, String titulo, int anio, String director, int duracionMinutos, EstadoRecurso estado) {
		super(identificador, titulo, anio, estado);
		this.director = director;
		this.duracionMinutos = duracionMinutos;
	}

	public String getDirector() { return director; }
	public void setDirector(String director) { this.director = director; }
	public int getDuracionMinutos() { return duracionMinutos; }
	public void setDuracionMinutos(int duracionMinutos) { this.duracionMinutos = duracionMinutos; }

	@Override
	public String getDetallesEspecificos() { return "Director: " + director + ", Duración: " + duracionMinutos + " min"; }
}

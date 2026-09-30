package model;

public class Pelicula extends Recurso {
	private String director;
	private int duracionMinutos;

	// Para crear películas nuevas en la app
	public Pelicula(String titulo, int anio, String director, int duracionMinutos) {
		super(titulo, anio);
		this.director = director;
		this.duracionMinutos = duracionMinutos;
	}

	// Para cargar películas desde el CSV
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

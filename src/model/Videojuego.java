package model;

public class Videojuego extends Recurso {
	private String plataforma;
	private int pegi;

	// Para crear videojuegos nuevos en la app
	public Videojuego(String titulo, int anio, String plataforma, int pegi) {
		super(titulo, anio);
		this.plataforma = plataforma;
		this.pegi = pegi;
	}

	// Para cargar videojuegos desde el CSV
	public Videojuego(String identificador, String titulo, int anio, String plataforma, int pegi, EstadoRecurso estado) {
		super(identificador, titulo, anio, estado);
		this.plataforma = plataforma;
		this.pegi = pegi;
	}

	public String getPlataforma() { return plataforma; }
	public void setPlataforma(String plataforma) { this.plataforma = plataforma; }
	public int getPegi() { return pegi; }
	public void setPegi(int pegi) { this.pegi = pegi; }

	@Override
	public String getDetallesEspecificos() { return "Plataforma: " + plataforma + ", PEGI: " + pegi; }
}

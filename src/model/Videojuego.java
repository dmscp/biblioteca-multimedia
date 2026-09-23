package model;

public class Videojuego extends Recurso {

	private String plataforma;
	private int pegi;

	public Videojuego(String identificador, String titulo, int anio, String plataforma, int pegi) {
		super(identificador, titulo, anio);
		this.plataforma = plataforma;
		this.pegi = pegi;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public int getPegi() {
		return pegi;
	}

	public void setPegi(int pegi) {
		this.pegi = pegi;
	}

	@Override
	public String getDetallesEspecificos() {
		return "Plataforma: " + plataforma + ", PEGI: " + pegi;
	}
}
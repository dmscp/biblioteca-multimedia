package model;

import java.util.Objects;

public abstract class Recurso {

	private final String identificador;
	private String titulo;
	private int anio;
	private EstadoRecurso estado;

	protected Recurso(String identificador, String titulo, int anio) {
		this.identificador = identificador;
		this.titulo = titulo;
		this.anio = anio;
		this.estado = EstadoRecurso.DISPONIBLE;
	}

	public String getIdentificador() {
		return identificador;
	}

	public String getTitulo() {
		return titulo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identificador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recurso other = (Recurso) obj;
		return Objects.equals(identificador, other.identificador);
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public EstadoRecurso getEstado() {
		return estado;
	}

	public void setEstado(EstadoRecurso estado) {
		this.estado = estado;
	}

	public boolean estaDisponible() {
		return estado == EstadoRecurso.DISPONIBLE;
	}

	public abstract String getDetallesEspecificos();

	@Override
	public String toString() {
		return "[" + identificador + "] " + titulo + " (" + anio + ") - Estado: " + estado + " | "
				+ getDetallesEspecificos();
	}
}
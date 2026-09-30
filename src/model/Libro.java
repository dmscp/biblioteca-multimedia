package model;

public class Libro extends Recurso {
	private String autor;
	private int paginas;

	// Para crear libros nuevos en la app
	public Libro(String titulo, int anio, String autor, int paginas) {
		super(titulo, anio);
		this.autor = autor;
		this.paginas = paginas;
	}

	// Para cargar libros desde el CSV
	public Libro(String identificador, String titulo, int anio, String autor, int paginas, EstadoRecurso estado) {
		super(identificador, titulo, anio, estado);
		this.autor = autor;
		this.paginas = paginas;
	}

	public String getAutor() { return autor; }
	public void setAutor(String autor) { this.autor = autor; }
	public int getPaginas() { return paginas; }
	public void setPaginas(int paginas) { this.paginas = paginas; }

	@Override
	public String getDetallesEspecificos() { return "Autor: " + autor + ", Páginas: " + paginas; }
}

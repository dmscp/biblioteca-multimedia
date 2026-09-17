package model;

/**
 * Recurso de tipo libro. Datos propios: autor y número de páginas.
 */
public class Libro extends Recurso {

    private String autor;
    private int paginas;

    public Libro(String identificador, String titulo, int anio, String autor, int paginas) {
        super(identificador, titulo, anio);
        this.autor = autor;
        this.paginas = paginas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    @Override
    public String getDetallesEspecificos() {
        return String.format("Autor: %s, Páginas: %d", autor, paginas);
    }
}
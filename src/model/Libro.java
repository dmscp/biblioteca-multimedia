package model;

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
    return "Autor: " + autor + ", Páginas: " + paginas;
}
}
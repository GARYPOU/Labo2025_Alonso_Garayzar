package objetos.biblioteca;

public abstract class Publicacion {
    private String titulo;
    private String autor;
    private int anio;
    private int stock;

    public Publicacion(String titulo, String autor, int anio, int stock) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.stock = stock;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

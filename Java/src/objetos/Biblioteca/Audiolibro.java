package objetos.Biblioteca;

public class Audiolibro extends Publicacion{
    private int duracion;
    private int peso;

    public Audiolibro(String titulo, String autor, int anio, int stock) {
        super(titulo, autor, anio, stock);
    }

    public Audiolibro(String titulo, String autor, int anio, int stock, int duracion) {
        super(titulo, autor, anio, stock);
        this.duracion = duracion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}

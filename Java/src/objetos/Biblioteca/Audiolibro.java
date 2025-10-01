package objetos.biblioteca;

public class Audiolibro extends Publicacion{
    private int duracion;
    private int peso;

    public Audiolibro(String titulo, String autor, int anio, int stock) {
        super(titulo, autor, anio, stock);
    }
}

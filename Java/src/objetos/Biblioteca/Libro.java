package objetos.biblioteca;

public class Libro extends Publicacion implements Prestable{
    private boolean saga;

    public Libro(String titulo, String autor, int anio, int stock) {
        super(titulo, autor, anio, stock);
    }

    @Override
    public void devolucion() {

    }
}

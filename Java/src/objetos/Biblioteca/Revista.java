package objetos.biblioteca;

import java.util.List;

public class Revista extends Publicacion implements Prestable {
    private String distribuidora;

    public Revista(String titulo, String autor, int anio, int stock) {
        super(titulo, autor, anio, stock);
    }

    @Override
    public void devolucion() {
        
    }
}

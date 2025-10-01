package objetos.Biblioteca;

import java.util.List;

public class Revista extends Publicacion implements Prestable {
    private String distribuidora;

    public Revista(String titulo, String autor, int anio, int stock) {
        super(titulo, autor, anio, stock);
    }

    public Revista(String titulo, String autor, int anio, int stock, String distribuidora) {
        super(titulo, autor, anio, stock);
        this.distribuidora = distribuidora;
    }

    public String getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(String distribuidora) {
        this.distribuidora = distribuidora;
    }

    @Override
    public int multa() {
        return 3500;
    }
}

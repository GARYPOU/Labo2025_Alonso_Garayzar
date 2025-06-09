package objetos.Electrodomesticos;

public class Cargador extends Electrodomestico{
    private int carga;
    static Seccion seccion = Seccion.CARGADORES;
    public Cargador(String nombre, double precio, int stock, int carga) {
        super(nombre, precio, stock, Seccion.CARGADORES);
        this.carga = carga;
    }

    public Cargador() {
        this.carga=0;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }
}

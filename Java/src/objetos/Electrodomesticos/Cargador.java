package objetos.Electrodomesticos;

public class Cargador extends Electrodomestico{
    private int carga;

    public Cargador(String nombre, double precio, int stock, int carga) {
        super(nombre, precio, stock);
        this.carga = carga;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }
}

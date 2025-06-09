package objetos.Electrodomesticos;

public class Televisores extends EquipoSonido{
    private int pixels;

    public Televisores(String nombre, double precio, int stock, boolean modulo, int pixels) {
        super(nombre, precio, stock, modulo);
        this.pixels = pixels;
    }

    public int getPixels() {
        return pixels;
    }

    public void setPixels(int pixels) {
        this.pixels = pixels;
    }
}

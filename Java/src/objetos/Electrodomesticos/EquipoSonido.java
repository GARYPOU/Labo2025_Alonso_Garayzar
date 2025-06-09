package objetos.Electrodomesticos;

public class EquipoSonido extends Electrodomestico{
    private boolean modulo;

    public EquipoSonido(String nombre, double precio, int stock, boolean modulo) {
        super(nombre, precio, stock);
        this.modulo = modulo;
    }

    public boolean isModulo() {
        return modulo;
    }

    public void setModulo(boolean modulo) {
        this.modulo = modulo;
    }
}

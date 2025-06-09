package objetos.Electrodomesticos;

public class EquipoSonido extends Electrodomestico{
    private boolean modulo;
    static Seccion seccion = Seccion.MULTIMEDIA;
    public EquipoSonido(String nombre, double precio, int stock, boolean modulo) {
        super(nombre, precio, stock, Seccion.MULTIMEDIA);
        this.modulo = modulo;
    }

    public boolean isModulo() {
        return modulo;
    }

    public void setModulo(boolean modulo) {
        this.modulo = modulo;
    }
}

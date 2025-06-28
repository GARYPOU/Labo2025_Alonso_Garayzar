package objetos.Bebidas;

public class Alcoholica extends Bebida{
    private int cantAlcohol;
    private int porcentaje;
    public Alcoholica(String nombre) {
        super(nombre, 0);
    }

    @Override
    int calcularCoficiente() {
        return getPositividad()-(cantAlcohol*porcentaje);
    }
}

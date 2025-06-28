package objetos.Bebidas;

public class Neutra extends Bebida{

    public Neutra(String nombre, int positividad, int negatividad) {
        super(nombre, positividad, negatividad);
    }


    @Override
    int calcularCoficiente() {
        return getPositividad()-getNegatividad();
    }
}

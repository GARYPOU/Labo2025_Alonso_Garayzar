package objetos.Bebidas;

import java.util.Scanner;

public class Azucarada extends Bebida{
    private int cantidadAzucar;
    private static int porcentaje=10;

    public Azucarada(String nombre, int cantidadAzucar) {
        super(nombre, 1);
        this.cantidadAzucar=cantidadAzucar;
    }

    @Override
    int calcularCoficiente() {
        return getPositividad()-(cantidadAzucar*porcentaje);
    }
}

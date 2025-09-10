package Arco_Flecha;

import java.util.HashMap;

public class Circular extends Diana{
    private static int radio = 13;

    public Circular(String nombre, Nivel dificultad, HashMap<Integer, Colores> puntajes) {
        super(nombre, dificultad, puntajes);
    }

    @Override
    public double calcularArea() {
        return (radio*radio*3.14);
    }

    public static int getRadio() {
        return radio;
    }

    public static void setRadio(int radio) {
        Circular.radio = radio;
    }
}

package Arco_Flecha;

import java.util.HashMap;

public class Cuadrada extends Diana{
    private int lado;

    public Cuadrada(String nombre, Nivel dificultad, HashMap<Integer, Colores> puntajes, int lado) {
        super(nombre, dificultad, puntajes);
        this.lado = lado;
    }

    public int getLado() {
        return lado;
    }

    public void setLado(int lado) {
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return lado*lado;
    }
}

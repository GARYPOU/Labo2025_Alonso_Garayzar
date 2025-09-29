package objetos.Arco_Flecha;

import java.util.HashMap;

public class Rectangular extends Diana{
    private int base;
    private int altura;

    public Rectangular(String nombre, Nivel dificultad, HashMap<Integer, Colores> puntajes, int base, int altura) {
        super(nombre, dificultad, puntajes);
        this.base = base;
        this.altura = altura;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return base*altura;
    }
}

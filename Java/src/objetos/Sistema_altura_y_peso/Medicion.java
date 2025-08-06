package objetos.Sistema_altura_y_peso;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Medicion {
    private int peso;
    private int altura;

    public Medicion(int peso, int altura) {
        this.peso = peso;
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }



}

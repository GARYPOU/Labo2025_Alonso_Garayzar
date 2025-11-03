package Complejidad;

import java.util.ArrayList;

public class Ejercicio11 {

    private ArrayList<Integer> masFrecuente(ArrayList<Integer>numeros) {
        ArrayList<Integer> numerosNuevos = new ArrayList<>();
        int cantNum = 0;
        int cantMax = 0;
        int cant3 = 0;
        int i = 0;

        return numerosNuevos;
    }

    public static void main(String[] args) {
        Ejercicio11 e = new Ejercicio11();
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(2);
        numeros.add(2);
        numeros.add(4);
        numeros.add(4);
        numeros.add(5);
        numeros.add(2);
        numeros.add(5);
        numeros.add(8);

        System.out.println(e.masFrecuente(numeros));

    }
}

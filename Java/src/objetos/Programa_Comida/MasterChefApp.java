package objetos.Programa_Comida;

import objetos.Control_Calorias.Ingredente;

import java.util.HashMap;
import java.util.HashSet;

public class MasterChefApp {
    public static void main(String[] args) {

        Ingredente tomate = new Ingredente("Tomate", 20);
        Ingredente carne = new Ingredente("Carne", 200);
        Ingredente lechuga = new Ingredente("Lechuga", 15);
        Ingredente pan = new Ingredente("Pan", 100);


        HashMap<Ingredente, Integer> ingPlato = new HashMap<>();
        ingPlato.put(tomate, 2);
        ingPlato.put(carne, 1);
        Plato hamburguesa = new Plato("Hamburguesa", ingPlato);


        System.out.println("\n--- PRUEBA PRINCIPIANTE ---");
        Principiante principiante = new Principiante();


        HashSet<Ingredente> prohibidos = new HashSet<>();
        prohibidos.add(lechuga);

        principiante.preparar();
        principiante.ccinar(hamburguesa);
        principiante.servir_entrada();


        System.out.println("\n--- PRUEBA INTERMEDIO ---");
        Intermedio intermedio = new Intermedio();


        HashMap<Ingredente, Integer> stockIntermedio = new HashMap<>();
        stockIntermedio.put(tomate, 2);
        stockIntermedio.put(carne, 1);
        stockIntermedio.put(pan, 1);


        intermedio.preparar();
        intermedio.ccinar(hamburguesa);
        intermedio.servir_principal();


        System.out.println("\n--- PRUEBA EXPERTO ---");
        Experto experto = new Experto();
        experto.preparar();
        experto.ccinar(hamburguesa);
        experto.servir_entrada();
        experto.servir_principal();
    }
}


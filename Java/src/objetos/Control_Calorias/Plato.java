package objetos.Control_Calorias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Plato {
    private String nombre;
    private HashMap<Ingredente,Integer>ingredientes;
    private double cantCalorias;

    public Plato(String nombre, HashMap<Ingredente, Integer> ingredientes) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }

    public Plato() {
        this.nombre = "felipe";
        this.ingredientes = new HashMap<>();
        this.cantCalorias = 20;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashMap<Ingredente, Integer> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(HashMap<Ingredente, Integer> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public double getCantCalorias() {
        return cantCalorias;
    }

    public void setCantCalorias(double cantCalorias) {
        this.cantCalorias = cantCalorias;
    }

    public double cantIngrdientes() {
        double total = 0;
        for (Map.Entry<Ingredente, Integer> ingre : ingredientes.entrySet()) {
            total = ingre.getValue();
        }
        this.cantCalorias = total;

        return total;
    }
}

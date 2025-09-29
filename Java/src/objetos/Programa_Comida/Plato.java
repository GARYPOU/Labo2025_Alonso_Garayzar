package objetos.Programa_Comida;

import objetos.Control_Calorias.Ingredente;

import java.util.HashMap;
import java.util.HashSet;

public class Plato {
    private String nombre;
    private HashMap<Ingredente, Integer> ingredientes;

    public Plato(String nombre, HashMap<Ingredente, Integer> ingredientes) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
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
}

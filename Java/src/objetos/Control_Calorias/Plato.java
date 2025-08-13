package objetos.Control_Calorias;

import java.util.ArrayList;

public class Plato {
    private String nombre;
    private ArrayList<String>ingredientes;
    private double cantCalorias;

    public Plato(String nombre, ArrayList<String> ingredientes, double cantCalorias) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.cantCalorias = cantCalorias;
    }

    public Plato() {
        this.nombre = "felipe";
        this.ingredientes = new ArrayList<>();
        this.cantCalorias = 20;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public double getCantCalorias() {
        return cantCalorias;
    }

    public void setCantCalorias(double cantCalorias) {
        this.cantCalorias = cantCalorias;
    }
}

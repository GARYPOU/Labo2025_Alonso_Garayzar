package objetos.Cocina;

import java.util.ArrayList;

public abstract class Receta {
    private String nombre;
    private Dificultad nivel;
    private ArrayList<String>pasos;

    public Receta(String nombre, Dificultad nivel, ArrayList<String> pasos) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.pasos = pasos;
    }
    public Receta() {
        this.nombre = "";
        this.nivel = Dificultad.AVANZADO;
        this.pasos = new ArrayList<>();
    }

    abstract void realizarreceta();
}

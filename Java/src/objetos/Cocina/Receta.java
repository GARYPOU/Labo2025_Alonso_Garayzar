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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Dificultad getNivel() {
        return nivel;
    }

    public void setNivel(Dificultad nivel) {
        this.nivel = nivel;
    }

    public ArrayList<String> getPasos() {
        return pasos;
    }

    public void setPasos(ArrayList<String> pasos) {
        this.pasos = pasos;
    }

    abstract void realizarReceta();



}

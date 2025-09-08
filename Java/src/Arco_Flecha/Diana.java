package Arco_Flecha;

import java.util.HashMap;
import java.util.HashSet;

public abstract class Diana {
    private String nombre;
    private Nivel dificultad;
    private HashMap<Integer,Colores> puntajes;


    public Diana(String nombre, Nivel dificultad, HashMap<Integer,Colores> puntajes) {
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.puntajes = puntajes;
    }
}

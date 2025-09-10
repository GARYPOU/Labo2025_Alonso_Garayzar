package Arco_Flecha;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class Diana {
    private String nombre;
    private Nivel dificultad;
    private HashMap<Integer,Colores> puntajes;


    public Diana(String nombre, Nivel dificultad, HashMap<Integer,Colores> puntajes) {
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.puntajes = puntajes;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Nivel getDificultad() {
        return dificultad;
    }

    public void setDificultad(Nivel dificultad) {
        this.dificultad = dificultad;
    }

    public HashMap<Integer, Colores> getPuntajes() {
        return puntajes;
    }

    public void setPuntajes(HashMap<Integer, Colores> puntajes) {
        this.puntajes = puntajes;
    }

    public abstract double calcularArea();


     public double calcularPuntos(){
         double TotalPuntos = 0;
         for (Map.Entry<Integer,Colores> d: puntajes.entrySet()){
             TotalPuntos = TotalPuntos + (d.getKey()*d.getValue().getPuntos());
         }
         return TotalPuntos;
     }
}

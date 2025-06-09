package objetos.Cocina;

import java.util.ArrayList;

public class PlatoPrincipal extends Receta{
    private double tiempoCoccion;
    private int comensales;

    public PlatoPrincipal(String nombre, Dificultad nivel, ArrayList<String> pasos, double tiempoCoccion, int comensales) {
        super(nombre, nivel, pasos);
        this.tiempoCoccion = tiempoCoccion;
        this.comensales = comensales;
    }
    public void realizarreceta(){
        System.out.println("Esta receta tarda"+""+tiempoCoccion);
        for(int i=0; i<pasos.size(); i++){
            System.out.println(pasos.get(i));
        }
    }
}

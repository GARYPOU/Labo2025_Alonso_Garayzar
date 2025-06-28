package objetos.Cocina;

import java.util.ArrayList;

public class PlatoPrincipal extends Receta{
    private double tiempoCoccion;
    private int comensales;

    public PlatoPrincipal(String nombre, Dificultad nivel, ArrayList<String>pasos, double tiempoCoccion, int comensales) {
        super(nombre, nivel, pasos);
        this.tiempoCoccion = tiempoCoccion;
        this.comensales = comensales;
    }
    public PlatoPrincipal() {
        super("xd", Dificultad.AVANZADO, new ArrayList<>());
        this.tiempoCoccion = 1;
        this.comensales = 22;
    }


    @Override
    public void realizarReceta(){
        System.out.println("Esta receta tarda"+""+tiempoCoccion);
        System.out.println("Los pasos a seguir son");
        for(int i=0; i<getPasos().size(); i++){
            System.out.println(getPasos().get(i));
        }
    }

    public double getTiempoCoccion() {
        return tiempoCoccion;
    }

    public void setTiempoCoccion(double tiempoCoccion) {
        this.tiempoCoccion = tiempoCoccion;
    }

    public int getComensales() {
        return comensales;
    }

    public void setComensales(int comensales) {
        this.comensales = comensales;
    }
}

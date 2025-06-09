package objetos.Mecanico;

import objetos.Color;
import objetos.Mecanico.Marca;
import objetos.Mecanico.Rueda;



public class Carro extends Automovil {
    private boolean descapotable;


    public Carro(Boolean descapotable, Marca marca, String modelo, Color color, int velocidad, Rueda cantRuedas, int patente){
        super(marca, modelo, color,velocidad,cantRuedas, patente);
        this.descapotable=descapotable;
    }
    public Carro(){
        super();
        descapotable=true;
    }

    public boolean isDescapotable() {
        return descapotable;
    }

    public void setDescapotable(boolean descapotable) {
        this.descapotable = descapotable;
    }
    public boolean carroDescapotable(){
        return descapotable;
    }

    public static void main(String[] args) {
        Carro c1 = new Carro(true, Marca.FIAT, "corola", Color.ROJO, 120, Rueda.CUATRORUEDAS,204);
        System.out.println("Aceleracion="+ c1.calcacel()+"km/h2");
        System.out.println("Frenado="+ c1.calcfreno());
        System.out.println("velocidad actual"+ c1.getVelocidad());

    }
}

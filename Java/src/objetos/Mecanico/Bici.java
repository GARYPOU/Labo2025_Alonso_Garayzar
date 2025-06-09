package objetos.Mecanico;

import objetos.Color;
import objetos.Mecanico.Marca;
import objetos.Mecanico.Rueda;

public class Bici extends SistVehiculo {




    public Bici(Marca marca, String modelo, Color color, int velocidad, Rueda cantRuedas){
        super(marca,color, modelo,velocidad, cantRuedas);
    }
    public Bici(){
        super();
    }

    public static void main(String[] args) {

    }

}

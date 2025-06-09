package objetos.Mecanico;

import objetos.Color;
import objetos.Mecanico.Marca;
import objetos.Mecanico.Rueda;

public class Automovil extends SistVehiculo{

    private int patente;


    public Automovil(Marca marca, String modelo, Color color, int velocidad, Rueda cantRuedas, int patente){
        super(marca,color,modelo,velocidad, cantRuedas);
        this.patente=patente;
    }
    public Automovil(){
        super();
    }



}

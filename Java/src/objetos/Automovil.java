package objetos;

public class Automovil extends SistVehiculo{

    private int patente;


    public Automovil(String marca, String modelo, String color, int velocidad,int cantRuedas, int patente){
        super(marca,modelo,color,velocidad, cantRuedas);
        this.patente=patente;
    }
    public Automovil(){
        super();
    }



}

package objetos;

import java.time.LocalDate;

public class SistVehiculo {
    private String marca;
    private String color;
    private String modelo;
    private int cantRuedas;
    private LocalDate año;
    private int velocidad;
    private String patente;

    public SistVehiculo(){
        this.marca= "Wolsbagen";
        this.color="rojo";
        this.modelo="306";
        this.cantRuedas=4;
        this.año=LocalDate.of(1000,9,10);
        this.velocidad=100;
    }
    public SistVehiculo(String marca, String color, String modelo, int cantRuedas, LocalDate año, int velocidad){
        this.marca=marca;
        this.color=color;
        this.modelo=modelo;
        this.cantRuedas=cantRuedas;
        this.año=año;
        this.velocidad=velocidad;
    }
    public SistVehiculo(String marca,String modelo, String color, int velocidad, String patente){
        this.marca=marca;
        this.color=color;
        this.modelo=modelo;
        this.velocidad=velocidad;
        this.patente=patente;    }

    public float calcacel(){
        float x;
        x = (float) (velocidad/60);
        return x;
    }

    public float calcfreno(){
        float x;
        x = (float) ((velocidad*velocidad)/180);
        return x;
    }
}

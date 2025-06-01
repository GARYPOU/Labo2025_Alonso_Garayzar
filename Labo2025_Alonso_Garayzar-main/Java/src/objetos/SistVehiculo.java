package objetos;

import java.time.LocalDate;

public class SistVehiculo  {
    private String marca;
    private String color;
    private String modelo;
    private int cantRuedas;
    private LocalDate año;
    private int velocidad;


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
    public SistVehiculo(String marca, String color, String modelo,int velocidad, int cantRuedas){
        this.marca=marca;
        this.color=color;
        this.modelo=modelo;
        this.velocidad=velocidad;
        this.cantRuedas=cantRuedas;
    }
    public SistVehiculo(String marca,String modelo, String color, int velocidad){
        this.marca=marca;
        this.color=color;
        this.modelo=modelo;
        this.velocidad=velocidad;

    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCantRuedas() {
        return cantRuedas;
    }

    public void setCantRuedas(int cantRuedas) {
        this.cantRuedas = cantRuedas;
    }

    public LocalDate getAño() {
        return año;
    }

    public void setAño(LocalDate año) {
        this.año = año;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }



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

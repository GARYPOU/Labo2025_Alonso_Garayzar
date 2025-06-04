package objetos;

import java.time.LocalDate;

public class SistVehiculo  {
    private Marca marca;
    private String modelo;
    private Color color;
    private Rueda cantRuedas;
    private LocalDate año;
    private int velocidad;


    public SistVehiculo(){
        Marca marca = Marca.CITROEN;
        Color color = Color.AZUL;
        this.modelo="306";
        this.cantRuedas=Rueda.CUATRORUEDAS;
        this.año=LocalDate.of(1000,9,10);
        this.velocidad=100;
    }
    public SistVehiculo(Marca marca, Color color, String modelo, Rueda cantRuedas, LocalDate año, int velocidad){
        this.marca=marca;
        this.color=color;
        this.modelo=modelo;
        this.cantRuedas=cantRuedas;
        this.año=año;
        this.velocidad=velocidad;
    }
    public SistVehiculo(Marca marca, Color color, String modelo,int velocidad, Rueda cantRuedas){
        this.marca=marca;
        this.color=color;
        this.modelo=modelo;
        this.velocidad=velocidad;
        this.cantRuedas=cantRuedas;
    }
    public SistVehiculo(Marca marca,String modelo, Color color, int velocidad){
        this.marca=marca;
        this.color=color;
        this.modelo=modelo;
        this.velocidad=velocidad;

    }


    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Rueda getCantRuedas() {
        return cantRuedas;
    }

    public void setCantRuedas(Rueda cantRuedas) {
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

package personas;

import java.time.LocalDate;

public class Profesor extends Persona{
    private float descuento;


    public Profesor(){
        super();
        this.descuento=2;
    }

    public Profesor(String nombre, String apellido,int direccion, LocalDate nacimiento, LocalDate fechaEmpezo, float descuento){
        super(nombre, apellido, direccion, nacimiento, fechaEmpezo);
        this.descuento=descuento;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

}
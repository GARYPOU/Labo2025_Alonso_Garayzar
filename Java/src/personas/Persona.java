package personas;

import java.time.LocalDate;
import java.util.ArrayList;

public class Persona {
    private String nombre;
    private String apellido;
    private int direccion;
    private LocalDate nacimiento;
    private LocalDate fechaEmpezo;




    public Persona(){
        this.nombre="Juaita";
        this.apellido="Jaime";
        this.direccion=1991919919;
        this.nacimiento=LocalDate.of(1990,7,12);
        this.fechaEmpezo=LocalDate.of(2020,11,13);



    }



    public Persona(String nom,String apellido, int direccion, LocalDate nacimiento,LocalDate fechaEmpezo){
        this.nombre=nom;
        this.apellido=apellido;
        this.direccion=direccion;
        this.nacimiento=nacimiento;
        this.fechaEmpezo=fechaEmpezo;


    }
    public Persona(String nom,String apellido, int direccion, LocalDate nacimiento){
        this.nombre=nom;
        this.apellido=apellido;
        this.direccion=direccion;
        this.nacimiento=nacimiento;


    }




    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nom){
        this.nombre=nom;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaEmpezo() {
        return fechaEmpezo;
    }

    public void setFechaEmpezo(LocalDate fechaEmpezo) {
        this.fechaEmpezo = fechaEmpezo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String mostrardatos(){
        String x;
        Persona p1 = new Persona();
        x = (String) ("Nombre:"+p1.nombre+" "+"Edad:"+p1.nacimiento.getYear()+"/"+p1.nacimiento.getMonth()+"/"+p1.nacimiento.getDayOfMonth()+" "+"Direccion:"+p1.direccion);
        return x;
    }
    public LocalDate antiguedad() {
        int year=fechaEmpezo.getYear()-LocalDate.now().getYear();
        int month=fechaEmpezo.getMonthValue()-LocalDate.now().getMonthValue();
        int day=fechaEmpezo.getDayOfMonth()-LocalDate.now().getDayOfMonth();
        return LocalDate.of(year, month, day);

    }




    public static void main(String[] args) {
        Persona p1 = new Persona();
        System.out.println(p1.mostrardatos());

    }
}


package personas;

import java.time.LocalDate;

public  class Persona {
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
    public Persona(String nombre){
        this.nombre=nombre;

    }

    public Persona(String nom, String apellido){
        this.nombre=nom;
        this.apellido=apellido;
    }

    public Persona(String nom, String apellido, LocalDate nacimiento){
        this.nombre=nom;
        this.apellido=apellido;
        this.nacimiento=nacimiento;


    }

    public Persona(String nom, String apellido, int direccion, LocalDate nacimiento, LocalDate fechaEmpezo){
        this.nombre=nom;
        this.apellido=apellido;
        this.direccion=direccion;
        this.nacimiento=nacimiento;
        this.fechaEmpezo=fechaEmpezo;


    }

    public Persona(String nom, LocalDate nacimiento){
        this.nombre=nom;
        this.nacimiento=nacimiento;



    }
    public Persona(String nom,String apellido, int direccion, LocalDate nacimiento){
        this.nombre=nom;
        this.apellido=apellido;
        this.direccion=direccion;
        this.nacimiento=nacimiento;
        this.fechaEmpezo=LocalDate.now();


    }




    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
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
        Persona p1 = new Jugador();
        x = (String) ("Nombre:"+p1.nombre+" "+"Edad:"+p1.nacimiento.getYear()+"/"+p1.nacimiento.getMonth()+"/"+p1.nacimiento.getDayOfMonth()+" "+"Direccion:"+p1.direccion);
        return x;
    }
    public LocalDate antiguedad() {
        int year=LocalDate.now().getYear()-fechaEmpezo.getYear();
        int month=LocalDate.now().getMonthValue()-fechaEmpezo.getMonthValue();
        if(month<1){
            month=1;
        }
        int day=LocalDate.now().getDayOfMonth()-fechaEmpezo.getDayOfMonth();
        if(day<1){
            day=1;
        }
        return LocalDate.of(year, month, day);

    }




    public static void main(String[] args) {
        Persona p1 = new Empleado();
        System.out.println(p1.mostrardatos());

    }
}


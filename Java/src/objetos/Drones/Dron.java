package objetos.Drones;

import java.time.LocalDate;

public abstract class Dron {
    private String nombre;
    private LocalDate fecha;
    private int bateria;
    static int cantidad;
    private int id;
    private Estado estado;



    public Dron(String nombre, LocalDate fecha, int bateria, int id, Estado estado) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.bateria = bateria;
        this.id = id;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getBateria() {
        return bateria;
    }
    public void setBateria(int bateria) {
        this.bateria=bateria;
    }


    public static int getCantidad() {
        return cantidad;
    }

    public static void setCantidad(int cantidad) {
        Dron.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void recargar(){
        if(bateria<=20){
            bateria=100;
        }
        else{
            bateria=bateria+10;
        }
        chquearBateria(bateria);
    }
    public void chquearBateria(int bateria) {
        if (bateria > 100) {
            this.bateria = 100;
        } else {
            this.bateria = bateria;
        }
    }

    abstract boolean completarMision(double longitudDestino, double latitudDestino);



}


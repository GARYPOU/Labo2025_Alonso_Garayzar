package objetos.Drones;

import java.time.LocalDate;

public abstract class Dron {
    private String nombre;
    private LocalDate fecha;
    private int bateria;
    static int cantidad;
    private int id;
    private Estado estado;
    static double latitud=Math.toRadians(-34.573195);
    static double longitud=Math.toRadians(-58.504111);


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

    abstract boolean completarMision();


    public double calcular(double longitudDestino, double latitudDestino) {
        double lat1Rad = latitud;
        double lon1Rad = longitud;
        double lat2Rad = Math.toRadians(latitudDestino);
        double lon2Rad = Math.toRadians(longitudDestino);

        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double radioTierraKm = 6371;
        return radioTierraKm * c;
    }
}


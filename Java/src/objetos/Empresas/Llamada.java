package objetos.Empresas;

import personas.Empleado;

import java.time.LocalDateTime;

public class Llamada {
    private Empleado origen;
    private Empleado destino;
    private LocalDateTime fecha;
    private double duracionSegundos;


    public Llamada(Empleado origen, Empleado destino, LocalDateTime fecha, double duracionSegundos) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.duracionSegundos = duracionSegundos;
    }

    public Empleado getOrigen() {
        return origen;
    }

    public void setOrigen(Empleado origen) {
        this.origen = origen;
    }

    public Empleado getDestino() {
        return destino;
    }

    public void setDestino(Empleado destino) {
        this.destino = destino;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }
}

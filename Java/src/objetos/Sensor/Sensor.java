package objetos.Sensor;

import java.time.LocalDate;

public abstract class Sensor {
    private boolean estado;
    private double medida;
    private LocalDate adquisicion;
    private double umbralInicial;
    private String nombre;

    public Sensor(boolean estado, double medida, LocalDate adquisicion, double umbralInicial, String nombre) {
        this.estado = estado;
        this.medida = medida;
        this.adquisicion = adquisicion;
        this.umbralInicial = umbralInicial;
        this.nombre = nombre;
    }

    public Sensor() {
        this.estado = true;
        this.medida = 50.0;
        this.adquisicion = LocalDate.of(2000,3,6);
        this.umbralInicial=60.0;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getMedida() {
        return medida;
    }

    public void setMedida(double medida) {
        this.medida = medida;
    }

    public LocalDate getAdquisicion() {
        return adquisicion;
    }

    public void setAdquisicion(LocalDate adquisicion) {
        this.adquisicion = adquisicion;
    }

    public double getUmbralInicial() {
        return umbralInicial;
    }

    public void setUmbralInicial(double umbralInicial) {
        this.umbralInicial = umbralInicial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void dispara(){

    }
}

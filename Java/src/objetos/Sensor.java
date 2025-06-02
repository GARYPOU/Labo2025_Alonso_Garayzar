package objetos;

import java.time.LocalDate;

public class Sensor {
    private boolean estado;
    private double medida;
    private LocalDate adquisicion;
    double umbralInicial;

    public Sensor(boolean estado, double medida, LocalDate adquisicion,  double umbralInicial) {
        this.estado = estado;
        this.medida = medida;
        this.adquisicion = adquisicion;
        this.umbralInicial=umbralInicial;
    }
    public Sensor() {
        this.estado = true;
        this.medida = 50.0;
        this.adquisicion = LocalDate.of(2000,3,6);
        this.umbralInicial=60.0;
    }

    public boolean getEstado() {
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

    public double getUmbralInicial() {
        return umbralInicial;
    }

    public void setUmbralInicial(double umbralInicial) {
        this.umbralInicial = umbralInicial;
    }

    public LocalDate getAdquisicion() {
        return adquisicion;
    }

    public void setAdquisicion(LocalDate adquisicion) {
        this.adquisicion = adquisicion;
    }

    public void dispara(){

    }
}

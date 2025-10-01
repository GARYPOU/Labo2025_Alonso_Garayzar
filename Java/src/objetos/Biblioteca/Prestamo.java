package objetos.Biblioteca;

import java.time.LocalDate;

public class Prestamo {
    private Publicacion info;
    private int numSocio;
    private LocalDate fechaIni;
    private LocalDate fechaDev;


    public Prestamo(Publicacion info, int numSocio, LocalDate fechaIni, LocalDate fechaDev) {
        this.info = info;
        this.numSocio = numSocio;
        this.fechaIni = fechaIni;
        this.fechaDev = fechaDev;
    }

    public Publicacion getInfo() {
        return info;
    }

    public void setInfo(Publicacion info) {
        this.info = info;
    }

    public int getNumSocio() {
        return numSocio;
    }

    public void setNumSocio(int numSocio) {
        this.numSocio = numSocio;
    }

    public LocalDate getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(LocalDate fechaIni) {
        this.fechaIni = fechaIni;
    }

    public LocalDate getFechaDev() {
        return fechaDev;
    }

    public void setFechaDev(LocalDate fechaDev) {
        this.fechaDev = fechaDev;
    }

    public boolean validar(){
        if(LocalDate.now().isAfter(fechaIni) && LocalDate.now().isBefore(fechaDev)){
            System.out.println("Devuelto en forma y horario");
            return false;
        }
        System.out.println("Prestamo devuelto fuera de fecha");
        return true;
    }

}

package personas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class Jugador {
    private String nombre;
    private LocalDate fecha;
    private int numero;
    private boolean capitan;

    public Jugador(){
        this.nombre="Gustavo";
        this.fecha = LocalDate.of(2004,12,31);
        this.numero=15;
        this.capitan=true;
    }
    public Jugador(String nombre, LocalDate fecha, int numero, boolean capitan){
        this.nombre=nombre;
        this.fecha = fecha;
        this.numero= numero;
        this.capitan = capitan;


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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isCapitan() {
        return capitan;
    }

    public void setCapitan(boolean capitan) {
        this.capitan = capitan;
    }
}
package personas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class Jugador extends Persona{

    private int numero;
    private boolean capitan;

    public Jugador(){
        super();
        this.numero=15;
        this.capitan=true;
    }
    public Jugador(String nombre, LocalDate nacimiento, int numero, boolean capitan){
        super(nombre, nacimiento);
        this.numero= numero;
        this.capitan = capitan;


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
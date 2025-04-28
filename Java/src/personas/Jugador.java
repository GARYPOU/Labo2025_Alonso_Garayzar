package personas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class Jugador {
    private String nombre;
    private LocalDate fecha;
    private int numero;

    public Jugador(){
        this.nombre="Gustavo";
        this.fecha = LocalDate.of(2004,12,31);
        this.numero=15;
        
    }
}
package objetos.Deportes;

import objetos.Deportes.Turno;
import personas.Jugador;
import objetos.Deportes.Partido;
import java.time.LocalDate;
import java.util.ArrayList;

public class Equipo {
    private String nombre;
    private String barrio;
    private Jugador[] jugadores = new Jugador[11];
    private Turno horario;

    public Equipo(){
        this.nombre="River";
        this.barrio = "Nuñes";
        this.llenarJugadores();
        Turno horario= Turno.MANANA;
    }
    public Equipo(String nombre, String barrio, Jugador[] jugadores, Turno horario){
        this.nombre=nombre;
        this.barrio = barrio;
        this.jugadores= jugadores;
        this.horario = horario;


    }
    public void llenarJugadores() {
        for (int i = 0; i < jugadores.length; i++) {
            String nombre = "Jugador" + (i + 1);
            LocalDate fecha = LocalDate.of(1995, 12, 1+i );
            int numero = i + 1;
            boolean capitan;
            if(i==5) {
                capitan = true;
            }
            else{
                capitan=false;
            }
            jugadores[i] = new Jugador(nombre, fecha, numero, capitan);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }

    public Turno getHorario() {
        return horario;
    }

    public void setHorario(Turno horario) {
        this.horario = horario;
    }

}


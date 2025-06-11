package objetos.Drones;

import java.time.LocalDate;

public class Vigilancia extends Dron{
    private int memoria;
    public Vigilancia(String nombre, LocalDate fecha, int bateria, int id, Estado estado) {
        super(nombre, fecha, bateria, id, estado);
    }



    @Override
    boolean completarMision() {
        return false;
    }

}

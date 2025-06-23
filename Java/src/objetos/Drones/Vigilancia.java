package objetos.Drones;

import javax.swing.plaf.IconUIResource;
import java.time.LocalDate;

public class Vigilancia extends Dron{
    private int memoria;
    public Vigilancia(String nombre, LocalDate fecha, int bateria, int id, Estado estado) {
        super(nombre, fecha, bateria, id, estado);
    }



    @Override
    boolean completarMision(double longitudDestino, double latitudDestino) {
        double calculars=Distancia.calcular(longitudDestino,latitudDestino);
        double total=calculars*2;
        if((memoria-total)<0){

            System.out.println("No puede completar la mision");
            return false;
        }
        System.out.println("Pudo completar la mision");
        return true;

    }

}

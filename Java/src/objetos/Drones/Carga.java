package objetos.Drones;
import objetos.Drones.Distancia;
import java.time.LocalDate;

public class Carga extends Dron {
    private int carga;

    public Carga(String nombre, LocalDate fecha, int bateria, int id, Estado estado) {
        super(nombre, fecha, bateria, id, estado);
    }
    public int disminuirBat(){
        setBateria(getBateria()-50);
        if(getBateria()<0){
            return getBateria();
        }
        System.out.println("hara 3 viajes de ida y vuelta");

        return getBateria();
    }

    @Override
    boolean completarMision(double longitudDestino, double latitudDestino) {
        double calculars;
        calculars = Distancia.calcular(longitudDestino,latitudDestino);
        if (calculars <= 30) {
            if(0>disminuirBat()){
                return false;
            }
            return true;
        } else {
            return false;
        }
    }



}

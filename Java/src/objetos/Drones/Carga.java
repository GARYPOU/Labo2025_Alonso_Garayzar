package objetos.Drones;

import java.time.LocalDate;

public class Carga extends Dron {
    private int carga;

    public Carga(String nombre, LocalDate fecha, int bateria, int id, Estado estado) {
        super(nombre, fecha, bateria, id, estado);
    }
    public int disminuirBat(){
        setBateria(getBateria()-48);
        if(getBateria()<0){
            return getBateria();
        }
        System.out.println("hara 3 viajes de ida y vuelta");

        return getBateria();
    }

    @Override
    boolean completarMision() {
        double longitudDestino=0;
        double latitudDestino=0;
        double calcular = calcular(longitudDestino, latitudDestino);
        if (calcular <= 30) {
            if(0>disminuirBat()){
                return false;
            }
            return true;
        } else {
            return false;
        }
    }



}

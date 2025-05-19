package objetos;

import java.util.ArrayList;

public class Concesionaria {
    private ArrayList<SistVehiculo>vehiculos;
    public Concesionaria(){
        ArrayList<SistVehiculo>vehiculos = new ArrayList<>();
    }
    public Concesionaria(ArrayList<SistVehiculo>vehiculos){
        this.vehiculos=vehiculos;
    }

    public ArrayList<SistVehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<SistVehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    public void masCant() {
        for (SistVehiculo v : vehiculos) {
            int bici = 0;
            int camio = 0;
            int auto = 0;


        }
    }

    public static void main(String[] args) {

    }
}



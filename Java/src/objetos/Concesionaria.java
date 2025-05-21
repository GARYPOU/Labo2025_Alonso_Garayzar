package objetos;

import java.util.ArrayList;

public class Concesionaria {
    private static ArrayList<SistVehiculo>vehiculos;
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
            int descapotable = 0;
            if(v instanceof Bici){
                bici=bici+1;
            }
            else if(v instanceof Carro){
                auto=auto+1;
                if(((Carro) v).carroDescapotable()){
                    descapotable=descapotable+1;
                }


            }
            else if(v instanceof Camioneta){
                camio=camio+1;
            }
            if(camio>bici && camio>auto){
                System.out.println("Camioneta");
            }
            if(camio<bici && auto<bici){
                System.out.println("Bicicleta");
            }
            if(auto>bici && auto>camio){
                System.out.println("Auto");
                System.out.println("Hay"+" "+descapotable+" "+"autos"+" "+"descapotables");
            }


        }
    }

    public static void main(String[] args) {
    SistVehiculo s1 = new SistVehiculo();
    Concesionaria c1 = new Concesionaria();
    Camioneta camio1 = new Camioneta();
    vehiculos.add(camio1);
    camio1.agregarCarga(120);
    c1.masCant();

    }
}



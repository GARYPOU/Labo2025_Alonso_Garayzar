package objetos.Sensor;

import Excepciones.Nombrenull;

import java.util.ArrayList;
import java.util.Scanner;

public class SistAlarmas {
    public ArrayList<Sensor> sensores;

    public SistAlarmas(ArrayList<Sensor> sensores) {
        this.sensores = sensores;
    }
    public SistAlarmas() {
        this.sensores = new ArrayList<>();
    }

    public void comparar() {
        double total=0;
        String mensaje=" ";
        for (Sensor s : sensores) {
            if (s instanceof MegaSensor) {
                total=((MegaSensor) s).getHumin().getMedida()+((MegaSensor) s).getPrecio().getMedida()+((MegaSensor) s).getTempe().getMedida();
                if(total>s.getUmbralInicial()){
                    mensaje="Ya no hay nada que podamos hacer";
                    System.out.println(mensaje);
                }
            } else {
                if (s.getMedida() > s.getUmbralInicial()) {
                    if (s instanceof Humo) {
                        dispara();
                    } else if (s instanceof Temperatura) {
                        dispara();
                    } else if (s instanceof Precion) {
                        s.dispara();
                    }
                }
            }
            System.out.println(mensaje);
        }
    }
    public void dispara(){
    }
    public void elegi(){
        for (int i = 0; i < sensores.size(); i++) {
            System.out.println(i+")"+sensores.get(i).getNombre());
        }
        System.out.println("Ingrese el escaner que quiere verificar");
        Scanner s1 = new Scanner(System.in);
        int pos= s1.nextInt();
        try{
            if (pos>sensores.size()){
                throw new ArrayIndexOutOfBoundsException("el numero es muy grande");
            }
            if(pos<0){
                throw new ArithmeticException("el numero es negativo");
            }
            if(sensores==null){
                throw new Nombrenull("no hay sensores");
            }
            System.out.println("Sensor"+sensores.get(pos).getNombre());
        } catch (Exception l){
            System.err.println(l);
        }


    }


    public static void main(String[] args) {
        SistAlarmas a1 = new SistAlarmas();
        Temperatura t1 = new Temperatura();
        a1.sensores.add(t1);
        System.out.println("elegi");
        a1.elegi();
        System.out.println("xd");
        a1.comparar();




    }


}

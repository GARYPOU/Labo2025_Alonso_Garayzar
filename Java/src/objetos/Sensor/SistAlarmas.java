package objetos;

import java.util.ArrayList;

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

    public static void main(String[] args) {
        SistAlarmas a1 = new SistAlarmas();
        Sensor s1 = new Sensor();
        a1.comparar();

    }
}

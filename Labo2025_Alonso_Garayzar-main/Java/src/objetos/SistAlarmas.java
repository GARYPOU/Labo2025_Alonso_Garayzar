package objetos;

import java.util.ArrayList;

public class SistAlarmas {
    public ArrayList<Sensor> sensores;


    public void comparar() {
        double total=0;
        for (Sensor s : sensores) {
            if (s instanceof MegaSensor) {
                total=((MegaSensor) s).getHumin().getMedida()+((MegaSensor) s).getPrecio().getMedida()+((MegaSensor) s).getTempe().getMedida();
                if(total>s.getUmbralInicial()){
                    System.out.println("Ya no hay nada que podamos hacer");
                }
            } else {
                if (s.getMedida() > s.getUmbralInicial()) {
                    if (s instanceof Humo) {
                        System.out.println("Llamar a los bomberos");
                    } else if (s instanceof Temperatura) {
                        System.out.println("¡Cuidado! La temperatura sube");
                    } else if (s instanceof Precion) {
                        System.out.println("Sensor de presión activado");
                    }
                }
            }
        }
    }
}

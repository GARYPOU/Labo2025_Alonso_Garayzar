package objetos.Sensor;

import java.time.LocalDate;

public class Temperatura extends Sensor{

    public Temperatura(boolean estado, double medida, LocalDate adquisicion, double umbralInicial, String nombre) {
        super(estado, medida, adquisicion, umbralInicial, nombre);
    }

    public Temperatura() {
    }


    @Override
    public void dispara(){
        System.out.println("¡Cuidado! La temperatura sube");
    }
}

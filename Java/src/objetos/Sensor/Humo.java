package objetos.Sensor;

import java.time.LocalDate;

public class Humo extends Sensor{

    public Humo(boolean estado, double medida, LocalDate adquisicion, double umbralInicial, String nombre) {
        super(estado, medida, adquisicion, umbralInicial, nombre);
    }

    public Humo() {
    }

    @Override
    public void dispara(){
        System.out.println("Llamar a los bomberos");
    }
}

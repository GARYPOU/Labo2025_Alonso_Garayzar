package objetos.Sensor;

import java.time.LocalDate;

public class Precion extends Sensor{

    public Precion(boolean estado, double medida, LocalDate adquisicion, double umbralInicial) {
        super(estado, medida, adquisicion, umbralInicial);
    }
    @Override
    public void dispara(){
        System.out.println("Sensor de presión activado");
    }
}

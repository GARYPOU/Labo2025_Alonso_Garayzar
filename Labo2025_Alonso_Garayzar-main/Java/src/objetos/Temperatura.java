package objetos;

import java.time.LocalDate;

public class Temperatura extends Sensor{

    public Temperatura(boolean estado, double medida, LocalDate adquisicion,  double umbralInicial) {
        super(estado, medida, adquisicion, umbralInicial);

    }
}

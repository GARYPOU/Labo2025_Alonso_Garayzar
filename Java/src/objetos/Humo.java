package objetos;

import java.time.LocalDate;

public class Humo extends Sensor{

    public Humo(boolean estado, double medida, LocalDate adquisicion,double umbralInicial) {
        super(estado, medida, adquisicion, umbralInicial);

    }
}

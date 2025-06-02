package objetos;

import java.time.LocalDate;

public class Precion extends Sensor{

    public Precion(boolean estado, double medida, LocalDate adquisicion, double umbralInicial) {
        super(estado, medida, adquisicion, umbralInicial);
    }
}

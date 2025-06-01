package objetos;

import java.time.LocalDate;

public class Precion extends Sensor{

    public Precion(boolean estado, double medida, double umbralInicial, LocalDate adquisicion) {
        super(estado, medida, adquisicion, umbralInicial);
    }
}

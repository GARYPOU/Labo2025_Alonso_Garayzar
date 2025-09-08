package Arco_Flecha;

import java.util.HashSet;

public class BeneficioComida extends Beneficio implements Acumulable {
    private HashSet<String> gaseosas;

    @Override
    public int descuentoExtra() {
        return 4500;
    }
}

package Arco_Flecha;

import objetos.Bebidas.Bebida;

import java.util.HashSet;

public class BeneficioGaseosa extends Beneficio implements Acumulable{
    private HashSet<Bebida>gaseosas;

    @Override
    public int descuentoExtra() {
        return 1000;
    }
}

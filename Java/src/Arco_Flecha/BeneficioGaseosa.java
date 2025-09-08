package Arco_Flecha;

import objetos.Bebidas.Bebida;

import java.util.HashSet;

public class BeneficioGaseosa extends Beneficio implements Acumulable{
    private HashSet<Bebida>gaseosas;

    public BeneficioGaseosa(String nombre, Usuario user, HashSet<Bebida> gaseosas) {
        super(nombre, user);
        this.gaseosas = gaseosas;
    }

    public HashSet<Bebida> getGaseosas() {
        return gaseosas;
    }

    public void setGaseosas(HashSet<Bebida> gaseosas) {
        this.gaseosas = gaseosas;
    }

    @Override
    public int descuentoExtra() {
        return 1000;
    }
}

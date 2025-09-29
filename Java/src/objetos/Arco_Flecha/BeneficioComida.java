package objetos.Arco_Flecha;

import java.util.HashSet;

public class BeneficioComida extends Beneficio implements Acumulable {
    private HashSet<String> gaseosas;

    public BeneficioComida(String nombre, Usuario user, HashSet<String> gaseosas) {
        super(nombre, user);
        this.gaseosas = gaseosas;
    }

    public HashSet<String> getGaseosas() {
        return gaseosas;
    }

    public void setGaseosas(HashSet<String> gaseosas) {
        this.gaseosas = gaseosas;
    }

    @Override
    public int descuentoExtra() {
        return 4500;
    }
}

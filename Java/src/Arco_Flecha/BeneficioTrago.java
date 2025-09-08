package Arco_Flecha;

import java.util.HashSet;

public class BeneficioTrago extends Beneficio{
    private HashSet<String>trago;

    public BeneficioTrago(String nombre, Usuario user, HashSet<String> trago) {
        super(nombre, user);
        this.trago = trago;
    }

    public HashSet<String> getTrago() {
        return trago;
    }

    public void setTrago(HashSet<String> trago) {
        this.trago = trago;
    }
}

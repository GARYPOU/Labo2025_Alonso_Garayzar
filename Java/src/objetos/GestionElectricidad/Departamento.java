package objetos.GestionElectricidad;

import java.util.HashMap;
import java.util.Map;

public class Departamento extends Vivienda{
    private int cantAmbientes;
    private int Precio=50;

    public Departamento(String direccion, int codigoPostal, Dueño dueño, HashMap<Mes, Integer> consumo) {
        super(direccion, codigoPostal, dueño, consumo);
    }

    @Override
    public int consumo(Mes m2) {
        int total=0;
        for (Map.Entry<Mes,Integer> m: getConsumo().entrySet()){
            if(m2.equals(m.getKey())){
                total=m.getValue()*Precio;
            }
        }
        return total;
    }
}

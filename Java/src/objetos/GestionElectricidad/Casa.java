package objetos.GestionElectricidad;

import java.util.HashMap;
import java.util.Map;

public class Casa extends Vivienda{
    private int cubiertos;
    private int descubiertos;
    private static int Precio=100;
    public Casa(String direccion, int codigoPostal, Dueño dueño, HashMap<Mes, Integer> consumo) {
        super(direccion, codigoPostal, dueño, consumo);
    }



    public int getCubiertos() {
        return cubiertos;
    }

    public void setCubiertos(int cubiertos) {
        this.cubiertos = cubiertos;
    }

    public int getDescubiertos() {
        return descubiertos;
    }

    public void setDescubiertos(int descubiertos) {
        this.descubiertos = descubiertos;
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

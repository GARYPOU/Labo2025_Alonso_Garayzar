package objetos.GestionElectricidad;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class Vivienda {
    private String direccion;
    private int codigoPostal;
    private Dueño dueño;
    private HashMap<Mes,Integer> consumo;
    private HashMap<Integer,HashMap<Mes,Integer>> consumoAño;

    public Vivienda(String direccion, int codigoPostal, Dueño dueño, HashMap<Mes, Integer> consumo) {
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.dueño = dueño;
        this.consumo = consumo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Dueño getDueño() {
        return dueño;
    }

    public void setDueño(Dueño dueño) {
        this.dueño = dueño;
    }

    public HashMap<Mes, Integer> getConsumo() {
        return consumo;
    }

    public void setConsumo(HashMap<Mes, Integer> consumo) {
        this.consumo = consumo;
    }

    public void cargarConsumo(Mes m, int cons){
        consumo.put(m,cons);
    }

    public HashMap<Integer, HashMap<Mes, Integer>> getConsumoAño() {
        return consumoAño;
    }

    public void setConsumoAño(HashMap<Integer, HashMap<Mes, Integer>> consumoAño) {
        this.consumoAño = consumoAño;
    }

    public abstract int consumo(Mes m2);

    public boolean Descuento(Mes m2){
        boolean verda=false;
        for (Map.Entry<Integer,HashMap<Mes,Integer>> año: consumoAño.entrySet()){
            for(Map.Entry<Mes,Integer> m: año.getValue().entrySet()){
                if(año.getKey()-1==LocalDate.now().getYear()-1){
                    
                }
            }
        }
        return verda;
    }
}

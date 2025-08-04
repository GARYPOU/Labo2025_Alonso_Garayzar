package objetos.Vacunatorio;

import personas.Ciudadano;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Sistema {
    HashMap<Integer, Ciudadano> vacunados;

    public Sistema(HashMap<Integer, Ciudadano> vacunados) {
        this.vacunados = vacunados;
    }

    public HashMap<Integer, Ciudadano> getVacunados() {
        return vacunados;
    }

    public void setVacunados(HashMap<Integer, Ciudadano> vacunados) {
        this.vacunados = vacunados;
    }

    public void historial_vacunas(int dni) {
        for (Map.Entry<Integer, Ciudadano> v : vacunados.entrySet()) {
            int dnis = v.getKey();
            Ciudadano ciu = v.getValue();
            if (dnis == dni) {
                for (Vacuna vac : ciu.getVacunas()) {
                    System.out.println("Historial Vacunas" + vac.getN_fabricion());
                }


            }
        }

    }

    public void vacunados_por_provincia() {
        HashMap<String, Ciudadano> provincias = new HashMap<>();
        for (Map.Entry<Integer, Ciudadano> v : vacunados.entrySet()) {
            provincias.put(v.getValue().getProvincia(), v.getValue());
        }
        for (Map.Entry<String, Ciudadano> p : provincias.entrySet()) {
            System.out.println("La provincia es:" + p.getKey() + "y sus ciudadanos" + p.getValue().getDni());
        }

    }

    public HashSet cantidadvacunas(int cantidad) {
        HashSet<Ciudadano> cumplen=new HashSet<>();
        for (Map.Entry<Integer, Ciudadano> v : vacunados.entrySet()) {
            if (v.getValue().getVacunas().size()>=cantidad){
                cumplen.add(v.getValue());
            }
        }
        return cumplen;
    }

    public HashSet ciudadanos(Vacuna vac){
        HashSet<Ciudadano> dieron=new HashSet<>();
        for (Map.Entry<Integer, Ciudadano> v : vacunados.entrySet()) {
            for(Vacuna va: v.getValue().getVacunas()){
                if(vac==va){
                    dieron.add(v.getValue());
                }
            }
        }
        return dieron;
        }

}

package Arco_Flecha;

import java.util.HashSet;

public class Bar {
    private HashSet<Diana>dianas;
    private int cantPersonas;
    private int recaudado;
    private HashSet<Beneficio>beneficiosImp;
    private HashSet<Acumulable>beneficiosAcu;

    public Bar(HashSet<Diana> dianas, int cantPersonas, int recaudado, HashSet<Beneficio> beneficiosImp, HashSet<Acumulable> beneficiosAcu) {
        this.dianas = dianas;
        this.cantPersonas = cantPersonas;
        this.recaudado = recaudado;
        this.beneficiosImp = beneficiosImp;
        this.beneficiosAcu = beneficiosAcu;
    }

    public HashSet<Diana> getDianas() {
        return dianas;
    }

    public void setDianas(HashSet<Diana> dianas) {
        this.dianas = dianas;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public int getRecaudado() {
        return recaudado;
    }

    public void setRecaudado(int recaudado) {
        this.recaudado = recaudado;
    }

    public HashSet<Beneficio> getBeneficiosImp() {
        return beneficiosImp;
    }

    public void setBeneficiosImp(HashSet<Beneficio> beneficiosImp) {
        this.beneficiosImp = beneficiosImp;
    }

    public HashSet<Acumulable> getBeneficiosAcu() {
        return beneficiosAcu;
    }

    public void setBeneficiosAcu(HashSet<Acumulable> beneficiosAcu) {
        this.beneficiosAcu = beneficiosAcu;
    }

    public int cantTragos(){
        int cant=0;
        for(Beneficio b: beneficiosImp){
            if(b.getClass()== BeneficioTrago.class){

                cant=cant+1;
            }
        }
        return cant;
    }


}

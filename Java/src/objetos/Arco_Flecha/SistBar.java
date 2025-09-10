package Arco_Flecha;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

public class SistBar {
    private HashSet<Bar>bares;
    private HashSet<Diana>dianas;
    private HashSet<Beneficio>beneficios;

    public SistBar(HashSet<Bar> bares, HashSet<Diana> dianas, HashSet<Beneficio> beneficios) {
        this.bares = bares;
        this.dianas = dianas;
        this.beneficios = beneficios;
    }

    public HashSet<Bar> getBares() {
        return bares;
    }

    public void setBares(HashSet<Bar> bares) {
        this.bares = bares;
    }

    public HashSet<Diana> getDianas() {
        return dianas;
    }

    public void setDianas(HashSet<Diana> dianas) {
        this.dianas = dianas;
    }

    public HashSet<Beneficio> getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(HashSet<Beneficio> beneficios) {
        this.beneficios = beneficios;
    }
    public Diana masPuntajesDist(){
        Diana d1 = null;
        for(Diana d:dianas){
            if(d.getPuntajes().size()>d1.getPuntajes().size()){
                d1=d;
            }
        }
        return d1;
    }

    public static void main(String[] args) {
        Circular d1 = new Circular("josue",Nivel.DIFICIL, new HashMap<>());
        Usuario u1 = new Usuario("gaston", "liras", LocalDate.now());
        BeneficioTrago bt1 = new BeneficioTrago("martini",u1,new HashSet<>());
        HashSet<Diana>dianas =new HashSet<>();
        Bar b1 = new Bar(dianas,5,4567,new HashSet<>(),new HashSet<>());
        HashSet<Bar>bares= new HashSet<>();
        SistBar s1 = new SistBar(bares,dianas,new HashSet<>());

        s1.masPuntajesDist();
        d1.calcularArea();
        d1.calcularPuntos();
        
    }
}

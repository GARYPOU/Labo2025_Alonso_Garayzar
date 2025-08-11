package objetos.Poblacion;

import java.util.HashSet;

public class Mundo {
    private HashSet<Lugar>lugares;
    private HashSet<Continente>continentes;

    public Mundo(HashSet<Lugar> lugares, HashSet<Continente> continentes) {
        this.lugares = lugares;
        this.continentes = continentes;
    }
    public Mundo() {
        this.lugares = new HashSet<>();
        this.continentes = new HashSet<>();
    }

    public HashSet<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(HashSet<Lugar> lugares) {
        this.lugares = lugares;
    }

    public HashSet<Continente> getContinentes() {
        return continentes;
    }

    public void setContinentes(HashSet<Continente> continentes) {
        this.continentes = continentes;
    }

    public void AgregarLugar(Lugar l1){
        lugares.add(l1);
    }
    public void BorrarLugar(Lugar l1){
        lugares.remove(l1);
    }
    public void ActualizarLugar(Lugar l1, Lugar l2){
        lugares.remove(l1);
        lugares.add(l2);
    }
    public int ConsultarPoblacion(int cod){
        int total=0;
        for (Lugar l: lugares){
            if(l.getCodigo()==cod){
                total=l.calcularPobla();
            }
        }
        return total;     
    }
    public Continente ContMas(){
        int total=0;
        Continente co = new Continente();
        for (Continente cont:continentes){
            if(cont.calcularPobla()>total){
                co=cont;
                total=co.calcularPobla();

            }
        }
        return co;
    }
    public Continente ContMenos(){
        int total=0;
        Continente co = new Continente();
        for (Continente cont:continentes){
            if(cont.calcularPobla()<total){
                co=cont;
                total=co.calcularPobla();

            }
        }
        return co;
    }

    public static void main(String[] args) {
        Mundo m1 = new Mundo();
        Continente c1 = new Continente();
        Pais p1 = new Pais();
        Pais p2 = new Pais();

        m1.AgregarLugar(p1);
        m1.BorrarLugar(c1);
        m1.ActualizarLugar(p1,p2);
        m1.ConsultarPoblacion(2);
        System.out.println("Pais con mas poblacion:"+c1.PaisMas().getNombre());
        System.out.println("Pais con menos poblacion:"+c1.PaisMenos().getNombre());
        System.out.println("Pais con mas poblacion:"+m1.ContMas().getNombre());
        System.out.println("Pais con menos poblacion:"+m1.ContMenos().getNombre());


    }
}

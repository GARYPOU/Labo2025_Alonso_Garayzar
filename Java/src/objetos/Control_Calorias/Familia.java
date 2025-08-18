package objetos.Control_Calorias;

import java.util.HashSet;

public class Familia {
    private HashSet<Pariente>parientes;

    public Familia(HashSet<Pariente> parientes) {
        this.parientes = parientes;
    }

    public Familia() {
        this.parientes = new HashSet<>();
    }

    public HashSet<Pariente> getParientes() {
        return parientes;
    }

    public void setParientes(HashSet<Pariente> parientes) {
        this.parientes = parientes;
    }
    public double cantCalorias(){
        double total=0;        for (Pariente p:parientes){
            for (Plato pl: p.getPlatos()){
                total+=pl.getCantCalorias();
            }
        }
        return total;
    }
    public Pariente masCalorias(){
        double cantCalo=1000000000;
        Pariente pa= new Pariente();
        for (Pariente p:parientes){
            if(p.cantCalorias()>cantCalo){
                cantCalo=p.cantCalorias();
                pa=p;


            }
        }
        return pa;
    }
    public Pariente menosCalorias(){
        double cantCalo=1000000000;
        Pariente pa= new Pariente();
        for (Pariente p:parientes){
            if(p.cantCalorias()<cantCalo){
                cantCalo=p.cantCalorias();
                pa=p;


            }
        }
        return pa;
    }

    public static void main(String[] args) {
        Pariente p1 = new Pariente();
        Plato pl1 = new Plato();
        Familia f1 = new Familia();

        p1.comer(pl1);
        p1.antiguedad();
        f1.cantCalorias();
        f1.masCalorias();
        f1.menosCalorias();
    }
}

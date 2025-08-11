package objetos.Control_Calorias;

import java.util.HashSet;

public class Familia {
    private HashSet<Pariente>parientes;

    public Familia(HashSet<Pariente> parientes) {
        this.parientes = parientes;
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
        double cantCalo=0;
        Pariente pa= new Pariente();
        for (Pariente p:parientes){
            for (Plato pl: p.getPlatos()){

                if(cantCalo<pl.getCantCalorias()){
                    pa=p;
                    cantCalo= pl.getCantCalorias();
                }


            }
        }
        return pa;
    }
    public Pariente menosCalorias(){
        double cantCalo=0;
        Pariente pa= new Pariente();
        for (Pariente p:parientes){
            for (Plato pl: p.getPlatos()){

                if(cantCalo>pl.getCantCalorias()){
                    pa=p;
                    cantCalo= pl.getCantCalorias();
                }


            }
        }
        return pa;
    }
}

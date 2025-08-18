package objetos.Control_Calorias;

import personas.Persona;

import java.time.LocalDate;
import java.util.HashSet;

public class Pariente extends Persona {
    private HashSet<Plato>platos;
    public Pariente(String nom, LocalDate nacimiento, HashSet<Plato>platos) {
        super(nom, nacimiento);
        this.platos=platos;
    }
    public Pariente() {
        super("Tralalero", LocalDate.now());
        this.platos=new HashSet<>();
    }

    public HashSet<Plato> getPlatos() {
        return platos;
    }

    public void comer(Plato p){
        platos.add(p);

    }
    public void setPlatos(HashSet<Plato> platos) {
        this.platos = platos;
    }
    public double cantCalorias(){
        double total=0;
        for(Plato p: platos){
            total=p.cantIngrdientes();
        }
        return total;
    }
}

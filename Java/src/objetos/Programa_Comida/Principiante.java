package objetos.Programa_Comida;

import objetos.Control_Calorias.Ingredente;

import java.util.HashSet;
import java.util.Map;

public class Principiante extends Participante implements plato_entrada{
    private HashSet<Ingredente>ingredientes_prohibidos;


    public Principiante(HashSet<Ingredente> ingredientes_prohibidos) {
        this.ingredientes_prohibidos = ingredientes_prohibidos;
    }
    public Principiante() {
        this.ingredientes_prohibidos = new HashSet<>();
    }

    public HashSet<Ingredente> getIngredientes_prohibidos() {
        return ingredientes_prohibidos;
    }

    public void setIngredientes_prohibidos(HashSet<Ingredente> ingredientes_prohibidos) {
        this.ingredientes_prohibidos = ingredientes_prohibidos;
    }

    @Override
    public void preparar() {

        System.out.println("Ya guarde todos los elementos prohibidos y no voy a usar:");
        for (Ingredente i: ingredientes_prohibidos){
            System.out.println(i.getNombre());
        }
    }

    @Override
    public void ccinar(Plato p1) {
        try {


            for (Map.Entry<Ingredente, Integer> i : p1.getIngredientes().entrySet()) {
                if (ingredientes_prohibidos.contains(i.getKey())) {
                    throw new UsoIngredientesProhibidos("El principiante quiere usar ingredientes prohibidos");
                }
            }
            System.out.println("Cocine entrada");

        }
        catch (UsoIngredientesProhibidos e){
            System.out.println(e);
        }
    }

    @Override
    public void servir_entrada() {
        System.out.println("Servi entrada");
    }
}

package objetos.Programa_Comida;

import objetos.Control_Calorias.Ingredente;

import java.util.HashSet;

public class Principiante extends Participante implements plato_entrada{
    private HashSet<Ingredente>ingredientes_prohibidos;


    @Override
    public void preparar() {
        String ing=" ";
        for (Ingredente i: ingredientes_prohibidos){
            ing=ing+"_"+i;
        }
        System.out.println("Ya guarde todos los elementos prohibidos y no voy a usar:"+ing);
    }

    @Override
    public void ccinar() {

    }

    @Override
    public void servir_entrada() {

    }
}

package objetos.Programa_Comida;

import objetos.Control_Calorias.Ingredente;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Intermedio extends Participante implements plato_principal{
    private HashMap<Ingredente,Integer>ingrediente_cant;

    @Override
    public void preparar() {
        String ing=" ";

        for(Map.Entry<Ingredente,Integer> i: ingrediente_cant.entrySet()){
            if(i.getValue()>1){
                ing=ing+"_"+i.getKey().getNombre();
            }
        }
        System.out.println("“Mi stock a utilizar es de"+ing);
    }

    @Override
    public void ccinar() {

    }

    @Override
    public void servir_principal() {

    }
}

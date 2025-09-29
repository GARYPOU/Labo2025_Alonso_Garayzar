package objetos.Programa_Comida;

import objetos.Control_Calorias.Ingredente;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Intermedio extends Participante implements plato_principal{
    private HashMap<Ingredente,Integer>ingrediente_cant;

    public Intermedio(HashMap<Ingredente, Integer> ingrediente_cant) {
        this.ingrediente_cant = ingrediente_cant;
    }
    public Intermedio() {
        this.ingrediente_cant = new HashMap<>();
    }

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
    public void ccinar(Plato p1) {
        try {
            int cont = 0;
            for (Map.Entry<Ingredente, Integer> i : ingrediente_cant.entrySet()) {
                for (Map.Entry<Ingredente, Integer> i2 : p1.getIngredientes().entrySet()) {
                    if (i.getKey() == i2.getKey()) {
                        if (i.getValue() == i2.getValue()) {
                            cont = cont + 1;
                        }
                    }

                }
            }
            if (cont == p1.getIngredientes().size()) {
                System.out.println("Se puede hacer");
            } else {
                throw new NoHayIngredientes("No tiene los ingredientes necesarios");
            }
        }
        catch (NoHayIngredientes e){
            System.out.println(e);
        }
    }

    @Override
    public void servir_principal() {

    }
}

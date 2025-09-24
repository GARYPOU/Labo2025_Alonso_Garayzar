package objetos.Programa_Comida;

import objetos.Control_Calorias.Ingredente;

import java.util.Map;

public class Experto extends Participante implements plato_entrada,plato_principal{
    private static int tiempo = 80;
    private int tiempo_individual = tiempo;

    @Override
    public void preparar() {
        System.out.println("perdi 5 minutos preparando la mesa por lo que mi tiempo restante es"+(tiempo_individual-5));
    }

    @Override
    public void ccinar() {

    }

    @Override
    public void servir_entrada() {

    }

    @Override
    public void servir_principal() {

    }
}

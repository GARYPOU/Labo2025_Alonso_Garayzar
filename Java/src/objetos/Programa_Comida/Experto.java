package objetos.Programa_Comida;

import objetos.Control_Calorias.Ingredente;

import java.util.Map;

public class Experto extends Participante implements plato_entrada,plato_principal{
    private static int tiempo = 80;
    private int tiempo_individual;


    public Experto(int tiempo_individual) {
        this.tiempo_individual = tiempo_individual;
    }
    public Experto() {
        this.tiempo_individual = tiempo;
    }

    @Override
    public void preparar() {
        System.out.println("perdi 5 minutos preparando la mesa por lo que mi tiempo restante es"+(tiempo_individual-5));
    }

    @Override
    public void ccinar(Plato p1) {
        try {
            if (tiempo_individual == 0){
                throw new NoHayTiempo("te quedaste sin tiempo");
            }
        }
        catch (NoHayTiempo e){
            System.out.println(e);
        }
    }

    @Override
    public void servir_entrada() {
        System.out.println("servir entrada");
    }

    @Override
    public void servir_principal() {
        System.out.println("servir principal");
    }
}

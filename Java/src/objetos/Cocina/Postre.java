package objetos.Cocina;

public class Postre extends Receta{
    private boolean diabetico;
    private double temperatura;

    @Override
    public void realizarReceta(){
        System.out.println("Los pasos a seguir son");
        for(int i=0; i<getPasos().size(); i++){
            System.out.println(getPasos().get(i));
            System.out.println("Acordate de mantener la cocina limpia y el horno al mínimo");

        }
    }

}

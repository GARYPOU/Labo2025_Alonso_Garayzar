package objetos.Cocina;

public class Entrada extends Receta{
    private Estado estado;

@Override
    public void realizarReceta(){
        if(Estado.FRIA.compareTo(estado)==0){
            System.out.println("Los pasos a seguir son");
            for(int i=0; i<getPasos().size(); i++){
                System.out.println(getPasos().get(i));
            }
            System.out.println("Acordate de meterlo en la heladera");
        }
        else{
            System.out.println("Acordate de prender el horno");
            System.out.println("Los pasos a seguir son");
            for(int i=0; i<getPasos().size(); i++){
                System.out.println(getPasos().get(i));
            }

        }
    }
}

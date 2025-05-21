package objetos;

public class Camioneta extends Automovil {
    private int peso;


    public Camioneta(int peso, String marca, String modelo, String color, int velocidad,int cantRuedas, int patente){
        super(marca, modelo, color,velocidad, cantRuedas, patente);
        this.peso=peso;
    }
    public Camioneta(){
        super();
    }




    public void agregarCarga (int agregar){
        if(peso+agregar>peso){

        }
        else {
            this.peso=peso+agregar;
        }
    }



    public static void main(String[] args) {
        Camioneta c1 = new Camioneta(1900, "Fiat", "corola", "Rojo", 120, 4, 123);
        System.out.println("Aceleracion="+ c1.calcacel()+"km/h2");
        System.out.println("Frenado="+ c1.calcfreno());
        System.out.println("velocidad actual"+ c1.getVelocidad());

    }

}


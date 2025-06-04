package objetos;

public class Camioneta extends Automovil {
    private int peso;


    public Camioneta(int peso, Marca marca, String modelo, Color color, int velocidad,Rueda cantRuedas, int patente){

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
        Camioneta c1 = new Camioneta(1900, Marca.FIAT, "corola", Color.AMARILLO, 120, Rueda.CUATRORUEDAS, 123);
        System.out.println("Aceleracion="+ c1.calcacel()+"km/h2");
        System.out.println("Frenado="+ c1.calcfreno());
        System.out.println("velocidad actual"+ c1.getVelocidad());

    }

}


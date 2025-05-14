package objetos;

public class Carro extends Automovil {



    public Carro(String marca, String modelo, String color, int velocidad, String patente){
        super(marca, modelo, color,velocidad, patente);
    }








    public static void main(String[] args) {
        Carro c1 = new Carro("Fiat","corola","Rojo",120);
        System.out.println("Aceleracion="+ c1.calcacel()+"km/h2");
        System.out.println("Frenado="+ c1.calcfreno());
        System.out.println("velocidad actual"+ c1.velocidad);
    }
}

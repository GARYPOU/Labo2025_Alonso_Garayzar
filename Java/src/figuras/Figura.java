package figuras;
import figuras.Circulo;
import figuras.Rectangulo;
public abstract class Figura {
    abstract float calculararea();
    abstract float calcularperi();

    public static void main(String[] args) {
        Circulo c1=new Circulo();
        Rectangulo r1=new Rectangulo();
        System.out.println(c1.calculararea());
        System.out.println(c1.calcularperi());
        System.out.println(r1.calculararea());
        System.out.println(r1.calcularperi());


    }

}


package figuras;
import figuras.Circulo;
import figuras.Rectangulo;
public class Figura {


    public static void main(String[] args) {
        Circulo c1=new Circulo();
        Rectangulo r1=new Rectangulo();
        System.out.println(c1.calculararea());
        System.out.println(c1.calcularperi());
        System.out.println(r1.calcularareas());
        System.out.println(r1.calcularperis());


    }

}


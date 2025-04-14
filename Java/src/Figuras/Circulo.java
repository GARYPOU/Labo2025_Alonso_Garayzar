package Figuras;

public class Circulo {
    private int radio;

    public Circulo(){
        this.radio=2;
    }

    public Circulo(int radio){
        this.radio=radio;
    }

    public int getRadio(){
        return radio;
    }

    public void setRadio(int radio){
        this.radio=radio;
    }

    public float calculararea(){
        float x;
        x = (float) (3.14 * this.radio*this.radio);
        return x;
    }

    public float calcularperi(){
        float x;
        x = (float) (2 * 3.14 *this.radio);
        return x;
    }


    public static void main(String[] args) {
        Circulo c1 = new Circulo();
        System.out.println("area="+ c1.calculararea());
        System.out.println("perimetro="+ c1.calcularperi());
    }
}

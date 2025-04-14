public class Rectangulo {
    private int altura;
    private int base;


    public Rectangulo(int alt, int bas){
        this.altura=alt;
        this.base=bas;

    }



    public int getAltura(){
        return altura;
    }

    public void setAltura(int alt){
        this.altura=alt;
    }

    public int getBase(){
        return base;
    }

    public void setBase(int bas){
        this.base=bas;
    }

    public float calculararea(){
        float x;
        x = (float) (this.altura*this.base);
        return x;
    }

    public float calcularperi(){
        float x;
        x = (float) (2 * this.altura + 2*this.base);
        return x;
    }


    public static void main(String[] args) {
        Rectangulo r1 = new Rectangulo(2,3);
        System.out.println("area="+ r1.calculararea());
        System.out.println("perimetro="+ r1.calcularperi());
    }
}

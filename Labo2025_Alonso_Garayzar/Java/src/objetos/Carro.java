package objetos;

public class Carro {
    private String marca;
    private String modelo;
    private String color;
    private int velocidad;


    public Carro(String mar, String mod, String col, int vel){
        this.marca=mar;
        this.modelo=mod;
        this.color=col;
        this.velocidad=vel;

    }



    public String getMarca(){
        return marca;
    }

    public void setMarca(String mar){
        this.marca=mar;
    }

    public String getModelo(){
        return modelo;
    }

    public void setModelo(String mod){
        this.modelo=mod;
    }
    public String getColor(){
        return color;
    }

    public void setColor(String col){
        this.color=col;
    }
    public int getVelocidad(){
        return velocidad;
    }

    public void setVelocidad(int vel){
        this.velocidad=vel;
    }


    public float calcacel(){
        float x;
        x = (float) (velocidad/60);
        return x;
    }

    public float calcfreno(){
        float x;
        x = (float) ((velocidad*velocidad)/180);
        return x;
    }


    public static void main(String[] args) {
        Carro c1 = new Carro("Fiat","corola","Rojo",120);
        System.out.println("Aceleracion="+ c1.calcacel()+"km/h2");
        System.out.println("Frenado="+ c1.calcfreno());
        System.out.println("velocidad actual"+ c1.velocidad);
    }
}

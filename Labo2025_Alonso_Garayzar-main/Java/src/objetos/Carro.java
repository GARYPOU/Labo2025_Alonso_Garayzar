package objetos;

public class Carro extends Automovil {
    private boolean descapotable;


    public Carro(Boolean descapotable, String marca, String modelo, String color, int velocidad, int cantRuedas, int patente){
        super(marca, modelo, color,velocidad,cantRuedas, patente);
        this.descapotable=descapotable;
    }
    public Carro(){
        super();
        descapotable=true;
    }

    public boolean isDescapotable() {
        return descapotable;
    }

    public void setDescapotable(boolean descapotable) {
        this.descapotable = descapotable;
    }
    public boolean carroDescapotable(){
        return descapotable;
    }

    public static void main(String[] args) {
        Carro c1 = new Carro(true, "Fiat", "corola", "Rojo", 120,4,204);
        System.out.println("Aceleracion="+ c1.calcacel()+"km/h2");
        System.out.println("Frenado="+ c1.calcfreno());
        System.out.println("velocidad actual"+ c1.getVelocidad());

    }
}

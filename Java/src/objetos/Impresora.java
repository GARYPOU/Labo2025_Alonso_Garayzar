package objetos;

public class Impresora extends Dissalida{
    private boolean met;
    private Metodo metodoImpresion;


    public Impresora(){
        super("impresora","intel","455",2000000,4,2);
        this.met=true;
        if(met){
            this.metodoImpresion=Metodo.LASER;
        }
        this.metodoImpresion=Metodo.COLOR;

    }

    public Impresora(String nombre, String fabricante, String modelo, double precio, int stock, int puertos, boolean met, Metodo metodoImpresion) {
        super(nombre, fabricante, modelo, precio, stock, puertos);
        this.met = met;
        this.metodoImpresion = metodoImpresion;
    }
}

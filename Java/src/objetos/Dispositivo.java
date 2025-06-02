package objetos;

public class Dispositivo extends Componente{
    private int puertos;


    public Dispositivo(String nombre,String fabricante, String modelo, double precio, int stock, int puertos) {

        super(nombre, fabricante, modelo, precio, stock);
        this.puertos = puertos;
    }
    public Dispositivo() {

        super("Teclado", "intel", "kumara", 200, 100);
        this.puertos = 1;
    }

    public int getPuertos() {
        return puertos;
    }

    public void setPuertos(int puertos) {
        this.puertos = puertos;
    }
    public void reducirStock(int cant){
        setStock(cant);
    }
}

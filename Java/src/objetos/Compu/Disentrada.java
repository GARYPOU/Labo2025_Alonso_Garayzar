package objetos.Compu;

public class Disentrada extends Dispositivo{
    private String conector;


    public Disentrada(String nombre, String fabricante, String modelo, double precio, int stock, int puertos, String conector) {
        super(nombre, fabricante, modelo, precio, stock, puertos);
        this.conector = conector;
    }
}

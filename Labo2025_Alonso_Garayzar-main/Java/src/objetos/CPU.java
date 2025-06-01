package objetos;

public class CPU extends Componente{

    public CPU(String nombre, String fabricante, String modelo, double precio, int stock) {
        super(nombre, fabricante, modelo, precio, stock);
    }
    public CPU() {
        super("I7", "intel", "I7", 500000, 7);
    }

    public void reducirStock(int cant){
      setStock(cant);
    }

}

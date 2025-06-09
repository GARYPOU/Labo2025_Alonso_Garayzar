package objetos.Electrodomesticos;

public class Electrodomestico {
    private String nombre;
    private double precio;
    private int stock;

    public Electrodomestico(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    public Electrodomestico(){
        this.nombre="";
        this.stock=0;
        this.precio=0.0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

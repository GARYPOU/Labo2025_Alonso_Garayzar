package objetos.Electrodomesticos;

public abstract class Electrodomestico {
    private String nombre;
    private double precio;
    private int stock;
    private Seccion seccion;

    public Electrodomestico(String nombre, double precio, int stock, Seccion seccion) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.seccion = seccion;
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

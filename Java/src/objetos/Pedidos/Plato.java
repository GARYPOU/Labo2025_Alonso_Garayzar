package objetos.Pedidos;

public class Plato {
    private Comida nombre;
    private float precio;

    public Plato(){
        this.nombre=Comida.CHORIZO;
        this.precio=200;
    }
    public Plato(Comida nombre, float precio){
        this.nombre=nombre;
        this.precio=precio;
    }

    public Comida getNombre() {
        return nombre;
    }

    public void setNombre(Comida nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}

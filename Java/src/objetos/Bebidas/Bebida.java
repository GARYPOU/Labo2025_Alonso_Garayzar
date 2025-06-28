package objetos.Bebidas;

public abstract class Bebida{
   private String nombre;
   private int positividad;
   private int negatividad;

    public Bebida(String nombre, int positividad, int negatividad) {
        this.nombre = nombre;
        this.positividad = positividad;
        this.negatividad = negatividad;
    }

    public Bebida(String nombre, int positividad) {
        this.nombre = nombre;
        this.positividad = positividad;

    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPositividad() {
        return positividad;
    }

    public void setPositividad(int positividad) {
        this.positividad = positividad;
    }

    public int getNegatividad() {
        return negatividad;
    }

    public void setNegatividad(int negatividad) {
        this.negatividad = negatividad;
    }
    abstract int calcularCoficiente();
}

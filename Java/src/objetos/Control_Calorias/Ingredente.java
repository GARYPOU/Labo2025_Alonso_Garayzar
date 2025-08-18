package objetos.Control_Calorias;

public class Ingredente {
    private String nombre;
    private int caloria;

    public Ingredente(String nombre, int caloria) {
        this.nombre = nombre;
        this.caloria = caloria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCaloria() {
        return caloria;
    }

    public void setCaloria(int caloria) {
        this.caloria = caloria;
    }
}

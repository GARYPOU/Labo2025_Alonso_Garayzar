package objetos.Empresa;

import java.util.HashMap;
import java.util.HashSet;

public class Proyecto {
    int ganancia;
    String nombre;

    public Proyecto(int ganancia) {
        this.ganancia = ganancia;
    }

    public int getGanancia() {
        return ganancia;
    }

    public void setGanancia(int ganancia) {
        this.ganancia = ganancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

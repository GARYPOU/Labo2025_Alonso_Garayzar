package objetos.Poblacion;

import java.util.HashSet;

public abstract class Lugar {
    private String nombre;
    private int codigo;
    private HashSet<Coordenadas>cordenadas;

    public Lugar(String nombre, int codigo, HashSet<Coordenadas> cordenadas) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.cordenadas = cordenadas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public HashSet<Coordenadas> getCordenadas() {
        return cordenadas;
    }

    public void setCordenadas(HashSet<Coordenadas> cordenadas) {
        this.cordenadas = cordenadas;
    }
    abstract int calcularPobla();
}

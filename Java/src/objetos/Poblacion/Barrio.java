package objetos.Poblacion;

import java.util.HashSet;

public class Barrio extends Lugar{
    private int poblacion;

    public Barrio(String nombre, int codigo, HashSet<Coordenadas> cordenadas, int poblacion) {
        super(nombre, codigo, cordenadas);
        this.poblacion = poblacion;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    @Override
    int calcularPobla() {
        return poblacion;
    }


}

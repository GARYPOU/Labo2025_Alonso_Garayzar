package objetos.Poblacion;

import java.util.HashSet;

public class Ciudad extends Lugar{
    private HashSet<Barrio>barrios;

    public Ciudad(String nombre, int codigo, HashSet<Coordenadas> cordenadas, HashSet<Barrio> barrios) {
        super(nombre, codigo, cordenadas);
        this.barrios = barrios;
    }

    public HashSet<Barrio> getBarrios() {
        return barrios;
    }

    public void setBarrios(HashSet<Barrio> barrios) {
        this.barrios = barrios;

    }

    @Override
    int calcularPobla() {
        int total=0;
        for (Barrio b: barrios){
            total=b.calcularPobla();
        }
        return total;
    }
}

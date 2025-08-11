package objetos.Poblacion;

import java.util.HashSet;

public class Provincia extends Lugar{
    HashSet<Ciudad>ciudades;

    public Provincia(String nombre, int codigo, HashSet<Coordenadas> cordenadas, HashSet<Ciudad> ciudades) {
        super(nombre, codigo, cordenadas);
        this.ciudades = ciudades;
    }

    public HashSet<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(HashSet<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }
    @Override
    int calcularPobla() {
        int total = 0;
        for (Ciudad c : ciudades) {
            for (Barrio b : c.getBarrios()) {
                total = b.calcularPobla();
            }
        }
        return total;
    }
}

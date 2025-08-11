package objetos.Poblacion;

import java.util.HashSet;

public class Pais extends Lugar {
    private HashSet<Provincia> provincias;

    public Pais(String nombre, int codigo, HashSet<Coordenadas> cordenadas, HashSet<Provincia> provincias) {
        super(nombre, codigo, cordenadas);
        this.provincias = provincias;
    }
    public Pais() {
        super("Bolivia",1323,new HashSet<>());
        this.provincias = new HashSet<>();
    }

    public HashSet<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(HashSet<Provincia> provincias) {
        this.provincias = provincias;
    }

    @Override
    int calcularPobla() {
        int total = 0;
        for (Provincia p : provincias) {
            for (Ciudad c : p.getCiudades()) {
                for (Barrio b : c.getBarrios()) {
                    total = b.calcularPobla();
                }
            }
        }
            return total;

    }


}

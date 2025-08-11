package objetos.Poblacion;

import java.util.HashSet;

public class Continente extends Lugar{
    private HashSet<Pais>paises;


    public Continente(String nombre, int codigo, HashSet<Coordenadas> cordenadas, HashSet<Pais> paises) {
        super(nombre, codigo, cordenadas);
        this.paises = paises;
    }
    public Continente() {
        super("pangea",0,new HashSet<>());
        this.paises = new HashSet<>();
    }

    public void setPaises(HashSet<Pais> paises) {
        this.paises = paises;
    }
    @Override
    int calcularPobla() {
        int total = 0;
        for (Pais pa : paises)
        for (Provincia p : pa.getProvincias()) {
            for (Ciudad c : p.getCiudades()) {
                for (Barrio b : c.getBarrios()) {
                    total = b.calcularPobla();
                }
            }
        }
        return total;

    }

    public Pais PaisMas(){
        int total=0;
        Pais pa = new Pais();
        for (Pais p: paises){
            if(p.calcularPobla()>total){
                pa=p;
                total=p.calcularPobla();

            }
        }
        return pa;
    }
    public Pais PaisMenos(){
        int total=0;
        Pais pa = new Pais();
        for (Pais p: paises){
            if(p.calcularPobla()<total){
                pa=p;
                total=p.calcularPobla();

            }
        }
        return pa;
    }
}

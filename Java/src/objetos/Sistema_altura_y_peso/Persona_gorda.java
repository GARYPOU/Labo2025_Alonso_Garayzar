package objetos.Sistema_altura_y_peso;

import personas.Persona;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Persona_gorda extends Persona {
    private HashMap<LocalDate,Medicion> medicion;

    public Persona_gorda(String nom, String apellido, int direccion, LocalDate nacimiento, HashMap<LocalDate, Medicion> medicion) {
        super(nom, apellido, direccion, nacimiento);
        this.medicion = medicion;
    }


    public Persona_gorda() {


















        super("Toilet", "Gary", LocalDate.now());
        this.medicion = new HashMap<>();
    }

    public HashMap<LocalDate, Medicion> getMedicion() {
        return medicion;
    }

    public void setMedicion(HashMap<LocalDate, Medicion> medicion) {
        this.medicion = medicion;
    }


    public void saber_pesoyaltura(LocalDate fecha1, Persona_gorda p1){
        for (Map.Entry<LocalDate,Medicion>map: medicion.entrySet()){
            if (map.getKey()==fecha1){
                System.out.println("Persona: "+p1+"y su peso y altura es: "+map.getValue());
            }
        }
    }

    public void Promedio(int año){
        int prompeso = 0;
        int promaltura = 0;
        int cantmedidas = 0;
        for(Map.Entry<LocalDate,Medicion>map: medicion.entrySet()){
            if (map.getKey().getYear()==año){
                prompeso+=map.getValue().getPeso();
                promaltura+=map.getValue().getAltura();
                cantmedidas++;
            }
        }
        prompeso=prompeso/cantmedidas;
        promaltura=promaltura/cantmedidas;
        System.out.println("el promedio de peso es: "+prompeso+"y la altura es: "+promaltura);
    }

    public double porcentaje(LocalDate fecha1, LocalDate fecha2){
        int alt1 = 0;
        int alt2 = 0;
        int dif = 0;
        for(Map.Entry<LocalDate,Medicion>map: medicion.entrySet()){
            if(fecha1.isBefore(fecha2)){
                if(map.getKey().isEqual(fecha1)){
                    alt1 = map.getValue().getAltura();
                }
                if(map.getKey().isEqual(fecha2)){
                    alt2 = map.getValue().getAltura();
                }

            }
            if(fecha2.isBefore(fecha1)){
                if(map.getKey().isEqual(fecha2)){
                    alt1 = map.getValue().getAltura();
                }
                if(map.getKey().isEqual(fecha1)){
                    alt2 = map.getValue().getAltura();
                }

            }

            dif = (alt2-alt1)/2;
        }
        return dif;
    }

    public static void main(String[] args) {
        LocalDate f1 = LocalDate.now();
        LocalDate f2 = LocalDate.of(1001,1,22);
        Persona_gorda p1 = new Persona_gorda();
        System.out.println("El promedio de las mediciones de las dos fechas fue:"+p1.porcentaje(f1,f2));
    }

}

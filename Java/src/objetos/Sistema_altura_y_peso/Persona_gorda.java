package objetos.Sistema_altura_y_peso;

import personas.Persona;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Persona_gorda extends Persona {
    private HashMap<LocalDate,Medicion> medicion;
    private int peso;
    private int altura;

    public Persona_gorda(String nom, String apellido, LocalDate nacimiento, HashMap<LocalDate, Medicion> medicion, int peso, int altura) {
        super(nom, apellido, nacimiento);
        this.medicion = medicion;
        this.peso = peso;
        this.altura = altura;
    }

    public HashMap<LocalDate, Medicion> getMedicion() {
        return medicion;
    }

    public void setMedicion(HashMap<LocalDate, Medicion> medicion) {
        this.medicion = medicion;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
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

}

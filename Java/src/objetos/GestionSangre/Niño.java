package objetos.GestionSangre;

import java.time.LocalDate;

public class Niño extends Pasciente implements Tratamiento{
    private Nivel tolerancia;
    public Niño(String nom, String apellido, int direccion, LocalDate nacimiento, String genero, Sangre sangre, boolean tipo, int edad) {
        super(nom, apellido, direccion, nacimiento, genero, sangre, tipo, edad);
    }

    @Override
    public boolean tratamiento() {
        if(getEdad()>=3){
            int total=tolerancia.getValores()*2;
            System.out.println("Tu tratamiento tardará esta cantidad de días:"+" "+total);
            return true;
        }
        else{
            System.out.println("Aún es chico para probar el tratamiento");
            return false;
        }
    }

    @Override
    public double coste() {
        return tolerancia.getValores()*450000;
    }
}

package objetos.GestionSangre;

import java.time.LocalDate;
import java.util.HashSet;

public class Joven extends Pasciente implements Tratamiento{
    private static int edadMinima=20;
    private static int edadMaxima=30;
    private HashSet<String>actividades;

    public Joven(String nom, String apellido, int direccion, LocalDate nacimiento, String genero, Sangre sangre, boolean tipo, int edad) {
        super(nom, apellido, direccion, nacimiento, genero, sangre, tipo, edad);
    }


    @Override
    public boolean tratamiento() {
            if (actividades.size()%2!=0 && edadMaxima-getEdad()<=2) {

                System.out.println("Lamentamos comunicarte que tu sangre aún no va a poder ser modificada");
                return false;
            }
            System.out.println("Será un tratamiento costoso, pero lo vamos a lograr");
        return true;
    }

    @Override
    public double coste() {
        if(getNacimiento().getYear()%2==0){
            return getNacimiento().getDayOfMonth()*145400;
        }
        return getNacimiento().getMonthValue()*760000;
    }
}


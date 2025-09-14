package objetos.GestionSangre;

import java.time.LocalDate;

public class Adulto extends Pasciente{
    public Adulto(String nom, String apellido, int direccion, LocalDate nacimiento, String genero, Sangre sangre, boolean tipo, int edad) {
        super(nom, apellido, direccion, nacimiento, genero, sangre, tipo, edad);
    }
}

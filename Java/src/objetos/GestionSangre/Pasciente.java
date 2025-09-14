package objetos.GestionSangre;

import personas.Persona;

import java.time.LocalDate;

public class Pasciente extends Persona {
    private String genero;
    private Sangre sangre;
    private boolean tipo;
    private int edad;


    public Pasciente(String nom, String apellido, int direccion, LocalDate nacimiento, String genero, Sangre sangre, boolean tipo, int edad) {
        super(nom, apellido, direccion, nacimiento);
        this.genero = genero;
        this.sangre = sangre;
        this.tipo = tipo;
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public Sangre getSangre() {
        return sangre;
    }

    public void setSangre(Sangre sangre) {
        this.sangre = sangre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}

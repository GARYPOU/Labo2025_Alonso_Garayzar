package objetos.BibliotecaVirtual;

import personas.Persona;

import java.time.LocalDate;
import java.util.HashSet;

public class Autor extends Persona {
    HashSet<LibrosElec>bibliografia;
    private int dni;


    public Autor(String nom, LocalDate nacimiento, HashSet<LibrosElec> bibliografia, int dni) {
        super(nom, nacimiento);
        this.bibliografia = bibliografia;
        this.dni = dni;
    }

    public HashSet<LibrosElec> getBibliografia() {
        return bibliografia;
    }

    public void setBibliografia(HashSet<LibrosElec> bibliografia) {
        this.bibliografia = bibliografia;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
}

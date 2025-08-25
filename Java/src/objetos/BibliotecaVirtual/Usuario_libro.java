package objetos.BibliotecaVirtual;

import personas.Persona;

import java.time.LocalDate;
import java.util.HashSet;

public class Usuario_libro extends Persona {
    private int dni;
    private String mail;
    private Tipo_membresia membresia;
    private HashSet<LibrosElec>Libros;

    public Usuario_libro(String nom, LocalDate nacimiento, int dni, String mail, Tipo_membresia membresia, HashSet<LibrosElec> libros) {
        super(nom, nacimiento);
        this.dni = dni;
        this.mail = mail;
        this.membresia = membresia;
        Libros = libros;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Tipo_membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Tipo_membresia membresia) {
        this.membresia = membresia;
    }

    public HashSet<LibrosElec> getLibros() {
        return Libros;
    }

    public void setLibros(HashSet<LibrosElec> libros) {
        Libros = libros;
    }


}

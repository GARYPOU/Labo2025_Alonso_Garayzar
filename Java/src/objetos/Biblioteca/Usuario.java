package objetos.biblioteca;

import personas.Persona;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

public class Usuario extends Persona {
    private int credito;
    private String mail;
    private HashSet<Publicacion>publicaciones;
    private HashMap<Publicacion,Integer> publicacionesPrestamo;
    private HashMap<Prestamo,Integer>prestamos;


    public Usuario(String nom, String apellido, int direccion, LocalDate nacimiento, int credito, String mail, HashSet<Publicacion> publicaciones, HashMap<Publicacion, Integer> publicacionesPrestamo, HashMap<Prestamo, Integer> prestamos) {
        super(nom, apellido, direccion, nacimiento);
        this.credito = credito;
        this.mail = mail;
        this.publicaciones = publicaciones;
        this.publicacionesPrestamo = publicacionesPrestamo;
        this.prestamos = prestamos;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public HashSet<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(HashSet<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public HashMap<Publicacion, Integer> getPublicacionesPrestamo() {
        return publicacionesPrestamo;
    }

    public void setPublicacionesPrestamo(HashMap<Publicacion, Integer> publicacionesPrestamo) {
        this.publicacionesPrestamo = publicacionesPrestamo;
    }

    public HashMap<Prestamo, Integer> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(HashMap<Prestamo, Integer> prestamos) {
        this.prestamos = prestamos;
    }
}

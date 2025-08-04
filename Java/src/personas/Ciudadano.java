package personas;

import objetos.Vacunatorio.Vacuna;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Ciudadano extends Persona{
    private int dni;
    private String provincia;
    private String domicilio;
    private String mail;
    HashSet<Vacuna>vacunas;

    public Ciudadano(String nom, String apellido, int dni, String provincia, String domicilio, String mail, HashSet<Vacuna> vacunas) {
        super(nom, apellido);
        this.dni = dni;
        this.provincia = provincia;
        this.domicilio = domicilio;
        this.mail = mail;
        this.vacunas = vacunas;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public HashSet<Vacuna> getVacunas() {
        return vacunas;
    }

    public void setVacunas(HashSet<Vacuna> vacunas) {
        this.vacunas = vacunas;
    }

    public void registar_vacuna(Vacuna v1){
        vacunas.add(v1);
    }


}

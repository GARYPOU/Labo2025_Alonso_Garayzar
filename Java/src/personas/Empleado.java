package personas;

import objetos.Llamada;

import java.time.LocalDate;
import java.util.ArrayList;

public class Empleado extends Persona{
    private int dni;
    private String pais;
    private int telefono;
    private ArrayList<Llamada>llamadas = new ArrayList<>();

    public Empleado(String nom, String apellido, int direccion, LocalDate nacimiento, int dni, String pais, int telefono) {
        super(nom, apellido, direccion, nacimiento);
        this.dni = dni;
        this.pais = pais;
        this.telefono = telefono;
    }

    public Empleado(){
        super("juan", "tadeo", 3627635, LocalDate.of(2000,3,3));
        this.dni = 782365868;
        this.pais = "venfica";
        this.telefono = 75625624;
    }
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public ArrayList<Llamada> getLlamadas() {
        return llamadas;
    }

    public void setLlamadas(ArrayList<Llamada> llamadas) {
        this.llamadas = llamadas;
    }
}

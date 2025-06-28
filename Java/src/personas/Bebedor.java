package personas;

import objetos.Bebidas.Bebida;

import java.time.LocalDate;
import java.util.ArrayList;

public class Bebedor extends Persona{
    private int dni;
    private static int co=0;
    private int coeficienteHidratacion;
    private ArrayList<Bebida>bebidas=new ArrayList<>();

    public Bebedor(String nom, String apellido, int direccion, LocalDate nacimiento, int dni, ArrayList<Bebida> bebidas, int coeficienteHidratacion) {
        super(nom, apellido, direccion, nacimiento);
        this.dni = dni;
        this.bebidas = bebidas;
        this.coeficienteHidratacion=coeficienteHidratacion;
    }
    public Bebedor() {
        super("juan", "perez",199191,LocalDate.now());
        this.dni = 1001010;
        this.bebidas = new ArrayList<>();
        this.coeficienteHidratacion=co;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getCoeficienteHidratacion() {
        return coeficienteHidratacion;
    }

    public void setCoeficienteHidratacion(int coeficienteHidratacion) {
        this.coeficienteHidratacion = coeficienteHidratacion;
    }

    public ArrayList<Bebida> getBebidas() {
        return bebidas;
    }

    public void setBebidas(ArrayList<Bebida> bebidas) {
        this.bebidas = bebidas;
    }
}

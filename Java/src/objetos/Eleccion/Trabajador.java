package objetos.Eleccion;

import com.sun.source.tree.BreakTree;
import personas.Persona;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Trabajador extends Persona implements AccionesCompañias {
    private int dni;
    private int cuil;
    private double sueldo;
    private String dir;
    private int HorarioLaboralInicio;
    private int HorarioLaboralFin;

    public Trabajador(String nom, String apellido, LocalDate nacimiento, int dni, int cuil, double sueldo, String dir) {
        super(nom, apellido, nacimiento);
        this.dni = dni;
        this.cuil = cuil;
        this.sueldo = sueldo;
        this.dir = dir;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getCuil() {
        return cuil;
    }

    public void setCuil(int cuil) {
        this.cuil = cuil;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    @Override
    public String mensaje() {
        if (HorarioLaboralInicio > LocalDateTime.now().getHour() && LocalDateTime.now().getHour() < HorarioLaboralFin) {
            return "Yo, nombreTrabajador te invito a que: Vote por el partido para un mejor futuro";
        }
        return null;
    }
}

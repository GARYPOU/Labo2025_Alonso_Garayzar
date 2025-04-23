package personas;
import Fechas.Fecha;
import objetos.Nota;

import java.util.ArrayList;

public class Alumno {
    private String nombre;
    private String apellido;
    private Fecha fechanacimiento;
    private ArrayList<Nota> notas = new ArrayList<Nota>();

    public Alumno(){
        this.nombre="Pepe";
        this.apellido="Alonso";
        this.fechanacimiento.setAnio(2024);
        this.fechanacimiento.setMes(12);
        this.fechanacimiento.setDia(31);
        this.notas.add(new Nota("Gary",2,"Historia"));

    }
    public Alumno(String nombre, String apellido, Fecha fechanacimiento, ArrayList<Nota> notas){
        this.nombre=nombre;
        this.apellido=apellido;
        this.fechanacimiento=fechanacimiento;
        this.notas=notas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Fecha getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Fecha fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Nota> notas) {
        this.notas = notas;
    }
}


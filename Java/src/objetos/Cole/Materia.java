package objetos;

import personas.Alumno;

import java.time.LocalDate;
import java.util.ArrayList;

public class Materia {
    String nombre;
    ArrayList<Alumno>alumnos;
    ArrayList<String>contenidos;

    public Materia(){
        this.nombre="Gustavo";
        this.alumnos=new ArrayList<>();
        this.contenidos=new ArrayList<>();
    }
    public Materia(String nombre){
        this.alumnos=new ArrayList<>();
        this.nombre=nombre;
        this.contenidos=contenidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public ArrayList<String> getContenidos() {
        return contenidos;
    }

    public void setContenidos(ArrayList<String> contenidos) {
        this.contenidos = contenidos;
    }
    public void agregar(Alumno alumno){
        this.alumnos.add(alumno);
    }
    public int promEdades(){
        int promedad=0;
        int cant=0;
        int edades=0;
        for(int i=0; i<alumnos.size();i++){
            edades=alumnos.get(i).getEdad();
            cant=i;
        }
        promedad=edades/cant;
        return promedad;

    }

    public static void main(String[] args) {
        Materia m1 = new Materia();
        System.out.println(m1.promEdades());
    }


}

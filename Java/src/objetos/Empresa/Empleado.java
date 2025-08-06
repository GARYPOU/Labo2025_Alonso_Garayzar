package objetos.Empresa;

import personas.Persona;

import java.util.HashSet;

public class Empleado extends Persona {
    HashSet<Proyecto> proyectos;
    Comision comi;

    public Empleado(String nombre, HashSet<Proyecto> proyectos, Comision comi) {
        super(nombre);
        this.proyectos = proyectos;
        this.comi = comi;
    }

    public double sueldo(){
    double suel=0;
    for(Proyecto p: proyectos){
        suel=p.getGanancia()*comi.getValor();
    }
    return suel;
}

    public int cantProyectos(){
        return proyectos.size();
    }
    public void datosProyecto(){
    for (Proyecto p: proyectos){
        System.out.println("El proyecto se llama"+p.getNombre()+"el empleado gano:"+sueldo()+"y su comision fue"+comi.getValor());
    }
    }
}

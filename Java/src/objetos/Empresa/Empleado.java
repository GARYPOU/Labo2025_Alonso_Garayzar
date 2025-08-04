package objetos.Empresa;

import java.util.HashSet;

public class Empleado {
    HashSet<Proyecto> proyectos;
    Comision comi;


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
}

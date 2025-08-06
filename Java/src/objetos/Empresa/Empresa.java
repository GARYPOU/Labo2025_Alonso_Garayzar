package objetos.Empresa;

import personas.Ciudadano;

import java.util.HashMap;
import java.util.HashSet;

public class Empresa {
    HashSet<Empleado> diseñadores;

    public int proyectoPrecio(Proyecto p1){
        return p1.getGanancia();
    }
}

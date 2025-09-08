package objetos.Eleccion;

import java.util.HashMap;
import java.util.HashSet;

public class Partido_politico {
    private String nombre;
    private String dir;
    private int cant_afiliados;
    private HashSet<AccionesCompañias> campañas;

    public Partido_politico(String nombre, String dir, int cant_afiliados, HashSet<AccionesCompañias> campañas) {
        this.nombre = nombre;
        this.dir = dir;
        this.cant_afiliados = cant_afiliados;
        this.campañas = campañas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public int getCant_afiliados() {
        return cant_afiliados;
    }

    public void setCant_afiliados(int cant_afiliados) {
        this.cant_afiliados = cant_afiliados;
    }

    public HashSet<AccionesCompañias> getCampañas() {
        return campañas;
    }

    public void setCampañas(HashSet<AccionesCompañias> campañas) {
        this.campañas = campañas;
    }

    public void agregar(AccionesCompañias c){
        if (campañas.contains(c)){
            System.out.println("ya tiene la compañia");
        }
        else {
            campañas.add(c);
        }
    }

    public String hacerCampaña(){
        for (AccionesCompañias c:campañas){
            return  c.mensaje();

        }
        return null;
    }
}

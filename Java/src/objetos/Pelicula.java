package objetos;

import personas.Persona;

import java.util.ArrayList;

public class Pelicula {
    private String nombre;
    private String genero;
    private double duracion;
    ArrayList<Persona>directores;
    ArrayList<Persona>actores;
    ArrayList<String>idiomas;

    public Pelicula(){
        this.nombre="tarzan";
        this.duracion=94.8;
        this.genero="accion";
        this.directores= new ArrayList<>();
        this.actores= new ArrayList<>();
        this.idiomas= new ArrayList<>();

    }
    public Pelicula(String nombre, String genero, double duracion, ArrayList<Persona>directores, ArrayList<Persona>actores, ArrayList<String>idiomas){
        this.nombre=nombre;
        this.duracion=duracion;
        this.genero=genero;
        this.directores= directores;
        this.actores= actores;
        this.idiomas= idiomas;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public ArrayList<Persona> getDirectores() {
        return directores;
    }

    public void setDirectores(ArrayList<Persona> directores) {
        this.directores = directores;
    }

    public ArrayList<Persona> getActores() {
        return actores;
    }

    public void setActores(ArrayList<Persona> actores) {
        this.actores = actores;
    }

    public ArrayList<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(ArrayList<String> idiomas) {
        this.idiomas = idiomas;
    }

    public void borrar(Persona nomDir, Persona nomAct, String idioma){
        directores.remove(nomDir);
        actores.remove(nomAct);
        idiomas.remove(idioma);
    }
    public void agregar(Persona nomDir, Persona nomAct, String idioma){
        directores.add(nomDir);
        actores.add(nomAct);
        idiomas.add(idioma);
    }
    public void modificar(Persona nomDir, int posDir, Persona nomAct, int posAct, String idioma, int posIdi){
        directores.set(posDir,nomDir);
        actores.set(posAct, nomAct);
        idiomas.set(posIdi, idioma);
    }
    public ArrayList<Persona> mayorEdad(){
        ArrayList<Persona>actoresMayores;
        actoresMayores=new ArrayList<>();
        for (Persona p: actores){
            if(p.getNacimiento().getYear()>18){
                actoresMayores.add(p);
            }
        }
        return actoresMayores;
    }

    public static void main(String[] args) {
        Pelicula p1 = new Pelicula();
        Persona per1 = new Persona();
        p1.agregar(per1,per1,"ingles");
        p1.borrar(per1,per1,"ingles");
        p1.modificar(per1,1,per1,1,"ingles",1);
       for(Persona p: p1.mayorEdad()){
           System.out.println("Actores:"+p);
       }
    }
}

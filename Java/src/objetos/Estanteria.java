package objetos;

import personas.Persona;

import java.util.ArrayList;

public class Estanteria {
    ArrayList<Pelicula>peliculas;
    int identificador;


    public Estanteria(){
        this.peliculas=new ArrayList<>();
        this.identificador=1;
    }

    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    public void borrar(Pelicula peli){
        peliculas.remove(peli);

    }
    public void agregar(Pelicula peli){
        peliculas.add(peli);

    }
    public void modificar(Pelicula peli, int posPeli){

        peliculas.set(posPeli,peli);

    }
    public ArrayList<Persona> direcRepetidos(){
        ArrayList<Persona>dir;
        ArrayList<Persona>dirRepetidos;
        dir=new ArrayList<>();
        dirRepetidos=new ArrayList<>();
        for(Pelicula p: peliculas){
            for (Persona di: p.directores) {
                dir.add(di);
            }
        }
        for(Persona direc : dir){
            for(int i=1; i<dir.size();i++){
                if(direc.getNombre()==dir.get(i).getNombre()){
                    dirRepetidos.add(direc);
                }
            }
        }
        return dirRepetidos;
    }
    public void pelisCortas (){
        for (Pelicula peli: peliculas){
            if(peli.getDuracion()<90){
                System.out.println(peli.getNombre());
            }
        }
    }

    public static void main(String[] args) {
        Estanteria e1 = new Estanteria();
        Pelicula p1 = new Pelicula();
        e1.agregar(p1);
        e1.borrar(p1);
        e1.modificar(p1,1);
        e1.direcRepetidos();
        e1.pelisCortas();
    }
}

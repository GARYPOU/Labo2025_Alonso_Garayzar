package objetos;

import personas.Persona;

import java.util.ArrayList;

public class SistemaVideoclub {
    private ArrayList<Videoclub>videoclubs;


    public SistemaVideoclub(){
        this.videoclubs=new ArrayList<>();
    }

    public ArrayList<Videoclub> getVideoclubs() {
        return videoclubs;
    }

    public void setVideoclubs(ArrayList<Videoclub> videoclubs) {
        this.videoclubs = videoclubs;
    }
    public void borrar(Videoclub vide){
        videoclubs.remove(vide);

    }
    public void agregar(Videoclub vide){
        videoclubs.add(vide);

    }
    public void modificar(Videoclub vide, int posVide){

        videoclubs.set(posVide,vide);

    }
    public ArrayList<String>dirPorComuna(String Comuna){
        ArrayList<String>vides;
        vides=new ArrayList<>();
        for (Videoclub vide: videoclubs){
            if(Comuna==vide.getComuna()){
                vides.add(vide.getDireccion());
            }
        }
        return vides;
    }

    public Videoclub maspelis(){
        Videoclub video = new Videoclub();
        ArrayList<Pelicula>pels;
        pels = new ArrayList<>();
        for (Videoclub vide: videoclubs){
            for(Estanteria est: vide.getEstanterias()){
                if(est.peliculas.size()>pels.size()){
                    video=vide;
                }


            }
        }
        return video;
    }
    public ArrayList<Pelicula> generos (String genere){
        Videoclub video = new Videoclub();
        ArrayList<Pelicula>pels;
        pels = new ArrayList<>();
        for (Videoclub vide: videoclubs){
            for(Estanteria est: vide.getEstanterias()){
                for (Pelicula p: est.getPeliculas()){
                    if(p.getGenero()==genere){
                        pels.add(p);
                    }
                }


            }
        }
        return pels;
    }
    public static void main(String[] args) {
        SistemaVideoclub Sist1 = new SistemaVideoclub();
        Videoclub v1 = new Videoclub();
        Sist1.agregar(v1);
        Sist1.borrar(v1);
        Sist1.modificar(v1,1);
        Sist1.dirPorComuna("Liniers");
        System.out.println(Sist1.maspelis().getNombre());
        for (Pelicula p: Sist1.generos("accion")){
            System.out.println(p);
        }
    }
}

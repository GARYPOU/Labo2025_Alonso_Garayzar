package objetos.Cocina;

import java.util.ArrayList;

public class Plataforma {
    private ArrayList<Receta>recetas=new ArrayList<>();


    public Plataforma(ArrayList<Receta> recetas) {
        this.recetas = recetas;

    }
    public Plataforma() {
        this.recetas = new ArrayList<>();

    }

    public ArrayList<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(ArrayList<Receta> recetas) {
        this.recetas = recetas;
    }
    public void agregarReceta(Receta receta){
        recetas.add(receta);

    }
    public void modificarReceta(Receta receta, int pos){
        recetas.set(pos,receta);

    }
    public void borrarReceta(Receta receta){
        recetas.remove(receta);

    }
    public void buscarReceta(Dificultad dificultad){
        for (Receta r: recetas){
            if(r.getNivel().compareTo(dificultad)==0){
                System.out.println("El nombre es:"+r.getNombre()+" "+"y su dificultad es:"+r.getNivel());
            }
        }
    }

    public void filtrarRecetas(){
        ArrayList<Receta>platosPiolas=new ArrayList<>();
        ArrayList<Receta>entradas=new ArrayList<>();
        ArrayList<Receta>postres=new ArrayList<>();

        for (Receta r: recetas){
            if(recetas.getClass().equals("PlatoPrincipal")){
                platosPiolas.add(r);
            }
            else if(recetas.getClass().equals("Entrada")){
                entradas.add(r);
            }
            else{
                postres.add(r);
            }
        }
        for(Receta r: platosPiolas){
            System.out.println("Los platos principales son:"+r.getNombre());
        }
        for(Receta r: entradas){
            System.out.println("Las entradas son:"+r.getNombre());
        }
        for(Receta r: postres){
            System.out.println("Los postres son:"+r.getNombre());
        }
    }
    public int cantRecetas(){
        int cont=0;
        for (Receta r: recetas){
            cont=cont+1;
        }
        return cont;
    }


    public Receta recetaMasPasos(){

        int cont=0;
        int max=0;
        int indice=0;
        for (int j=0; j<recetas.size();j++){
            for(int i=0; i<recetas.get(j).getPasos().size(); i++){
                cont=cont+1;
            }
            if(max<cont){
                indice=j;
            }
        }
        return recetas.get(indice);
    }

    public static void main(String[] args) {
        Plataforma p1 = new Plataforma();
        Postre pos1 = new Postre();
        PlatoPrincipal pla1 = new PlatoPrincipal();
        Entrada ent1 = new Entrada();
        p1.agregarReceta(pos1);
        p1.agregarReceta(pla1);
        p1.agregarReceta(ent1);
        p1.borrarReceta(pos1);
        p1.modificarReceta(pos1,1);
        p1.agregarReceta(pla1);
        p1.buscarReceta(Dificultad.MEDIO);
        p1.filtrarRecetas();
        System.out.println("hay"+p1.cantRecetas()+"recetas");
        System.out.println("La receta con mas pasos es:"+p1.recetaMasPasos().getNombre());
    }
}

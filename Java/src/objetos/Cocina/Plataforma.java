package objetos.Cocina;

import java.util.ArrayList;

public class Plataforma {
    private ArrayList<Receta>recetas=new ArrayList<>();


    public Plataforma(ArrayList<Receta> recetas) {
        this.recetas = recetas;

    }

    public ArrayList<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(ArrayList<Receta> recetas) {
        this.recetas = recetas;
    }
}

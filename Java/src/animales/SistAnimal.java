package animales;

import java.util.ArrayList;

public class SistAnimal {
    ArrayList<Mascota>mascotas = new ArrayList<>();


    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public void mostrarDatos(){
        for (Mascota  m :mascotas) {
            System.out.println("el animal es:"+" "+m.getClass()+" "+"su nombre es"+" "+m.getNombre()+" "+"su dueño es"+" "+m.getDueño());
        }
    }
    public void borrarMascota(Mascota m){
        mascotas.remove(m);
    }
    public void agregarMascota(Mascota m){
        mascotas.add(m);
    }
    public void agregarMascota(Mascota m, int pos){
        mascotas.set(pos, m);
    }

}

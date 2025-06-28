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
    public void modificarMascota(Mascota m, int pos){
        mascotas.set(pos, m);
    }

    public void borrarPez(){
        for(Mascota m: mascotas){
            if(m instanceof Pez){
                if(((Pez) m).chequear()){
                    System.out.println("se murio");
                    mascotas.remove(m);
                }
            }
        }
    }

    public static void main(String[] args) {
        Pez pescado = new Pez();
        Gato gatin = new Gato();
        Perro perrin = new Perro();
        Pajaro pajarin = new Pajaro();
        SistAnimal s = new SistAnimal();
        gatin.chequearAlegria();
        perrin.chequearAlegria();
        pajarin.chequearAlegria();
        s.mostrarDatos();
        s.agregarMascota(pescado);
        s.agregarMascota(perrin);
        s.agregarMascota(gatin);
        s.borrarMascota(gatin);
        s.agregarMascota(gatin);
        s.agregarMascota(pajarin);
        s.modificarMascota(perrin,0);
        s.modificarMascota(pescado,1);
        System.out.println(perrin.saludar("Teo","Gary"));
        System.out.println(perrin.saludar("Teor","Gary"));
        pajarin.cantor("cocoroco");
        System.out.println(pajarin.saludar("Jaime","Juan"));
        pescado.saludar("Pepe","Juan");
        pescado.alimentar();
        System.out.println(pescado.getVidas());
        pescado.saludar("Pepe","J");

    }
}
package objetos.Musica;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import personas.Persona;
import objetos.Musica.Cancion;

public class Cd {
    private ArrayList<Cancion> canciones = new ArrayList<Cancion>();;

    public Cd() {

        this.canciones.add (new Cancion("La quinta sinfonia", new Persona()));

    }


    public Cd(ArrayList<Cancion> canciones) {

        canciones = canciones;
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
    }

    public void cantCanciones(int cant){
        for (int i = 0; i < this.canciones.size(); i++) {
            cant=i+1;
        }
        System.out.println(cant);
    }
    public void mostrarCancion(){

        System.out.println("ingrese un numero de la cancion");
        Scanner e = new Scanner(System.in);
        int num = e.nextInt();
        num=num-1;
        System.out.println(canciones.get(num).gettitulo()+" "+canciones.get(num).getautor().getNombre()+" "+canciones.get(num).getautor().getNacimiento().getYear()+" "+canciones.get(num).getautor().getDireccion());

    }
    public void grabarCancion(Cancion c2){

        Scanner t = new Scanner(System.in);
        System.out.println("ingrese un numero de cancion a reemplazar");
        int num2 = t.nextInt();
        num2=num2-1;
        canciones.set(num2, c2);
    }
    public void agregarCancion(Cancion c3){
        canciones.add(c3);


    }
    public void eliminar(){

        Scanner t = new Scanner(System.in);
        System.out.println("ingrese un numero de cancion a borrar");
        int num3 = t.nextInt();
        num3=num3-1;
        canciones.remove(num3);
    }


    public static void main(String[] args) {
        int cant=0;
        Cd c1 = new Cd();
        Cancion c2 = new Cancion();
        Cancion c3 = new Cancion();
        c1.canciones.add(new Cancion("Shake it off", new Persona()));
        c1.canciones.add(new Cancion("Enemy", new Persona()));
        c2=new Cancion("Fanatico", new Persona());
        c3=new Cancion("Frozen", new Persona());
        c1.cantCanciones(cant);
        c1.mostrarCancion();
        c1.grabarCancion(c2);
        c1.mostrarCancion();
        c1.agregarCancion(c3);
        c1.mostrarCancion();
        c1.eliminar();
        c1.mostrarCancion();



    }
}
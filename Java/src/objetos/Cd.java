package objetos;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import personas.Persona;
import objetos.Cancion;

public class Cd {
    private Cancion cancion;

    public Cd() {

        this.cancion = new Cancion("", new Persona("", 0, ""));

    }


    public Cd(Cancion cancion) {
        cancion = cancion;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }
    public void cantCanciones(int cant, ArrayList<Cancion>canciones){
        for (int i = 0; i < canciones.size(); i++) {
            cant=i+1;
        }
        System.out.println(cant);
    }
    public void mostrarCancion(ArrayList<Cancion>canciones){

        System.out.println("ingrese un numero de la cancion");
        Scanner e = new Scanner(System.in);
        int num = e.nextInt();
        num-=num;
        System.out.println(canciones.get(num).gettitulo()+" "+canciones.get(num).getautor().getNombre()+" "+canciones.get(num).getautor().getEdad()+" "+canciones.get(num).getautor().getDireccion());

    }
    public void grabarCancion(ArrayList<Cancion>canciones, Cancion c2){

        Scanner t = new Scanner(System.in);
        System.out.println("ingrese un numero de cancion a reemplazar");
        int num2 = t.nextInt();
        num2-=num2;

        canciones.set(num2, c2);
    }


    public static void main(String[] args) {
        int cant=0;
        ArrayList<Cancion> canciones = new ArrayList<Cancion>();
        canciones.add(new Cancion("Shake it off", new Persona("Taylor Swift", 35, "Chicago")));
        canciones.add(new Cancion("Enemy", new Persona("Imagine Dragons", 43, "Venezuela")));
        Cd c1 = new Cd();
        Cancion c2 = new Cancion("Fanatico", new Persona("Lali", 23, "Buenos Aires"));
        c1.cantCanciones(cant,canciones);
        c1.mostrarCancion(canciones);
        c1.grabarCancion(canciones, c2);
        c1.mostrarCancion(canciones);


    }
}
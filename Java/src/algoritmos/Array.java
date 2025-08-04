package algoritmos;
import personas.Customer;
import personas.Persona;
import java.util.ArrayList;
import java.util.Scanner;

public class Array {

    public static void main(String[] args) {
        Scanner e = new Scanner(System.in);
        int total=0;
        String pal;
        char compara;


        ArrayList <Integer> numeros = new ArrayList <Integer>();
        ArrayList <String> palabras = new ArrayList <String>();
        ArrayList <Persona> personas = new ArrayList <Persona>();
        numeros.add(9);
        numeros.add(11);
        numeros.add(99);
        palabras.add("Feli");
        palabras.add("Jorge");
        palabras.add("Feñi");
        personas.add(new Customer());
        for(int i=0; i<numeros.size(); i++){
            total = total + numeros.get(i);
    }
        System.out.println("Ingrese una letra a comparar");
        compara = e.nextLine().charAt(0);
        System.out.println(total);
        for(int i=0; i<palabras.size(); i++){
            pal=palabras.get(i).toLowerCase();
            if(compara==pal.charAt(0)) {
                System.out.println(pal);
            }
        }
        for(Persona persona : personas){
            if(persona.getNacimiento().getYear()>30);
            System.out.println("nombre" + persona.getNombre() + "edad" + persona.getNacimiento().getYear() + "dir" + persona.getDireccion());
        }



    }
}

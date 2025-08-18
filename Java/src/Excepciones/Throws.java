package Excepciones;

import java.util.Scanner;

public class Throws {
    public static void Nombre(String nombre)throws Exception{
        System.out.println("El largo del nombre es:" + nombre.length());
    }
    public static void main(String[] args) {

        try {
            String nombre = null;
            Nombre(nombre);
        }
        catch (Exception e){
            System.err.println("el nombre es null");
        }
    }
}

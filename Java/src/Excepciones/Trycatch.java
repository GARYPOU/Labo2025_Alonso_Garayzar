package Excepciones;

public class Trycatch {

    public static void main(String[] args) {
            String nombre = null;
            try {
                System.out.println("El largo del nombre es:" + nombre.length());
            }
            catch (Exception e){
                System.err.println("el nombre es null");
            }
    }
}

package Excepciones;

public class Throw {

    public static void main(String[] args) throws Exception {
        String nombre = null;
            if (nombre==null){
                throw new Exception("el nombre es null");
            }
            else System.out.println("El largo del nombre es:" + nombre.length());

    }

}

package Excepciones;

public class Nombrenull extends Exception{

    public Nombrenull(String message) {
        super(message);
    }

    public static void main(String[] args) throws Exception {
        String nombre = null;
        if (nombre==null){
            throw new Nombrenull("el nombre es null xd");
        }
        else System.out.println("El largo del nombre es:" + nombre.length());

    }
}

package animales;

public class Pez extends Mascota {
    private static int vidasTotales = 10;
    private int vidas;


    public Pez(String nombre, String dueño, int alegria) {
        super(nombre, dueño, alegria);
        this.vidas = vidasTotales;
    }
    public Pez() {
        super("Pepe","Juan",0);
        this.vidas = vidasTotales;
    }





    public static int getVidasTotales() {
        return vidasTotales;
    }

    public static void setVidasTotales(int vidasTotales) {
        Pez.vidasTotales = vidasTotales;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    @Override
    String saludar(String nombre, String dueño) {
        if (nombre.equals(getNombre()) && dueño.equals(getDueño())) {
            vidas = vidas - 1;
            return "perdio una vida";


        } else {
            vidas = 0;
            return "se quedo sin vidas";

        }
    }

    @Override
    void alimentar() {
        int vidin=getVidas()+1;
        setVidas(vidin);
    }

    @Override
    String obtenerClase() {
        return "Pez";
    }

    public boolean chequear(){
        if(vidas<=0){

            return true;
        }
        else{

            return false;
        }
    }

    public static void main(String[] args) {
        Pez p1 = new Pez("juan","juan",1);


    }


}

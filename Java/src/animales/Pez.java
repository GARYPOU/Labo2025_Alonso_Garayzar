package animales;

public class Pez extends Mascota{
    private static int vidas = 10;


    public Pez(String nombre, String dueño) {
        super(nombre, dueño);
    }



    public static int getVidas() {
        return vidas;
    }

    public static void setVidas(int vidas) {
        Pez.vidas = vidas;
    }
    @Override
    Saludo saludar() {
        return null;
    }
}

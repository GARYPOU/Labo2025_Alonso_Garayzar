package objetos.Eleccion;

public class Paloma implements AccionesCompañias{
    private String tipo;
    private String color;
    private String nombre;
    private static String especie = "cactus";
    private boolean mapa;

    public Paloma(String tipo, String color, String nombre, boolean mapa) {
        this.tipo = tipo;
        this.color = color;
        this.nombre = nombre;
        this.mapa = mapa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static String getEspecie() {
        return especie;
    }

    public static void setEspecie(String especie) {
        Paloma.especie = especie;
    }

    public boolean isMapa() {
        return mapa;
    }

    public void setMapa(boolean mapa) {
        this.mapa = mapa;
    }


    @Override
    public String mensaje() {
        if(mapa) {
            return "Lanzando un papelito que dice: Vote por el partido para un mejor futuro";
        }
        return null;
    }
}

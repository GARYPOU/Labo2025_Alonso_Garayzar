package animales;

public abstract class Mascota extends Animal{
    private String nombre;
    private String dueño;
    private Saludo saludo;
    private static int alegria = 1;

    public Mascota(String nombre, String dueño, Saludo saludo, int alegria) {
        this.nombre = nombre;
        this.dueño = dueño;
        this.saludo = saludo;
        this.alegria = alegria;
    }

    public Mascota(String nombre, String dueño) {
        this.nombre = nombre;
        this.dueño = dueño;
    }


    public static int getAlegria() {
        return alegria;
    }

    public static void setAlegria(int alegria) {
        Mascota.alegria = alegria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDueño() {
        return dueño;
    }

    public void setDueño(String dueño) {
        this.dueño = dueño;
    }

    public Saludo getSaludo() {
        return saludo;
    }

    public void setSaludo(Saludo saludo) {
        this.saludo = saludo;
    }


    abstract Saludo saludar();
}

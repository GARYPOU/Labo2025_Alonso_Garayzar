package animales;

public abstract class Mascota extends Animal{
    private String nombre;
    private String dueño;
    private Saludo saludo;
    private static int alegriaInicial = 1;
    private int alegria=alegriaInicial;


    public Mascota(String nombre, String dueño, Saludo saludo, int alegria) {
        this.nombre = nombre;
        this.dueño = dueño;
        this.saludo = saludo;
        this.alegria = alegria;
    }
    public Mascota(String nombre, String dueño, int alegria) {
        this.nombre = nombre;
        this.dueño = dueño;
        this.alegria = alegria;
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

    public static int getAlegriaInicial() {
        return alegriaInicial;
    }

    public static void setAlegriaInicial(int alegriaInicial) {
        Mascota.alegriaInicial = alegriaInicial;
    }

    public int getAlegria() {
        return alegria;
    }

    public void setAlegria(int alegria) {
        this.alegria = alegria;
    }

    abstract String saludar(String nombre, String dueño);
    abstract void alimentar();
    abstract String obtenerClase();
}

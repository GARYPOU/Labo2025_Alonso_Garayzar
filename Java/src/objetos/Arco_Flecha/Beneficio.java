package objetos.Arco_Flecha;

public abstract class Beneficio {
    private String nombre;
    private Usuario user;

    public Beneficio(String nombre, Usuario user) {
        this.nombre = nombre;
        this.user = user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}

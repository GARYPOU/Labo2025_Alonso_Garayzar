package objetos.BibliotecaVirtual;

public class LibrosElec {
    private  String titulo;
    private Autor autor;
    private Genero genero;
    private String archivo;
    private static int cant_descargas_todos = 145;
    private int cant_descargas;

    public LibrosElec(String titulo, Autor autor, Genero genero, String archivo, int cant_descargas) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.archivo = archivo;
        this.cant_descargas = cant_descargas_todos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public static int getCant_descargas_todos() {
        return cant_descargas_todos;
    }

    public static void setCant_descargas_todos(int cant_descargas_todos) {
        LibrosElec.cant_descargas_todos = cant_descargas_todos;
    }

    public int getCant_descargas() {
        return cant_descargas;
    }

    public void setCant_descargas(int cant_descargas) {
        this.cant_descargas = cant_descargas;
    }
}

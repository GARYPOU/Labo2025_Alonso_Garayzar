package objetos;

import fechas.Fecha;
import personas.Persona;

import java.util.Scanner;

public class Libro {
    private String titulo;
    private int isbn;
    private int paginas;
    private String editorial;
    private Persona autor;
    private Fecha publicacion;


    public Libro() {
        this.titulo = "el ñobit";
        this.isbn = 12;
        this.paginas = 200;
        this.editorial = "La Paz";
        this.autor = new Persona("Jorgito", 40 ,"Juan v justo 9100");
        this.publicacion = new Fecha(1,2,2000);

    }


    public Libro(Persona autor, String titulo, int isbn, int paginas, String editorial, Fecha publicacion) {
        this.autor = autor;
        this.publicacion = publicacion;
        this.titulo = titulo;
        this.isbn = isbn;
        this.paginas= paginas;
        this.editorial = editorial;


    }
    public Libro( int isbn, String editorial) {
        this.titulo = "El hambre";
        this.isbn = isbn;
        this.editorial = editorial;


    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Persona getAutor() {
        return autor;
    }

    public void setAutor(Persona autor) {
        this.autor = autor;
    }

    public Fecha getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Fecha publicacion) {
        this.publicacion = publicacion;
    }

    public void mostrar() {
        System.out.println("titulo:" + titulo + "isbn:" + isbn + "paginas:" + paginas + "autor:" + autor.getNombre() + "fecha:" + publicacion.getDia() +"/"+publicacion.getMes()+"/"+publicacion.getAnio());
    }

    public void comparar(Fecha f2){
       if(publicacion.getAnio()>f2.getAnio()) {
           System.out.println("La primera Fecha es anterior");
       }
        else if(publicacion.getAnio()>f2.getAnio() && publicacion.getMes()>f2.getMes()){
            System.out.println("La primera Fecha es anterior");
        }
       else if(publicacion.getAnio()>f2.getAnio() && publicacion.getMes()>f2.getMes() && publicacion.getDia()>f2.getDia()){
           System.out.println("La primera Fecha es anterior");
       }
       else{

               System.out.println("La primera Fecha es posterior");

       }
    }
    public static void main(String[] args) {
        Scanner e = new Scanner(System.in);
        Libro l1= new Libro();
        Fecha f2 = new Fecha(1,2,2007);
        l1.mostrar();
        l1.comparar(f2);








    }
}

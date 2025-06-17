
package objetos.Musica;


import personas.Customer;
import personas.Persona;

public class Cancion {
    private String titulo;
    private Persona autor;
  


    public Cancion() {
      this.titulo="";
      this.autor=new Customer();

    }

    
    public Cancion(String titulo, Persona autor) {
        this.titulo=titulo;
        this.autor=autor;


    }
   


    

    public String gettitulo() {
        return titulo;
    }

    public void setcantMaxima(String titulo) {
        this.titulo=titulo;
    }

    public Persona getautor() {
        return autor;
    }

    public void setcantActual(Persona autor) {
        this.autor = autor;
    }

   


    

  
    
    public static void main(String[] args) {
        Cancion tema = new Cancion();
    }
}
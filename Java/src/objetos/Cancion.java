



public class Cancion {
    private String titulo;
    private String autor;
  


    public Cafetera() {
      this.titulo="";
      this.autor="";

    }

    
    public Cafetera(String titulo, String autor) {
        this.titulo=titulo;
        this.autor=autor;


    }
   


    

    public String gettitulo() {
        return titulo;
    }

    public void setcantMaxima(String titulo) {
        this.titulo=titulo;
    }

    public int getautor() {
        return autor;
    }

    public void setcantActual(String autor) {
        this.autor = autor;
    }

   


    

  
    
    public static void main(String[] args) {
        Cancion tema = new Cancion();
    }
}
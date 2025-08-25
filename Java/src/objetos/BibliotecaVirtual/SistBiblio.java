package objetos.BibliotecaVirtual;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class SistBiblio {
    HashSet<LibrosElec>libros;




    public boolean chequear(LibrosElec L, Usuario_libro u){
        boolean verda=false;
        try {
            if (L.getCant_descargas() <= 0) {
                throw new LimiteDePrestamosAlcanzadoException("Se llego al limite de prestamos del libro");

            }
            if (u.getMembresia().getTipo_membresia() < u.getLibros().size()){
                throw new MembresiaException("Se llego al limite de prestamos del usuario");
            }
            verda=true;

        }catch (Exception error){
            System.err.println(error.getMessage());
        }




        return verda;
    }
    public void Prestamo(Usuario_libro u, LibrosElec l){

        if(chequear(l, u)){
            u.getLibros().add(l);
            l.setCant_descargas(l.getCant_descargas()-1);
        }
    }
    public void Devolucion(Usuario_libro u, LibrosElec l){

        if(chequear(l, u)){
            u.getLibros().remove(l);
            l.setCant_descargas(l.getCant_descargas()+1);
        }
    }

    public void agregarLibro(LibrosElec l){
        libros.add(l);
    }
    public  void borrarLibro(LibrosElec l){
        libros.remove(l);
    }
    public  void borrarLibro(LibrosElec l, LibrosElec l2){
        libros.remove(l);
        libros.add(l2);
    }

    public static void main(String[] args) {
        SistBiblio s1 = new SistBiblio();
        int i=0;
        for (LibrosElec l: s1.libros){
            System.out.println(i+")"+l.getTitulo());
            i=i+1;
        }
        i=0;
        Autor a1 = new Autor("Tilin", LocalDate.now(),new HashSet<>(),919191919);
        LibrosElec l1 = new LibrosElec("Tintin",a1,Genero.AVENTURA,"PDFTintin",100);
        Usuario_libro u1 = new Usuario_libro("Juan",LocalDate.now(),299292929,"Juan@gmail.com",Tipo_membresia.ORO,new HashSet<>());


    }

}

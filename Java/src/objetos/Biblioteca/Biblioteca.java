package objetos.Biblioteca;

import java.util.HashSet;

public class Biblioteca {
    HashSet<Publicacion>publicaciones;
    HashSet<Prestable>publicacionesPrestables;
    HashSet<Prestamo>prestamos;

    public Biblioteca(HashSet<Publicacion> publicaciones, HashSet<Prestable> publicacionesPrestables, HashSet<Prestamo> prestamos) {
        this.publicaciones = publicaciones;
        this.publicacionesPrestables = publicacionesPrestables;
        this.prestamos = prestamos;
    }

    public HashSet<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(HashSet<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public HashSet<Prestable> getPublicacionesPrestables() {
        return publicacionesPrestables;
    }

    public void setPublicacionesPrestables(HashSet<Prestable> publicacionesPrestables) {
        this.publicacionesPrestables = publicacionesPrestables;
    }

    public HashSet<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(HashSet<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

     public void devolverPrestamo(){
        for (Prestamo p: prestamos){
            if(p.validar()){
                
            }
        }
     }
}

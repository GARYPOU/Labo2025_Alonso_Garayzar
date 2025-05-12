package test;
import objetos.Estanteria;
import objetos.Pelicula;
import objetos.Videoclub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class VideoClubTest {
    Pelicula p1, p2;
    Estanteria e1;
    Videoclub v1;
    @Test
    public void testmayorduracion(){
        Videoclub v1=new Videoclub();
        Pelicula p1=new Pelicula();
        Pelicula p2=new Pelicula("titanic",2,"amor",new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        Estanteria e1=new Estanteria();
        e1.getPeliculas().add(p1);
        v1.getEstanterias().add(e1);
        assertEquals(98.4, e1.getPeliculas().get(1).getDuracion());
        e1.getPeliculas().add(p2);
        assertEquals(98.4, e1.getPeliculas().get(1).getDuracion());
    }
}

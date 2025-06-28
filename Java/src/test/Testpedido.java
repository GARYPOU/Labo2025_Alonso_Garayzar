//package test;
//
//import objetos.Comida;
//import objetos.Pedido;
//import objetos.Plato;
//import objetos.Sistemapedido;
//import org.junit.Test;
//import personas.Persona;
//import personas.Profesor;
//
//import java.util.ArrayList;
//
//import static junit.framework.Assert.*;
//
//public class Testpedido {
//@Test
//    public void testagregar() {
//        Plato p1 = new Plato();
//        Pedido pe1 = new Pedido();
//        Sistemapedido s1= new Sistemapedido();
//        Plato p2 = new Plato(Comida.CHORIZO, 23);
//        Persona per = new Persona();
//        Profesor profe = new Profesor();
//
//        assertEquals(0, pe1.getPlatos().size());
//        pe1.agregarplatos(p1);
//        assertEquals(1, pe1.getPlatos().size());
//        pe1.modificarplatos(p2);
//        assertEquals(23, pe1.getPlatos().get(1).getPrecio());
//        pe1.eliminarplaros(p1);
//        assertEquals(0, pe1.getPlatos().size());
//        int espacio = s1.platosacocinar().size();
//        assertTrue(espacio>1);
//        for(Plato p: s1.platosacocinar()){
//            assertTrue(p.getPrecio()>0);
//            assertEquals(198,p.getPrecio()*profe.getDescuento());
//        }
//
//    }
//}
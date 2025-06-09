package objetos.Pedidos;

import personas.Profesor;

import java.awt.image.AreaAveragingScaleFilter;
import java.time.LocalDate;
import java.util.ArrayList;

public class Sistemapedido {
    private ArrayList<Pedido> pedidos;

    public Sistemapedido() {
        this.pedidos = new ArrayList<>();
    }

    public Sistemapedido(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public ArrayList<Plato> platosacocinar() {
        ArrayList<Plato> platosdia = new ArrayList<>();
        for (Pedido Pe : pedidos) {
            if (Pe.getCreacion() == LocalDate.now()) {
                for (Plato p : Pe.getPlatos()) {
                    if (Pe.getSolicitud() instanceof Profesor) {
                        float v = p.getPrecio() - ((Profesor) Pe.getSolicitud()).getDescuento();
                        p.setPrecio(v);
                    }
                    platosdia.add(p);
                }
            }
        }
        return  platosdia;
    }

    public void masPedidos() {
        ArrayList<Integer> cant = new ArrayList<>();
        ArrayList<Plato> platines = new ArrayList<>();
        Plato platin = new Plato();
        int canti = 0;
        int cont = 0;
        for (Pedido pe : pedidos) {
            for (Plato p : pe.getPlatos()) {
                for (Plato p2 : pe.getPlatos()) {
                    if (p.getNombre() == p2.getNombre()) {
                        cont = cont + 1;
                    }

                }
                platines.add(p);
                cant.add(cont);
                cont = 0;
            }

        }
        for (int i = 0; i < cant.size(); i++) {
            for (int j = i + 1; j < cant.size(); j++) {
                if (cant.get(i) < cant.get(j)) {
                    canti = cant.get(i);
                    platin = platines.get(i);
                    cant.set(i, cant.get(j));
                    platines.set(i, platines.get(j));
                    cant.set(j, canti);
                    platines.set(j, platin);

                }

            }
        }
        System.out.println("el top uno fue:" + platines.getFirst().getNombre() + "el top dos fue:" + platines.get(2).getNombre() + "el top tres fue:" + platines.get(3).getNombre());
    }


    public static void main(String[] args) {
        Sistemapedido s1 = new Sistemapedido();
        s1.platosacocinar();
        s1.masPedidos();
    }
}
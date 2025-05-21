package objetos;

import personas.Profesor;

import java.time.LocalDate;
import java.util.ArrayList;

public class Sistemapedido {
    private ArrayList<Pedido> pedidos;


    public Sistemapedido(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public ArrayList<Plato> platosacocinar(){
        ArrayList<Plato>platosdia= new ArrayList<>();
        for(Pedido Pe:pedidos){
            if(Pe.getCreacion()== LocalDate.now()){
                for ( Plato p: Pe.getPlatos()){
                    if (Pe.getSolicitud() instanceof Profesor){
                        p.getPrecio()-
                    }
                    platosdia.add(p);
                }
            }
        }
    }
}
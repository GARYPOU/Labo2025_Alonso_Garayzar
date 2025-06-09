package objetos.Electrodomesticos;

import java.util.ArrayList;

public class TiendaElectrodomesticos {
    private ArrayList<Electrodomestico>productos;

    public TiendaElectrodomesticos(){
        this.productos=new ArrayList<>();
    }

    public TiendaElectrodomesticos(ArrayList<Electrodomestico>productos){
        this.productos=productos;
    }

    public ArrayList<Electrodomestico> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Electrodomestico> productos) {
        this.productos = productos;
    }




    public void agregarProdu(Electrodomestico producto){
        productos.add(producto);

    }
    public void modificarProdu(Electrodomestico producto, int posicion){
        productos.set(posicion,producto);
    }
    public void borrarProdu(Electrodomestico producto){
        productos.remove(producto);

    }


    public double masStock(){
        int masStock=0;
        Electrodomestico eMas = new Electrodomestico();
        for (Electrodomestico e: productos){
            if(e.getStock()>eMas.getStock()){
            masStock=e.getStock();
            }
        }
        return masStock;
    }
    public double minStock(){
        int minStock=0;
        Electrodomestico eMenos = new Electrodomestico();
        for (Electrodomestico e: productos){
            if(e.getStock()<eMenos.getStock()){
                minStock=e.getStock();
            }
        }
        return minStock;
    }
}

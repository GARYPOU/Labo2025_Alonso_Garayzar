package objetos;

import personas.Persona;

import java.util.ArrayList;

public class Sistemacompu {
    private ArrayList<Computadora> computadoras;
    private ArrayList<Compra> compras;


    public Sistemacompu(){
        this.computadoras=new ArrayList<>();
        this.compras=new ArrayList<>();
    }

    public Sistemacompu(ArrayList<Computadora> computadoras, ArrayList<Compra> compras) {
        this.computadoras = computadoras;
        this.compras = compras;
    }
    public void registrarCompra(int cant, ArrayList<Integer>cantPeri, ArrayList<String>nombrePeri){
        Compra c1 = new Compra(new Persona(),"Tarjeta",cant);
        Dispositivo d1 = new Dispositivo();
        CPU cp1 = new CPU();
        compras.add(c1);
        for(int i=0; i<nombrePeri.size();i++) {
            if (d1.getNombre()==nombrePeri.get(i)){
                d1.setStock(d1.getStock() - cantPeri.get(i));

            }

        }
        cp1.setStock(cp1.getStock()-cant);



    }
}

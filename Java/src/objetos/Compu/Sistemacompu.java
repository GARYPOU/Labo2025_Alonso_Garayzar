package objetos.Compu;


import java.util.ArrayList;

public class Sistemacompu {
    private ArrayList<Compra> computadoras;



    public Sistemacompu(){
        this.computadoras=new ArrayList<>();

    }

    public Sistemacompu(ArrayList<Compra> computadoras) {
        this.computadoras = computadoras;

    }
    public void regisCompra(Compra compu){
        computadoras.add(compu);
    }
}
